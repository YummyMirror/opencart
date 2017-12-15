package com.opencart.tests;

import com.opencart.models.PublicRegData;
import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;

public class PublicRegTests extends TestBase {
    @Test
    public void registrationTest() {
        String randomEmail = app.getPublicRegPage().randomEmailGeneration();
        PublicRegData regData = new PublicRegData().setFirstName("Kobe")
                                                   .setLastName("Bryant")
                                                   .setEmail(randomEmail)
                                                   .setPhone("12345")
                                                   .setPassword("password")
                                                   .setRePassword("password")
                                                   .setSubscribe(true)
                                                   .setPolicy(true);

        app.getPublicRegPage().clickMyAccount();
        app.getPublicRegPage().clickRegister();
        app.getPublicRegPage().fillRegistrationForm(regData);

        assertEquals(app.getPublicRegPage().getAccountTitle(), "My Account");
    }
}