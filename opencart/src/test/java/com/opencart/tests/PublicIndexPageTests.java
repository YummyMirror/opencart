package com.opencart.tests;

import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import java.util.List;

public class PublicIndexPageTests extends TestBase {
    @Test
    public void naviMenuTransitionTest() {
        List<WebElement> naviMenuItems = app.getPublicNaviPage().getNaviMenuItems();
        app.getPublicNaviPage().naviMenuItemsTransition(naviMenuItems);
    }
}
