package com.opencart;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import static org.openqa.selenium.support.ui.ExpectedConditions.*;

public class PageBase {
    protected WebDriver wd;
    protected WebDriverWait wait;

    //Constructor
    public PageBase(WebDriver wd, WebDriverWait wait) {
        this.wd = wd;
        this.wait = wait;
    }

    public void check(By locator) {
        wd.findElement(locator).click();
    }

    public void input(By locator, String value) {
        wd.findElement(locator).click();
        wd.findElement(locator).clear();
        wd.findElement(locator).sendKeys(value);
    }

    public void click(By locator) {
        wait.until(elementToBeClickable(locator)).click();
    }
}
