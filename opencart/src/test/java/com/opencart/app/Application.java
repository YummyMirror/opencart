package com.opencart.app;

import com.opencart.pages.PublicNaviPage;
import com.opencart.pages.PublicRegPage;
import org.openqa.selenium.By;
import org.openqa.selenium.HasCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.support.ui.WebDriverWait;
import sun.plugin.dom.exception.BrowserNotSupportedException;
import static java.lang.System.setProperty;
import static java.util.concurrent.TimeUnit.SECONDS;
import static org.openqa.selenium.support.ui.ExpectedConditions.*;

public class Application {
    private WebDriver wd;
    private WebDriverWait wait;
    private String browser;
    private PublicRegPage publicRegPage;
    private PublicNaviPage publicNaviPage;
    //Constructor
    public Application(String browser) {
        this.browser = browser;
    }

    public void init() {
        wd = getWebDriver(browser);
        wd.manage().timeouts().implicitlyWait(5, SECONDS);
        wait = new WebDriverWait(wd, 10);
        wd.manage().window().maximize();
        wd.navigate().to("http://localhost/opencart/");
        wait.until(visibilityOfElementLocated(By.xpath("//a[text() = 'Your Store']")));
        //Delegates
        publicRegPage = new PublicRegPage(wd, wait);
        publicNaviPage = new PublicNaviPage(wd, wait);

        System.out.println(((HasCapabilities) wd).getCapabilities());
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

    public PublicNaviPage getPublicNaviPage() {
        return publicNaviPage;
    }

    public WebDriver getWebDriver(String browser) {
        switch (browser) {
            case "CHROME":
                wd = new ChromeDriver();
                break;
            case "FIREFOX":
                setProperty("webdriver.gecko.driver", "F:\\Private\\Programs\\geckodriver.exe");
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.setLegacy(false);
                firefoxOptions.setCapability(CapabilityType.PAGE_LOAD_STRATEGY, "eager");
                wd = new FirefoxDriver(firefoxOptions);
                break;
            default:
                throw new BrowserNotSupportedException("Incorrect browser type input!");
        }
        return wd;
    }
}
