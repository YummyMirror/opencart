package com.opencart.tests;

import com.opencart.dataProviders.AllDataProviders;
import com.opencart.listeners.MyRetryAnalyzer;
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

    @Test(enabled = true, dataProviderClass = AllDataProviders.class, dataProvider = "validRegDataJson", priority = 1, retryAnalyzer = MyRetryAnalyzer.class)
    public void registrationTest(PublicRegData regData) {
        app.getPublicNaviPage().clickMyAccount();
        app.getPublicNaviPage().clickRegister();
        app.getPublicRegPage().fillRegistrationForm(regData);

        assertEquals(app.getPublicRegPage().getSuccessLinkTitle(), "Success", "User isn't registered!");
    }

    @Test(enabled = true, dataProviderClass = AllDataProviders.class, dataProvider = "invalidRegDataJson", priority = 2, retryAnalyzer = MyRetryAnalyzer.class)
    public void invalidRegistrationTest(PublicRegData regData) {
        app.getPublicNaviPage().clickMyAccount();
        app.getPublicNaviPage().clickRegister();
        app.getPublicRegPage().fillRegistrationForm(regData);

        assertEquals(app.getPublicRegPage().getRegisterLinkTitle(), "Register", "User is registered!");
    }
}