package com.opencart.app;

import com.opencart.pages.*;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.MultiPartEmail;
import org.openqa.selenium.By;
import org.openqa.selenium.HasCapabilities;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.log4testng.Logger;
import sun.plugin.dom.exception.BrowserNotSupportedException;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;
import java.util.Properties;
import static java.lang.System.setProperty;
import static java.util.concurrent.TimeUnit.SECONDS;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

public class Application {
    private WebDriver wd;
    private WebDriverWait wait;
    private Actions actions;
    private JavascriptExecutor js;
    private Properties properties;
    private PublicRegPage publicRegPage;
    private PublicNaviPage publicNaviPage;
    private PublicLoginPage publicLoginPage;
    private AdminNaviPage adminNaviPage;
    private AdminCategoryPage adminCategoryPage;
    private AdminProductPage adminProductPage;
    private AdminReviewPage adminReviewPage;
    private ITestResult result;
    private String fileWithResults = "src/test/resources/results/report.csv";
    private FileWriter writer = null;
    private static final Logger LOGGER = Logger.getLogger(Application.class);

    public void init(String browser) throws IOException {
        initProperties();
        wd = getWebDriver(browser);
        wd.manage().timeouts().implicitlyWait(5, SECONDS);
        wait = new WebDriverWait(wd, 10);
        actions = new Actions(wd);
        js = (JavascriptExecutor) wd;
        wd.manage().window().maximize();
        wd.navigate().to(properties.getProperty("baseUrl"));
        wait.until(visibilityOfElementLocated(By.xpath("//a[text() = 'Your Store']")));
        //Delegates
        publicRegPage = new PublicRegPage(wd, wait, actions);
        publicNaviPage = new PublicNaviPage(wd, wait, actions);
        publicLoginPage = new PublicLoginPage(wd, wait, actions);
        adminNaviPage = new AdminNaviPage(wd, wait, actions);
        adminCategoryPage = new AdminCategoryPage(wd, wait, actions);
        adminProductPage = new AdminProductPage(wd, wait, actions);
        adminReviewPage = new AdminReviewPage(wd, wait, actions);

        LOGGER.info(((HasCapabilities) wd).getCapabilities());
    }

    public void tearDown() throws IOException, EmailException {
        wd.quit();
        if (wd != null)
            wd = null;
        result = Reporter.getCurrentTestResult();
        //sendEmailWithStatuses(result);
    }

    //Getters of delegates
    public PublicRegPage getPublicRegPage() {
        return publicRegPage;
    }

    public PublicNaviPage getPublicNaviPage() {
        return publicNaviPage;
    }

    public PublicLoginPage getPublicLoginPage() {
        return publicLoginPage;
    }

    public AdminNaviPage getAdminNaviPage() {
        return adminNaviPage;
    }

    public AdminCategoryPage getAdminCategoryPage() {
        return adminCategoryPage;
    }

    public AdminProductPage getAdminProductPage() {
        return adminProductPage;
    }

    public AdminReviewPage getAdminReviewPage() {
        return adminReviewPage;
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

    public Properties getProperties() {
        return properties;
    }

    public void initProperties() throws IOException {
        properties = new Properties();
        File fileWithLocalProperties = new File("src/test/resources/properties/local.properties");
        FileReader reader = new FileReader(fileWithLocalProperties);
        properties.load(reader);
    }

    public void sendEmailWithStatuses(ITestResult result) throws IOException, EmailException {
        saveResultsInFile(result);
        try {
            //Create the attachment
            EmailAttachment attachment = new EmailAttachment();
            attachment.setPath(fileWithResults);
            attachment.setDisposition(EmailAttachment.ATTACHMENT);
            attachment.setDescription("Report statuses");
            attachment.setName("report.csv");
            //Create the email message
            MultiPartEmail email = new MultiPartEmail();
            email.setHostName("smtp.gmail.com");
            email.setSmtpPort(465);
            email.setAuthenticator(new DefaultAuthenticator("tester2.exposit@gmail.com", "Expotester256"));
            email.setSSLOnConnect(true);
            email.addTo("anatoli.anukevich@exposit.com", "");
            email.setFrom("tester2.exposit@gmail.com", "Anatoli");
            email.setSubject("Test Report");
            email.setMsg("Here is the file with Test Report with statuses of executed Test cases in the attachment section");
            email.attach(attachment);
            email.send();
            LOGGER.info("Email is successfully sent");
        } catch (EmailException e) {
            e.printStackTrace();
            LOGGER.error("Email is not sent!");
        }
    }

    public void saveResultsInFile(ITestResult result) throws IOException {
        Collection<ITestNGMethod> passed = result.getTestContext().getPassedTests().getAllMethods();
        Collection<ITestNGMethod> failed = result.getTestContext().getFailedTests().getAllMethods();
        Collection<ITestNGMethod> skipped = result.getTestContext().getSkippedTests().getAllMethods();
        File file = new File(fileWithResults);
        if (file.exists() && file.isFile())
            file.delete();
        try {
            writer = new FileWriter(file);
            writer.write("Statuses of executed Test cases: \n\n");
            passed.forEach(p -> {
                try {
                    writer.write("Passed TC: " + p.getMethodName() + " (priority - " + p.getPriority() + ")\n");
                    LOGGER.info("Passed TCs is written");
                } catch (IOException e) {
                    e.printStackTrace();
                    LOGGER.error("Passed TCs is not written");
                }
            });
            writer.write("\n");
            failed.forEach(f -> {
                try {
                    writer.write("Failed TC: " + f.getMethodName() + " (priority - " + f.getPriority() + ")\n");
                    LOGGER.info("Failed TCs is written");
                } catch (IOException e) {
                    e.printStackTrace();
                    LOGGER.error("Failed TCs is not written");
                }
            });
            writer.write("\n");
            skipped.forEach(s -> {
                try {
                    writer.write("Skipped TC: " + s.getMethodName() + " (priority - " + s.getPriority() + ")\n");
                    LOGGER.info("Skipped TCs is written");
                } catch (IOException e) {
                    e.printStackTrace();
                    LOGGER.error("Skipped TCs is not written");
                }
            });
            LOGGER.info("Data is successfully saved to the file");
        } catch (IOException e) {
            e.printStackTrace();
            LOGGER.error("Data is not save to the file!");
        } finally {
            writer.flush();
            writer.close();
        }
    }
}
