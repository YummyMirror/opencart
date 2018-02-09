package com.opencart.tests;

import com.opencart.annotations.DataSource;
import com.opencart.dataProviders.AllDataProviders;
import com.opencart.listeners.RetryAnalyzer;
import com.opencart.models.PublicLoginData;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.util.List;
import static org.testng.Assert.*;

public class PublicLoginTests extends TestBase {
    @BeforeMethod
    public void precondition() {
        app.getPublicNaviPage().clickMyAccount();
        List<WebElement> menuItems = app.getPublicNaviPage().getAccountMenuItems();
        if (menuItems.size() > 2)
            app.getPublicNaviPage().clickLogout();
    }

    @DataSource("src/test/resources/dataProviders/validLoginData.json")
    @Test(priority = 1, groups = {"criticalPath"}, dataProviderClass = AllDataProviders.class, dataProvider = "loginData", retryAnalyzer = RetryAnalyzer.class)
    public void loginTest(PublicLoginData loginData) {
        app.getPublicNaviPage().clickMyAccount();
        app.getPublicNaviPage().clickLogin();
        app.getPublicLoginPage().fillLoginForm(loginData);

        assertEquals(app.getPublicLoginPage().getMyAccountTitle(), "My Account", "User isn't logged in!");
    }

    @DataSource("src/test/resources/dataProviders/invalidLoginData.json")
    @Test(priority = 2, groups = {"regression"}, dataProviderClass = AllDataProviders.class, dataProvider = "loginData", retryAnalyzer = RetryAnalyzer.class)
    public void invalidLoginTest(PublicLoginData loginData) {
        app.getPublicNaviPage().clickMyAccount();
        app.getPublicNaviPage().clickLogin();
        app.getPublicLoginPage().fillLoginForm(loginData);

        assertEquals(app.getPublicLoginPage().getLoginFormTitle(), "Returning Customer", "User is logged in!");
    }
}
