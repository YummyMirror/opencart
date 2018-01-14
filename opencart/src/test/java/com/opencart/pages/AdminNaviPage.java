package com.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.log4testng.Logger;

import java.util.List;
import static org.openqa.selenium.support.ui.ExpectedConditions.*;

public class AdminNaviPage extends PageBase {
    //Constructor
    public AdminNaviPage(WebDriver wd, WebDriverWait wait, Actions actions) {
        super(wd, wait, actions);
    }

    public String getAdminDashboardTitle() {
        return wait.until(visibilityOfElementLocated(By.xpath("//h1[text() = 'Dashboard']"))).getText();
    }

    public void openMenuItem(String itemName, String subItemName, String subSubItemName) {
        for (int i = 0; i < getMenuItems().size(); i++) {
            List<WebElement> menuItems = getMenuItems();
            if (itemName.equals(menuItems.get(i).getText().replace(".", ""))) {
                if (menuItems.get(i).getAttribute("class").equals("parent collapsed"))
                    menuItems.get(i).click();

                List<WebElement> subItems = getSubItems(getMenuItems().get(i));
                for (int j = 0; j < subItems.size(); j++) {
                    List<WebElement> subItems2 = getSubItems(getMenuItems().get(i));
                    if (subItemName.equals(subItems2.get(j).getText())) {
                        if (subItems2.get(j).getAttribute("class").contains("collapsed") || subItems2.get(j).getAttribute("href").contains("token"))
                            subItems2.get(j).click();

                        List<WebElement> subMenuItems = getSubItems(getMenuItems().get(i));
                        List<WebElement> subSubItems = getSubItems(subMenuItems.get(j));
                        for (int s = 0; s < subSubItems.size(); s++) {
                            List<WebElement> subItems3 = getSubItems(getMenuItems().get(i));
                            List<WebElement> subSubItems3 = getSubItems(subItems3.get(j));
                            if (subSubItemName.equals(subSubItems3.get(s).getText())) {
                                subSubItems3.get(s).click();
                                wait.until(visibilityOfElementLocated(By.xpath("//h1[text() = '" + subSubItemName + "']")));
                            }
                        }
                    }
                }
            }
        }
    }

    public List<WebElement> getMenuItems() {
        return wait.until(visibilityOfAllElementsLocatedBy(By.xpath("//ul[@id = 'menu']/li/a")));
    }

    public List<WebElement> getSubItems(WebElement parent) {
        return parent.findElements(By.xpath("./../ul/li/a"));
    }

    public String getMenuItemHeaderTitle() {
        return wait.until(visibilityOfElementLocated(By.xpath("//h1"))).getText();
    }
}
