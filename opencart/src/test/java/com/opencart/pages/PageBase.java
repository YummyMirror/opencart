package com.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;
import static org.openqa.selenium.support.ui.ExpectedConditions.*;

public class PageBase {
    protected WebDriver wd;
    protected WebDriverWait wait;


    //Constructor
    public PageBase(WebDriver wd, WebDriverWait wait) {
        this.wd = wd;
        this.wait = wait;
    }

    public void check(By locator, Boolean value) {
        WebElement element = wd.findElement(locator);
        if (value.equals(true)) {
            if (element.getAttribute("checked") == null)
                element.click();
        } else {
            if (element.getAttribute("checked") != null)
                element.click();
        }
    }

    public void input(By locator, String value) {
        if (value != null) {
            String currentValue = wd.findElement(locator).getAttribute("value");
            if (!value.equals(currentValue)) {
                wd.findElement(locator).click();
                wd.findElement(locator).clear();
                wd.findElement(locator).sendKeys(value);
            }
        }
    }

    public void radio(By locator, Boolean value) {
        List<WebElement> radios = wd.findElements(locator);
        WebElement yes = radios.stream().filter(r -> r.getAttribute("value").equals("1")).findAny().get();
        WebElement no = radios.stream().filter(r -> r.getAttribute("value").equals("0")).findAny().get();
        if (value.equals(true)) {
            if (yes.getAttribute("checked") == null)
                yes.click();
        } else {
            if (no.getAttribute("checked") == null)
                no.click();
        }
    }

    public void click(By locator) {
        wait.until(elementToBeClickable(locator)).click();
    }
}
