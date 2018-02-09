package com.opencart.tests;

import com.opencart.annotations.DataSource;
import com.opencart.dataProviders.AllDataProviders;
import com.opencart.listeners.RetryAnalyzer;
import com.opencart.models.PublicRegData;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.util.List;
import static org.testng.Assert.*;

public class PublicRegTests extends TestBase {
    @BeforeMethod
    public void precondition() {
        app.getPublicNaviPage().clickMyAccount();
        List<WebElement> menuItems = app.getPublicNaviPage().getAccountMenuItems();
        if (menuItems.size() > 2)
            app.getPublicNaviPage().clickLogout();
    }

    @DataSource("src/test/resources/dataProviders/validRegData.json")
    @Test(priority = 1, groups = {"criticalPath"}, dataProviderClass = AllDataProviders.class, dataProvider = "registrationData", retryAnalyzer = RetryAnalyzer.class)
    public void registrationTest(PublicRegData regData) {
        app.getPublicNaviPage().clickMyAccount();
        app.getPublicNaviPage().clickRegister();
        app.getPublicRegPage().fillRegistrationForm(regData);

        assertEquals(app.getPublicRegPage().getSuccessLinkTitle(), "Success", "User isn't registered!");
    }

    @DataSource("src/test/resources/dataProviders/invalidRegData.json")
    @Test(priority = 2, groups = {"regression"}, dataProviderClass = AllDataProviders.class, dataProvider = "registrationData", retryAnalyzer = RetryAnalyzer.class)
    public void invalidRegistrationTest(PublicRegData regData) {
        app.getPublicNaviPage().clickMyAccount();
        app.getPublicNaviPage().clickRegister();
        app.getPublicRegPage().fillRegistrationForm(regData);

        assertEquals(app.getPublicRegPage().getRegisterLinkTitle(), "Register", "User is registered!");
    }
}