package com.opencart.tests;

import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import java.util.List;
import static org.testng.Assert.*;

public class PublicIndexPageTests extends TestBase {
    @Test
    public void naviMenuTransitionTest() {
        List<WebElement> naviMenuItems = app.getPublicNaviPage().getNaviMenuItemsWithSubItems();
        app.getPublicNaviPage().naviMenuItemsTransition(naviMenuItems);
    }

    @Test
    public void openNaviMenuItemTest() {
        app.getPublicNaviPage().openNeededNaviMenuItem("Desktops", "PC");

        assertEquals(app.getPublicNaviPage().getNaviMenuItemTitle(), "PC");
    }
}
