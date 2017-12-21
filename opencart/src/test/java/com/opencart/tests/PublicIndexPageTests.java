package com.opencart.tests;

import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import java.util.List;
import static org.testng.Assert.*;

public class PublicIndexPageTests extends TestBase {
    @Test(enabled = true, priority = 2)
    public void naviMenuTransitionTest() {
        List<WebElement> naviMenuItems = app.getPublicNaviPage().getNaviMenuItemsWithSubItems();
        app.getPublicNaviPage().naviMenuItemsTransition(naviMenuItems);
    }

    @Test(enabled = true, priority = 1)
    public void openNaviMenuItemTest() {
        app.getPublicNaviPage().openNeededNaviMenuItem("Desktops", "PC");

        assertEquals(app.getPublicNaviPage().getNaviMenuItemTitle(), "PC");
    }

    @Test(enabled = true, priority = 0)
    public void openAllNaviMenuItemsTest() {
        app.getPublicNaviPage().openAllNaviMenuItems();
    }
}
