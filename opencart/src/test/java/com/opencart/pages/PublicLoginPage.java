package com.opencart.pages;

import com.opencart.models.PublicLoginData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import static org.openqa.selenium.support.ui.ExpectedConditions.*;

public class PublicLoginPage extends PageBase {
    //Constructor
    public PublicLoginPage(WebDriver wd, WebDriverWait wait) {
        super(wd, wait);
    }

    public void fillLoginForm(PublicLoginData loginData) {
        input(By.xpath("//input[@id = 'input-email']"), loginData.getEmail());
        input(By.xpath("//input[@id = 'input-password']"), loginData.getPassword());
        click(By.xpath("//input[@value = 'Login']"));
    }

    public String getMyAccountTitle() {
        return wait.until(visibilityOfElementLocated(By.xpath("//div[@id = 'content']/h2[text() = 'My Account']"))).getText();
    }

    public String getLoginFormTitle() {
        return wait.until(visibilityOfElementLocated(By.xpath("//div[@class = 'well']/h2[text() = 'Returning Customer']"))).getText();
    }
}
