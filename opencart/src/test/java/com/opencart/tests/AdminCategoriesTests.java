package com.opencart.tests;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.opencart.models.AdminCategoryData;
import org.openqa.selenium.WebElement;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Iterator;
import java.util.List;
import static org.testng.Assert.*;

public class AdminCategoriesTests extends TestBase {
    @DataProvider
    public Iterator<Object[]> validCatDataJson() throws IOException {
        File file = new File("src/test/resources/dataProviders/validCategoryData.json");
        FileReader reader = new FileReader(file);
        BufferedReader buffReader = new BufferedReader(reader);
        String line = buffReader.readLine();
        String json = "";
        while (line != null) {
            json += line;
            line = buffReader.readLine();
        }
        Gson gson = new Gson();
        Type collectionType = new TypeToken<List<AdminCategoryData>>(){}.getType();
        List<AdminCategoryData> list = gson.fromJson(json, collectionType);
        reader.close();
        buffReader.close();
        return list.stream().map(l -> new Object[] {l}).iterator();
    }

    @Test(enabled = true, dataProvider = "validCatDataJson", priority = 1)
    public void createMainCategories(AdminCategoryData categoryData) {
        //Public side
        List<WebElement> listMenuItemsBefore = app.getPublicNaviPage().getListMenuItems();
        //Admin side
        app.getAdminNaviPage().openNewWindow("http://localhost/opencart/admin/");
        app.getAdminNaviPage().loginToAdminSide("admin", "admin");
        assertEquals(app.getAdminNaviPage().getAdminDashboardTitle(), "Dashboard", "User isn't logged in to admin side!");

        app.getAdminNaviPage().openMenuItem("Catalog", "Categories", "");
        assertEquals(app.getAdminNaviPage().getMenuItemHeaderTitle(), "Categories", "Categories menu item isn't opened!");

        int categoryAmountBefore = app.getAdminCategoryPage().getCategoryAmount();
        app.getAdminCategoryPage().createCategory(categoryData);

        int categoryAmountAfter = app.getAdminCategoryPage().getCategoryAmount();
        assertEquals(categoryAmountAfter, categoryAmountBefore + 1, "New category isn't created!");
        //Public side
        app.getAdminNaviPage().closeCurrentWindow();
        app.getPublicNaviPage().refreshPage();
        List<WebElement> listMenuItemsAfter = app.getPublicNaviPage().getListMenuItems();
        assertEquals(listMenuItemsAfter.size(), listMenuItemsBefore.size() + 1, "New category isn't presented at Public side!");
    }
}
