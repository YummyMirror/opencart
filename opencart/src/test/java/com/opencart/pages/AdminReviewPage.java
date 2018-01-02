package com.opencart.pages;

import com.opencart.models.AdminReviewData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import static org.openqa.selenium.support.ui.ExpectedConditions.*;

public class AdminReviewPage extends PageBase {
    //Constructor
    public AdminReviewPage(WebDriver wd, WebDriverWait wait, Actions actions) {
        super(wd, wait, actions);
    }

    public Set<AdminReviewData> getReviews() {
        Set<AdminReviewData> reviews = new HashSet<AdminReviewData>(0);
        if (areElementsPresent(By.xpath("//a[text() = '|<']")))
            click(By.xpath("//a[text() = '|<']"));
        for (int i = 1; i <= getPagination(); i++) {
            List<WebElement> elements = wait.until(visibilityOfAllElementsLocatedBy(By.xpath("//form[@id = 'form-review']//tbody/tr")));
            for (WebElement element : elements) {
                List<WebElement> cells = element.findElements(By.xpath("./td"));
                int id = Integer.parseInt(cells.get(0).findElement(By.xpath("./input[@name = 'selected[]']")).getAttribute("value"));
                String product = cells.get(1).getText();
                String author = cells.get(2).getText();
                int rating = Integer.parseInt(cells.get(3).getText());
                AdminReviewData reviewData = new AdminReviewData().setId(id)
                                                                  .setProduct(product)
                                                                  .setAuthor(author)
                                                                  .setRating(rating);
                reviews.add(reviewData);
            }
            if (areElementsPresent(By.xpath("//a[text() = '>']")))
                click(By.xpath("//a[text() = '>']"));
        }
        return reviews;
    }

    public int getPagination() {
        String text = wd.findElement(By.xpath("//div[@class = 'row']/div[contains(@class, 'text-right')]")).getText();
        return Integer.parseInt(text.substring(text.length() - 9).replaceAll("[() a-zA-Z]", ""));
    }

    public void createReview(AdminReviewData reviewData) {
        click(By.xpath("//a[contains(@href, 'catalog/review/add')]"));
        wait.until(visibilityOfElementLocated(By.xpath("//h3[contains(text(), 'Add Review')]")));
        input(By.xpath("//input[@id = 'input-author']"), reviewData.getAuthor());
        inputWithHints(By.xpath("//input[@id = 'input-product']"), reviewData.getProduct(), By.xpath("//div/ul[contains(@style, 'block')]/li"));
        textArea(By.xpath("//textarea[@id = 'input-text']"), reviewData.getText());
        radioGroup(By.xpath("//input[@name = 'rating']"), reviewData.getRating());
        click(By.xpath("//button[@data-original-title = 'Save']"));
        wait.until(visibilityOfElementLocated(By.xpath("//div[contains(@class, 'alert-success')]")));
    }
}
