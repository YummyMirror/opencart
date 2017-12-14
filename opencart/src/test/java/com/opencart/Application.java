package com.opencart;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.List;
import static java.util.concurrent.TimeUnit.SECONDS;
import static org.openqa.selenium.support.ui.ExpectedConditions.*;

public class Application {
    private WebDriver wd;
    private WebDriverWait wait;

    public void init() {
        wd = new ChromeDriver();
        wd.manage().timeouts().implicitlyWait(5, SECONDS);
        wait = new WebDriverWait(wd, 10);
        wd.manage().window().maximize();
        wd.navigate().to("http://localhost/opencart/");
        wait.until(visibilityOfElementLocated(By.xpath("//a[text() = 'Your Store']")));
    }

    public String randomEmailGeneration() {
        List<String> letters = Arrays.asList("a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m",
                                            "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z");
        int randomInt = new SecureRandom().nextInt(100);
        int randomInt2 = new SecureRandom().nextInt(70);
        int randomLetter = new SecureRandom().nextInt(letters.size() - 1);
        return letters.get(randomLetter) + randomInt + letters.get(randomLetter) + randomInt2 + "@gmail.com";
    }

    public void fillRegistrationForm() {
        input(By.xpath("//input[@id = 'input-firstname']"), "Kobe");
        input(By.xpath("//input[@id = 'input-lastname']"), "Bryant");
        input(By.xpath("//input[@id = 'input-email']"), randomEmailGeneration());
        input(By.xpath("//input[@id = 'input-telephone']"), "12345");
        input(By.xpath("//input[@id = 'input-password']"), "password");
        input(By.xpath("//input[@id = 'input-confirm']"), "password");
        check(By.xpath("//input[@name = 'agree']"));
        click(By.xpath("//input[@value = 'Continue']"));
        wait.until(visibilityOfAllElementsLocatedBy(By.xpath("//p[contains(text(), 'account has been successfully created')]")));
        click(By.xpath("//div[@class = 'pull-right']/a"));
        wait.until(visibilityOfAllElementsLocatedBy(By.xpath("//div[@id = 'content']/h2[text() = 'My Account']")));
    }

    public void check(By locator) {
        wd.findElement(locator).click();
    }

    public void input(By locator, String value) {
        wd.findElement(locator).click();
        wd.findElement(locator).clear();
        wd.findElement(locator).sendKeys(value);
    }

    public void clickRegister() {
        click(By.xpath("//a[contains(@href, 'register')]"));
        wait.until(visibilityOfAllElementsLocatedBy(By.xpath("//h1[text() = 'Account']")));
    }

    public void clickMyAccount() {
        click(By.xpath("//a[@title = 'My Account']"));
    }

    public void click(By locator) {
        wait.until(elementToBeClickable(locator)).click();
    }

    public void tearDown() {
        wd.quit();
        if (wd != null)
            wd = null;
    }
}
