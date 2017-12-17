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
        input(By.xpath("//input[@id = 'input-email']"), "test@test.test");
        input(By.xpath("//input[@id = 'input-password']"), "test");
        click(By.xpath("//input[@value = 'Login']"));
        wait.until(visibilityOfElementLocated(By.xpath("//div[@id = 'content']/h2[text() = 'My Account']")));
    }

    public String getMyAccountTitle() {
        return wd.findElement(By.xpath("//div[@id = 'content']/h2[text() = 'My Account']")).getText();
    }
}
