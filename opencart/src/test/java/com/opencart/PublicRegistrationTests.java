package com.opencart;

import org.testng.annotations.Test;

public class PublicRegistrationTests extends TestBase {
    @Test
    public void registrationTest() {
        application.getPublicRegistrationPage().clickMyAccount();
        application.getPublicRegistrationPage().clickRegister();
        application.getPublicRegistrationPage().fillRegistrationForm();
    }
}
