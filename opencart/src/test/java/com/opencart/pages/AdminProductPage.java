package com.opencart.pages;

import com.opencart.models.AdminProductData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import static org.openqa.selenium.support.ui.ExpectedConditions.*;

public class AdminProductPage extends PageBase {
    //Constructor
    public AdminProductPage(WebDriver wd, WebDriverWait wait, Actions actions) {
        super(wd, wait, actions);
    }

    public void createProduct(AdminProductData productData) {
        click(By.xpath("//a[contains(@href, 'catalog/product/add')]"));
        wait.until(visibilityOfElementLocated(By.xpath("//h3[@class = 'panel-title']")));
        input(By.xpath("//input[@id = 'input-name1']"), productData.getName());
        input(By.xpath("//input[@id = 'input-meta-title1']"), productData.getMetaTagTitle());
        if (productData.getModel() != null && productData.getDateAvailable() > 0) {
            openProductTab(By.xpath("//a[@href = '#tab-data']"));
            input(By.xpath("//input[@id = 'input-model']"), productData.getModel());
            selectDate(By.xpath("//div[@class = 'input-group date']//button"), productData.getDateAvailable());
        }
        click(By.xpath("//button[@data-original-title = 'Save']"));
        wait.until(visibilityOfElementLocated(By.xpath("//div[contains(@class, 'alert-success')]")));
    }

    public void deleteProduct(AdminProductData productData) {
        selectProduct(productData.getId());
        click(By.xpath("//button[@data-original-title = 'Delete']"));
        wd.switchTo().alert().accept();
        wait.until(visibilityOfElementLocated(By.xpath("//div[contains(@class, 'alert-success')]")));
    }

    public void selectProduct(int product) {
        if (areElementsPresent(By.xpath("//a[text() = '|<']")))
            click(By.xpath("//a[text() = '|<']"));
        for (int i = 1; i <= getPagination(); i++) {
            if (areElementsPresent(By.xpath("//input[@value = '" + product + "']"))) {
                wd.findElement(By.xpath("//input[@value = '" + product + "']")).click();
                break;
            } else {
                if (areElementsPresent(By.xpath("//a[text() = '>']")))
                    click(By.xpath("//a[text() = '>']"));
            }
        }
    }

    public void openProductTab(By locator) {
        click(locator);
        wait.until(attributeContains(locator, "aria-expanded", "true"));
    }

    public int getPagination() {
        String text = wd.findElement(By.xpath("//div[@class = 'row']/div[contains(@class, 'text-right')]")).getText();
        return Integer.parseInt(text.substring(text.length() - 9).replaceAll("[() a-zA-Z]", ""));
    }

    public Set<AdminProductData> getProducts() {
        Set<AdminProductData> set = new HashSet<AdminProductData>(0);
        if (areElementsPresent(By.xpath("//a[text() = '|<']")))
            click(By.xpath("//a[text() = '|<']"));
        for (int i = 1; i <= getPagination(); i++) {
            List<WebElement> elements = wait.until(visibilityOfAllElementsLocatedBy(By.xpath("//table/tbody/tr")));
            for (WebElement element : elements) {
                List<WebElement> cells = element.findElements(By.xpath("./td"));
                int id = Integer.parseInt(cells.get(0).findElement(By.xpath("./input")).getAttribute("value"));
                String name = cells.get(2).getText();
                AdminProductData productData = new AdminProductData().setId(id).setName(name);
                set.add(productData);
            }
            if (areElementsPresent(By.xpath("//a[text() = '>']")))
                click(By.xpath("//a[text() = '>']"));
        }
        return set;
    }
}
