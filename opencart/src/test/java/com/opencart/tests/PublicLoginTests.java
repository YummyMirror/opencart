package com.opencart.tests;

import com.opencart.dataProviders.AllDataProviders;
import com.opencart.listeners.MyRetryAnalyzer;
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

    @Test(enabled = true, dataProviderClass = AllDataProviders.class, dataProvider = "validLoginDataJson", priority = 1, retryAnalyzer = MyRetryAnalyzer.class)
    public void loginTest(PublicLoginData loginData) {
        app.getPublicNaviPage().clickMyAccount();
        app.getPublicNaviPage().clickLogin();
        app.getPublicLoginPage().fillLoginForm(loginData);

        assertEquals(app.getPublicLoginPage().getMyAccountTitle(), "My Account", "User isn't logged in!");
    }

    @Test(enabled = true, dataProviderClass = AllDataProviders.class, dataProvider = "invalidLoginDataJson", priority = 2, retryAnalyzer = MyRetryAnalyzer.class)
    public void invalidLoginTest(PublicLoginData loginData) {
        app.getPublicNaviPage().clickMyAccount();
        app.getPublicNaviPage().clickLogin();
        app.getPublicLoginPage().fillLoginForm(loginData);

        assertEquals(app.getPublicLoginPage().getLoginFormTitle(), "Returning Customer", "User is logged in!");
    }
}
