package com.opencart.tests;

import com.opencart.models.PublicLoginData;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

public class PublicLoginTests extends TestBase {
    @Test
    public void loginTest() {
        app.getPublicNaviPage().clickMyAccount();
        app.getPublicNaviPage().clickLogin();

        PublicLoginData loginData = new PublicLoginData().setEmail("")
                                                         .setPassword("");
        app.getPublicLoginPage().fillLoginForm(loginData);

        assertEquals(app.getPublicLoginPage().getMyAccountTitle(), "My Account", "User isn't logged in!");
    }
}
