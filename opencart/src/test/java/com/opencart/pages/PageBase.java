package com.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.security.SecureRandom;
import java.util.List;
import java.util.Set;
import static org.openqa.selenium.support.ui.ExpectedConditions.*;

public class PageBase {
    protected WebDriver wd;
    protected WebDriverWait wait;
    protected Actions actions;
    protected JavascriptExecutor js;

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

    public void openAdminSideAndLogin(String url, String login, String password) {
        openNewWindow(url);
        loginToAdminSide(login, password);
    }

    public Boolean areElementsPresent(By locator) {
        return wd.findElements(locator).size() > 0;
    }
}
