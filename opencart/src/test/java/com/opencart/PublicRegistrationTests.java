package com.opencart;

import org.testng.annotations.Test;

public class PublicRegistrationTests extends TestBase {
    @Test
    public void registrationTest() {
        application.clickMyAccount();
        application.clickRegister();
        application.fillRegistrationForm();
    }
}
