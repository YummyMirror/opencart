package com.opencart.tests;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.opencart.listeners.MyRetryAnalyzer;
import com.opencart.models.PublicLoginData;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.io.*;
import java.lang.reflect.Type;
import java.util.Iterator;
import java.util.List;
import static org.testng.Assert.*;

public class PublicLoginTests extends TestBase {
    @DataProvider
    public Iterator<Object[]> validLoginDataJson() throws IOException {
        File file = new File("src/test/resources/dataProviders/validLoginData.json");
        FileReader reader = new FileReader(file);
        BufferedReader buffReader = new BufferedReader(reader);
        String line = buffReader.readLine();
        String json = "";
        while (line != null) {
            json += line;
            line = buffReader.readLine();
        }
        Gson gson = new Gson();
        Type collectionType = new TypeToken<List<PublicLoginData>>(){}.getType();
        List<PublicLoginData> list = gson.fromJson(json, collectionType);
        reader.close();
        buffReader.close();
        return list.stream().map(l -> new Object[] {l}).iterator();
    }

    @DataProvider
    public Iterator<Object[]> invalidLoginDataJson() throws IOException {
        File file = new File("src/test/resources/dataProviders/invalidLoginData.json");
        FileReader reader = new FileReader(file);
        BufferedReader buffReader = new BufferedReader(reader);
        String line = buffReader.readLine();
        String json = "";
        while (line != null) {
            json += line;
            line = buffReader.readLine();
        }
        Gson gson = new Gson();
        Type collectionType = new TypeToken<List<PublicLoginData>>(){}.getType();
        List<PublicLoginData> list = gson.fromJson(json, collectionType);
        reader.close();
        buffReader.close();
        return list.stream().map(l -> new Object[] {l}).iterator();
    }

    @BeforeMethod
    public void precondition() {
        app.getPublicNaviPage().clickMyAccount();
        List<WebElement> menuItems = app.getPublicNaviPage().getAccountMenuItems();
        if (menuItems.size() > 2)
            app.getPublicNaviPage().clickLogout();
    }

    @Test(enabled = true, dataProvider = "validLoginDataJson", priority = 1, retryAnalyzer = MyRetryAnalyzer.class)
    public void loginTest(PublicLoginData loginData) {
        app.getPublicNaviPage().clickMyAccount();
        app.getPublicNaviPage().clickLogin();
        app.getPublicLoginPage().fillLoginForm(loginData);

        assertEquals(app.getPublicLoginPage().getMyAccountTitle(), "My Account", "User isn't logged in!");
    }

    @Test(enabled = true, dataProvider = "invalidLoginDataJson", priority = 2, retryAnalyzer = MyRetryAnalyzer.class)
    public void invalidLoginTest(PublicLoginData loginData) {
        app.getPublicNaviPage().clickMyAccount();
        app.getPublicNaviPage().clickLogin();
        app.getPublicLoginPage().fillLoginForm(loginData);

        assertEquals(app.getPublicLoginPage().getLoginFormTitle(), "Returning Customer", "User is logged in!");
    }
}
