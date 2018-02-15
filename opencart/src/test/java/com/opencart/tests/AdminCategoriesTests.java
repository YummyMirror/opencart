package com.opencart.tests;

import com.opencart.annotations.DataSource;
import com.opencart.dataProviders.AllDataProviders;
import com.opencart.listeners.RetryAnalyzer;
import com.opencart.models.AdminCategoryData;
import org.openqa.selenium.By;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

public class AdminCategoriesTests extends TestBase {
    @BeforeMethod
    public void precondition() {
        if (!app.getAdminProductPage().areElementsPresent(By.xpath("//img[@title = 'OpenCart']")))
            app.getAdminProductPage().openAdminSideAndLogin();
        if (!app.getAdminProductPage().areElementsPresent(By.xpath("//a[contains(@href, 'catalog/category/add')]"))) {
            app.getAdminNaviPage().openMenuItem("Catalog", "Categories", "");
            assertEquals(app.getAdminNaviPage().getMenuItemHeaderTitle(), "Categories", "Categories menu item isn't opened!");
        }
    }

    @DataSource(value = "src/test/resources/dataProviders/validCategoryData.json", format = DataSource.Format.JSON)
    @Test(priority = 1, groups = {"criticalPath"}, dataProviderClass = AllDataProviders.class, dataProvider = "categoryData", retryAnalyzer = RetryAnalyzer.class)
    public void createMainCategories(AdminCategoryData categoryData) {
        int categoryAmountBefore = app.getAdminCategoryPage().getCategoryAmount();
        app.getAdminCategoryPage().createCategory(categoryData);

        int categoryAmountAfter = app.getAdminCategoryPage().getCategoryAmount();
        assertEquals(categoryAmountAfter, categoryAmountBefore + 1, "New category isn't created!");
    }
}
