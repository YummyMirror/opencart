package com.opencart.tests;

import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;

public class AdminCategoriesTests extends TestBase {
    @Test
    public void createCategories() {
        app.getAdminNaviPage().openNewWindow("http://localhost/opencart/admin/");
        app.getAdminNaviPage().loginToAdminSide("admin", "admin");
        //app.getAdminNaviPage().openMenuItem("Catalog", "Attributes", "Attribute Groups");
        app.getAdminNaviPage().openMenuItem("Catalog", "Attributes", "Attribute Groups");

        //assertEquals("Dashboard", app.getAdminNaviPage().getAdminDashboardTitle(), "User isn't logged in to admin side!");
    }
}
