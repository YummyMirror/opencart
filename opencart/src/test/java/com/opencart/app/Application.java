package com.opencart.app;

import com.opencart.pages.PublicRegPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import static java.util.concurrent.TimeUnit.SECONDS;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

public class Application {
    private WebDriver wd;
    private WebDriverWait wait;
    private PublicRegPage publicRegPage;

    public void init() {
        wd = new ChromeDriver();
        wd.manage().timeouts().implicitlyWait(5, SECONDS);
        wait = new WebDriverWait(wd, 10);
        wd.manage().window().maximize();
        wd.navigate().to("http://localhost/opencart/");
        wait.until(visibilityOfElementLocated(By.xpath("//a[text() = 'Your Store']")));
        //Delegates
        publicRegPage = new PublicRegPage(wd, wait);
    }

    public void tearDown() {
        wd.quit();
        if (wd != null)
            wd = null;
    }

    //Getters of delegates
    public PublicRegPage getPublicRegPage() {
        return publicRegPage;
    }
}
