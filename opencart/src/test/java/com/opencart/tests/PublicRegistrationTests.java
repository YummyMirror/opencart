package com.opencart.tests;

import org.testng.annotations.Test;

public class PublicRegistrationTests extends TestBase {
    @Test
    public void registrationTest() {
        app.getPublicRegistrationPage().clickMyAccount();
        app.getPublicRegistrationPage().clickRegister();
        app.getPublicRegistrationPage().fillRegistrationForm();
    }
}
