package com.opencart.pages;

import com.opencart.models.AdminCategoryData;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import static org.openqa.selenium.support.ui.ExpectedConditions.*;

public class AdminCategoryPage extends PageBase {
    //Constructor
    public AdminCategoryPage(WebDriver wd, WebDriverWait wait) {
        super(wd, wait);
    }

    public int getCategoryAmount() {
        return Integer.parseInt(wd.findElement(By.xpath("//div[@class = 'col-sm-6 text-right']")).getText().substring(19, 22).replace(" ", ""));
    }

    public void openAddNewCategoryPage() {
        click(By.xpath("//a[contains(@href, 'category/add')]"));
        wait.until(visibilityOfElementLocated(By.xpath("//h3[@class = 'panel-title']")));
    }

    public void createCategory(AdminCategoryData categoryData) {
        openAddNewCategoryPage();
        input(By.xpath("//input[@id = 'input-name1']"), categoryData.getCatName());
        textArea(By.xpath("//div[@class = 'note-editable panel-body']"), categoryData.getCatDesc());
        input(By.xpath("//input[@id = 'input-meta-title1']"), categoryData.getMetaTagTitle());
        textArea(By.xpath("//textarea[@id = 'input-meta-description1']"), categoryData.getMetaTagDesc());
        textArea(By.xpath("//textarea[@id = 'input-meta-keyword1']"), categoryData.getMetaTagKeywords());

        if (categoryData.getParent() != null || categoryData.getFilters() != null || categoryData.getTop() != null || categoryData.getColumns() != null ||
                categoryData.getSortOrder() != null || categoryData.getStatus() != null) {
            openCategoryTab(By.xpath("//a[@href = '#tab-data']"));
            inputWithHints(By.xpath("//input[@id = 'input-parent']"), categoryData.getParent(), By.xpath("//input[@id = 'input-parent']/../ul/li/a"));
            changePhoto(By.xpath("//a[@id = 'thumb-image']"));
            check(By.xpath("//input[@id = 'input-top']"), categoryData.getTop());
            input(By.xpath("//input[@id = 'input-column']"), categoryData.getColumns());
            input(By.xpath("//input[@id = 'input-sort-order']"), categoryData.getSortOrder());
            radio(By.xpath("//select[@id = 'input-status']/option"), categoryData.getStatus());
        }
        if (categoryData.getSeoKeywords() != null) {
            openCategoryTab(By.xpath("//a[@href = '#tab-seo']"));
            input(By.xpath("//input[contains(@name, 'category_seo')]"), categoryData.getSeoKeywords());
        }
        if (categoryData.getDesignLayout() != null) {
            openCategoryTab(By.xpath("//a[@href = '#tab-design']"));
            select(By.xpath("//select[contains(@name, 'category_layout')]"), categoryData.getDesignLayout());
        }
        click(By.xpath("//button[@data-original-title = 'Save']"));
        wait.until(visibilityOfElementLocated(By.xpath("//div[contains(@class, 'alert-success')]")));
    }

    public void openCategoryTab(By locator) {
        click(locator);
        wait.until(attributeContains(locator, "aria-expanded", "true"));
    }
}
