package com.opencart.tests;

import com.opencart.models.PublicRegistrationData;
import org.testng.annotations.Test;

public class PublicRegistrationTests extends TestBase {
    @Test
    public void registrationTest() {
        String randomEmail = app.getPublicRegistrationPage().randomEmailGeneration();
        PublicRegistrationData registrationData = new PublicRegistrationData().setFirstName("Kobe")
                                                                              .setLastName("Bryant")
                                                                              .setEmail(randomEmail)
                                                                              .setPhone("12345")
                                                                              .setPassword("password")
                                                                              .setRePassword("password")
                                                                              .setSubscribe(true)
                                                                              .setPolicy(true);

        app.getPublicRegistrationPage().clickMyAccount();
        app.getPublicRegistrationPage().clickRegister();
        app.getPublicRegistrationPage().fillRegistrationForm();
    }
}