package com.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AdminNaviPage extends PageBase {
    //Constructor
    public AdminNaviPage(WebDriver wd, WebDriverWait wait) {
        super(wd, wait);
    }

    public String getAdminDashboardTitle() {
        return wd.findElement(By.xpath("//h1[text() = 'Dashboard']")).getText();
    }
}
