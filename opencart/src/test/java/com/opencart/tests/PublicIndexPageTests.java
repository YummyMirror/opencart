package com.opencart.tests;

import com.opencart.listeners.MyRetryAnalyzer;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import java.util.List;
import static org.testng.Assert.*;

public class PublicIndexPageTests extends TestBase {
    @Test(enabled = true, priority = 2, retryAnalyzer = MyRetryAnalyzer.class)
    public void naviMenuTransitionTest() {
        List<WebElement> naviMenuItems = app.getPublicNaviPage().getNaviMenuItemsWithSubItems();
        app.getPublicNaviPage().naviMenuItemsTransition(naviMenuItems);
    }

    @Test(enabled = true, priority = 1, retryAnalyzer = MyRetryAnalyzer.class)
    public void openNaviMenuItemTest() {
        app.getPublicNaviPage().openNeededNaviMenuItem("Desktops", "PC");

        assertEquals(app.getPublicNaviPage().getNaviMenuItemTitle(), "PC");
    }

    @Test(enabled = true, priority = 0, retryAnalyzer = MyRetryAnalyzer.class)
    public void openAllNaviMenuItemsTest() {
        app.getPublicNaviPage().openAllNaviMenuItems();
    }
}
