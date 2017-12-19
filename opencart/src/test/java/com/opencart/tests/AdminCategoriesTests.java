package com.opencart.tests;

import com.opencart.models.AdminCategoryData;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import java.util.List;
import static org.testng.Assert.*;

public class AdminCategoriesTests extends TestBase {
    @Test
    public void createMainCategories() {
        //Public side
        List<WebElement> listMenuItemsBefore = app.getPublicNaviPage().getListMenuItems();
        //Admin side
        app.getAdminNaviPage().openNewWindow("http://localhost/opencart/admin/");
        app.getAdminNaviPage().loginToAdminSide("admin", "admin");
        assertEquals(app.getAdminNaviPage().getAdminDashboardTitle(), "Dashboard", "User isn't logged in to admin side!");

        app.getAdminNaviPage().openMenuItem("Catalog", "Categories", "");
        assertEquals(app.getAdminNaviPage().getMenuItemHeaderTitle(), "Categories", "Categories menu item isn't opened!");

        int categoryAmountBefore = app.getAdminCategoryPage().getCategoryAmount();
        app.getAdminCategoryPage().openAddNewCategoryPage();
        AdminCategoryData categoryData = new AdminCategoryData().setCatName("Test")
                                                                .setCatDesc("Desc")
                                                                .setMetaTagTitle("Test_ANA2")
                                                                .setMetaTagDesc("Meta tag desc")
                                                                .setMetaTagKeywords("Meta tag keyword")
                                                                .setParent("")
                                                                .setFilters("Filters")
                                                                .setTop(true)
                                                                .setColumns("1")
                                                                .setSortOrder("1")
                                                                .setStatus(true)
                                                                .setSeoKeywords("Seo")
                                                                .setDesignLayout("Account");
        app.getAdminCategoryPage().inputCategoryForm(categoryData);

        int categoryAmountAfter = app.getAdminCategoryPage().getCategoryAmount();
        assertEquals(categoryAmountAfter, categoryAmountBefore + 1, "New category isn't created!");
        //Public side
        app.getAdminNaviPage().closeCurrentWindow();
        app.getPublicNaviPage().refreshPage();
        List<WebElement> listMenuItemsAfter = app.getPublicNaviPage().getListMenuItems();
        assertEquals(listMenuItemsAfter.size(), listMenuItemsBefore.size() + 1, "New category isn't presented at Public side!");
    }
}
