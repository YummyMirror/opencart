package com.opencart.tests;

import com.opencart.listeners.RetryAnalyzer;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import java.util.List;
import static org.testng.Assert.*;

public class PublicIndexPageTests extends TestBase {
    @Test(priority = 2, groups = {"regression"}, retryAnalyzer = RetryAnalyzer.class)
    public void naviMenuTransitionTest() {
        List<WebElement> naviMenuItems = app.getPublicNaviPage().getNaviMenuItemsWithSubItems();
        app.getPublicNaviPage().naviMenuItemsTransition(naviMenuItems);
    }

    @Test(priority = 1, groups = {"criticalPath"}, retryAnalyzer = RetryAnalyzer.class)
    public void openNaviMenuItemTest() {
        app.getPublicNaviPage().openNeededNaviMenuItem("Desktops", "PC");

        assertEquals(app.getPublicNaviPage().getNaviMenuItemTitle(), "PC");
    }

    @Test(priority = 0, groups = {"smoke"}, retryAnalyzer = RetryAnalyzer.class)
    public void openAllNaviMenuItemsTest() {
        app.getPublicNaviPage().openAllNaviMenuItems();
    }
}
