package com.opencart.pages;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.*;
import java.lang.reflect.Type;
import java.security.SecureRandom;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.openqa.selenium.support.ui.ExpectedConditions.*;

public class PageBase {
    protected WebDriver wd;
    protected WebDriverWait wait;
    protected Actions actions;
    protected JavascriptExecutor js;
    protected String fileWithCookies = "src/test/resources/cookies/adminCookies.json";

    //Constructor
    public PageBase(WebDriver wd, WebDriverWait wait, Actions actions) {
        this.wd = wd;
        this.wait = wait;
        this.actions = actions;
        this.js = (JavascriptExecutor) wd;
    }

    public void check(By locator, Boolean value) {
        WebElement element = wd.findElement(locator);
        if (value.equals(true)) {
            if (element.getAttribute("checked") == null || element.getAttribute("checked").equals("false"))
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

    public void inputWithHints(By locator, String value, By hintsLocator) {
        if (value != null) {
            String currentValue = wd.findElement(locator).getAttribute("value");
            if (!value.equals(currentValue)) {
                wd.findElement(locator).sendKeys(value);
                List<WebElement> hints = wait.until(visibilityOfAllElementsLocatedBy(hintsLocator));
                if (hints.size() > 1)
                    hints.stream().skip(1).findAny().get().click();
                else
                    hints.stream().findAny().get().click();
            }
        }
    }

    public void textArea(By locator, String value) {
        if (value != null) {
            String currentValue = wd.findElement(locator).getText();
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

    public void select(By locator, String value) {
        if (value != null) {
            new Select(wd.findElement(locator)).selectByVisibleText(value);
        }
    }

    public void selectDate(By locator, int date) {
        click(locator);
        WebElement widget = wait.until(visibilityOf(wd.findElement(By.xpath("//div[contains(@class, 'datetimepicker-widget')]"))));
        List<WebElement> cells = widget.findElements(By.xpath(".//tbody//td[@class = 'day' or @class = 'day active today']"));
        if (date > 0 && date <= cells.size()) {
            for (int i = 0; i < cells.size(); i++) {
                if (date == Integer.parseInt(cells.get(i).getText())) {
                    cells.get(i).click();
                    wait.until(attributeContains(widget, "style", "display: none"));
                    break;
                }
            }
        }
    }

    public void openNewWindow(String url) {
        Set<String> currentWindows = wd.getWindowHandles();
        js.executeScript("window.open('" + url + "');");
        String newWindow  = wait.until(waitNewWindow(currentWindows));
        wd.switchTo().window(newWindow);
    }

    public void closeCurrentWindow() {
        String currentWindow = wd.getWindowHandle();
        Set<String> currentWindows = wd.getWindowHandles();
        String newWindow = currentWindows.stream().filter(w -> !w.equals(currentWindow)).findAny().get();
        wd.close();
        wd.switchTo().window(newWindow);
    }

    public void loginToAdminSide(String username, String password) {
        if (areElementsPresent(By.xpath("//button[text() = ' Login']"))) {
            input(By.xpath("//input[@id = 'input-username']"), username);
            input(By.xpath("//input[@id = 'input-password']"), password);
            click(By.xpath("//div[@class = 'text-right']/button[@type = 'submit']"));
            wait.until(visibilityOfElementLocated(By.xpath("//h1[text() = 'Dashboard']")));
        }
    }

    public ExpectedCondition<String> waitNewWindow(Set<String> oldWindows) {
        return wd -> {
            Set<String> handles = wd.getWindowHandles();
            handles.removeAll(oldWindows);
            return handles.size() > 0 ? handles.stream().findAny().get() : null;
        };
    }

    public void refreshPage() {
        wd.navigate().refresh();
    }

    public void changePhoto(By locator) {
        click(locator);
        click(By.xpath("//div[@class = 'popover-content']/button[@id = 'button-image']"));
        wait.until(visibilityOfElementLocated(By.xpath("//div[@id = 'modal-image']//div[@class = 'modal-content']")));
        List<WebElement> images = wd.findElements(By.xpath("//div[@class = 'col-sm-3 col-xs-6 text-center']/a"));
        if (images.size() > 0) {
            int randomImage = new SecureRandom().nextInt(images.size());
            images.get(randomImage).click();
            wait.until(attributeContains(By.xpath("//div[@id = 'modal-image']"), "style", "none"));
        }
    }

    public void openAdminSideAndLogin() {
        openNewWindow("http://localhost/opencart/admin/");
        loginToAdminSide("admin", "admin");
        wait.until(visibilityOfElementLocated(By.xpath("//h1[text() = 'Dashboard']")));
    }

    public void back() {
        wd.navigate().back();
    }

    public Boolean areElementsPresent(By locator) {
        return wd.findElements(locator).size() > 0;
    }

    public Set<Cookie> getCookies() {
        return wd.manage().getCookies();
    }

    public void saveCookies(Set<Cookie> cookies) throws IOException {
        if (cookies.size() > 0) {
            File file = new File(fileWithCookies);
            if (file.exists())
                file.delete();
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String json = gson.toJson(cookies);
            FileWriter writer = new FileWriter(file);
            writer.write(json);
            writer.flush();
            writer.close();
        }
    }

    public Set<Cookie> getCookiesFromFile() throws IOException {
        File file = new File(fileWithCookies);
        FileReader reader = new FileReader(file);
        BufferedReader buffReader = new BufferedReader(reader);
        String line = buffReader.readLine();
        String json = "";
        while (line != null) {
            json += line;
            line = buffReader.readLine();
        }
        Gson gson = new Gson();
        Type colType = new TypeToken<Set<Cookie>>(){}.getType();
        buffReader.close();
        reader.close();
        return gson.fromJson(json, colType);
    }

    public void addCookies() throws IOException {
        Set<Cookie> cookies = getCookiesFromFile();
        for (Cookie cookie : cookies) {
            wd.manage().addCookie(cookie);
        }
    }

    public List<LogEntry> getAllBrowserLogs() {
        return wd.manage().logs().get(LogType.BROWSER).getAll();
    }

    public List<LogEntry> getSevereBrowserLogs() {
        return getAllBrowserLogs().stream().filter(l -> l.getLevel().toString().equals("SEVERE")).collect(Collectors.toList());
    }
}
