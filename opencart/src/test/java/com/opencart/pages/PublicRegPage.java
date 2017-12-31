package com.opencart.pages;

import com.opencart.models.PublicRegData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.List;
import static org.openqa.selenium.support.ui.ExpectedConditions.*;

public class PublicRegPage extends PageBase {
    //Constructor
    public PublicRegPage(WebDriver wd, WebDriverWait wait, Actions actions) {
        super(wd, wait, actions);
    }

    public void fillRegistrationForm(PublicRegData registrationData) {
        input(By.xpath("//input[@id = 'input-firstname']"), registrationData.getFirstName());
        input(By.xpath("//input[@id = 'input-lastname']"), registrationData.getLastName());
        input(By.xpath("//input[@id = 'input-email']"), registrationData.getEmail());
        input(By.xpath("//input[@id = 'input-telephone']"), registrationData.getPhone());
        input(By.xpath("//input[@id = 'input-password']"), registrationData.getPassword());
        input(By.xpath("//input[@id = 'input-confirm']"), registrationData.getRePassword());
        radio(By.xpath("//label[@class = 'radio-inline']/input"), registrationData.getSubscribe());
        check(By.xpath("//input[@name = 'agree']"), registrationData.getPolicy());
        click(By.xpath("//input[@value = 'Continue']"));
    }

    public String getSuccessLinkTitle() {
        return wait.until(visibilityOfElementLocated(By.xpath("//a[contains(@href, 'route=account/success')]"))).getText();
    }

    public String getRegisterLinkTitle() {
        return wait.until(visibilityOfElementLocated(By.xpath("//ul[@class = 'breadcrumb']//a[contains(@href, 'route=account/register')]"))).getText();
    }
}
