package com.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;
import static org.openqa.selenium.support.ui.ExpectedConditions.*;

public class PublicNaviPage extends PageBase {
    //Constructor
    public PublicNaviPage(WebDriver wd, WebDriverWait wait) {
        super(wd, wait);
    }

    public void clickMyAccount() {
        WebElement dropDown = wait.until(elementToBeClickable(By.xpath("//a[@title = 'My Account']")));
        if (dropDown.getAttribute("aria-expanded") == null || dropDown.getAttribute("aria-expanded").equals("false"))
            dropDown.click();
    }

    public void clickRegister() {
        click(By.xpath("//ul[@class = 'dropdown-menu dropdown-menu-right']//a[contains(@href, 'register')]"));
        wait.until(visibilityOfElementLocated(By.xpath("//h1[text() = 'Account']")));
    }

    public void clickLogin() {
        click(By.xpath("//ul[@class = 'dropdown-menu dropdown-menu-right']//a[contains(@href, 'login')]"));
        wait.until(visibilityOfElementLocated(By.xpath("//div[@class = 'well']/h2[text() = 'Returning Customer']")));
    }

    public void clickLogout() {
        click(By.xpath("//a[contains(@href, 'logout')]"));
    }

    public List<WebElement> getMenuItems() {
        return wait.until(visibilityOfAllElementsLocatedBy(By.xpath("//ul[@class = 'dropdown-menu dropdown-menu-right']/li/a")));
    }
}
