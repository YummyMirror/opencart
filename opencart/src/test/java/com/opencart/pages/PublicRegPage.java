package com.opencart.pages;

import com.opencart.models.PublicRegData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.List;
import static org.openqa.selenium.support.ui.ExpectedConditions.*;

public class PublicRegPage extends PageBase {
    //Constructor
    public PublicRegPage(WebDriver wd, WebDriverWait wait) {
        super(wd, wait);
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

    public String randomEmailGeneration() {
        List<String> letters = Arrays.asList("a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m",
                                            "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z");
        int randomInt = new SecureRandom().nextInt(100);
        int randomInt2 = new SecureRandom().nextInt(70);
        int randomLetter = new SecureRandom().nextInt(letters.size() - 1);
        return letters.get(randomLetter) + randomInt + letters.get(randomLetter) + randomInt2 + "@gmail.com";
    }
}
