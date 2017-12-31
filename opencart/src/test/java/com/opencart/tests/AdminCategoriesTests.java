package com.opencart.tests;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.opencart.listeners.MyRetryAnalyzer;
import com.opencart.models.AdminCategoryData;
import org.openqa.selenium.By;
import org.testng.annotations.BeforeMethod;
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

    @BeforeMethod
    public void precondition() {
        if (!app.getAdminProductPage().areElementsPresent(By.xpath("//img[@title = 'OpenCart']"))) {
            app.getAdminProductPage().openAdminSideAndLogin();
            if (!app.getAdminProductPage().areElementsPresent(By.xpath("//a[contains(@href, 'catalog/category/add')]"))) {
                app.getAdminNaviPage().openMenuItem("Catalog", "Categories", "");
                assertEquals(app.getAdminNaviPage().getMenuItemHeaderTitle(), "Categories", "Categories menu item isn't opened!");
            }
        }
    }

    @Test(enabled = true, dataProvider = "validCatDataJson", priority = 1, retryAnalyzer = MyRetryAnalyzer.class)
    public void createMainCategories(AdminCategoryData categoryData) {
        //Lazy action for Retry Analyzer
        if (!app.getAdminProductPage().areElementsPresent(By.xpath("//a[contains(@href, 'catalog/category/add')]"))) {
            app.getAdminNaviPage().openMenuItem("Catalog", "Categories", "");
            assertEquals(app.getAdminNaviPage().getMenuItemHeaderTitle(), "Categories", "Categories menu item isn't opened!");
        }
        int categoryAmountBefore = app.getAdminCategoryPage().getCategoryAmount();
        app.getAdminCategoryPage().createCategory(categoryData);

        int categoryAmountAfter = app.getAdminCategoryPage().getCategoryAmount();
        assertEquals(categoryAmountAfter, categoryAmountBefore + 1, "New category isn't created!");
    }
}
