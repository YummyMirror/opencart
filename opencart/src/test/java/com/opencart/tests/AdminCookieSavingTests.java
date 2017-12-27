package com.opencart.tests;

import org.openqa.selenium.Cookie;
import org.testng.annotations.Test;
import java.io.IOException;
import java.util.Set;
import static org.testng.Assert.*;

public class AdminCookieSavingTests extends TestBase {
    @Test(enabled = true, priority = 3)
    public void saveAdminSideCookie() throws IOException {
        app.getAdminNaviPage().openNewWindow("http://localhost/opencart/admin/");
        app.getAdminNaviPage().loginToAdminSide("admin", "admin");
        assertEquals(app.getAdminNaviPage().getAdminDashboardTitle(), "Dashboard", "Authorization to Admin side isn't occurred!");

        Set<Cookie> cookies = app.getAdminNaviPage().getCookies();
        assertTrue(cookies.size() > 0, "There are no cookies!");

        app.getAdminNaviPage().saveCookies(cookies);
    }
}
