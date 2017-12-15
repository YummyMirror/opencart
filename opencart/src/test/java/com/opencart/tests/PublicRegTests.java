package com.opencart.tests;

import com.opencart.models.PublicRegData;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import static org.testng.Assert.*;

public class PublicRegTests extends TestBase {
    @DataProvider
    public Iterator<Object[]> validRegDataCsv() throws IOException {
        List<Object[]> list = new ArrayList<Object[]>(0);
        File file = new File("src/test/resources/dataProviders/validRegData.csv");
        FileReader reader = new FileReader(file);
        BufferedReader buffReader = new BufferedReader(reader);
        String line = buffReader.readLine();
        while (line != null) {
            String[] splitData = line.split(";");
            list.add(new Object[] {new PublicRegData().setFirstName(splitData[0])
                                                      .setLastName(splitData[1])
                                                      .setEmail(splitData[2])
                                                      .setPhone(splitData[3])
                                                      .setPassword(splitData[4])
                                                      .setRePassword(splitData[5])
                                                      .setSubscribe(Boolean.parseBoolean(splitData[6]))
                                                      .setPolicy(Boolean.parseBoolean(splitData[7]))});
            line = buffReader.readLine();
        }
        reader.close();
        buffReader.close();
        return list.iterator();
    }

    @BeforeMethod
    public void precondition() {
        app.getPublicRegPage().clickMyAccount();
        List<WebElement> menuItems = app.getPublicRegPage().getMenuItems();
        if (menuItems.size() > 2)
            app.getPublicRegPage().clickLogout();
    }

    @Test(enabled = true, dataProvider = "validRegDataCsv", priority = 1)
    public void registrationTest(PublicRegData regData) {
        app.getPublicRegPage().clickMyAccount();
        app.getPublicRegPage().clickRegister();
        app.getPublicRegPage().fillRegistrationForm(regData);

        assertEquals(app.getPublicRegPage().getAccountTitle(), "My Account");
    }
}