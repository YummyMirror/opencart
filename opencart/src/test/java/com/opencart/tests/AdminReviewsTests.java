package com.opencart.tests;

import com.opencart.annotations.DataSource;
import com.opencart.dataProviders.AllDataProviders;
import com.opencart.listeners.RetryAnalyzer;
import com.opencart.models.AdminReviewData;
import org.openqa.selenium.By;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.util.Set;
import static org.testng.Assert.*;

public class AdminReviewsTests extends TestBase {
    @BeforeMethod
    public void precondition() {
        if (!app.getAdminProductPage().areElementsPresent(By.xpath("//img[@title = 'OpenCart']")))
            app.getAdminProductPage().openAdminSideAndLogin();
        if (!app.getAdminProductPage().areElementsPresent(By.xpath("//a[contains(@href, 'catalog/review/add')]"))) {
            app.getAdminNaviPage().openMenuItem("Catalog", "Reviews", "");
            assertEquals(app.getAdminNaviPage().getMenuItemHeaderTitle(), "Reviews", "Reviews menu item isn't opened!");
        }
    }

    @DataSource(value = "src/test/resources/dataProviders/validReviewData.json", format = DataSource.Format.JSON)
    @Test(priority = 1, groups = {"criticalPath"}, dataProviderClass = AllDataProviders.class, dataProvider = "reviewData", retryAnalyzer = RetryAnalyzer.class)
    public void createReviewTest(AdminReviewData reviewData) {
        Set<AdminReviewData> reviewsBefore = app.getAdminReviewPage().getReviews();

        app.getAdminReviewPage().createReview(reviewData);

        Set<AdminReviewData> reviewsAfter = app.getAdminReviewPage().getReviews();

        //Asserting by SIZEs
        assertEquals(reviewsAfter.size(), reviewsBefore.size() + 1, "Size of collections aren't equal!");
        //Asserting by COLLECTIONs
        reviewData.setId(reviewsAfter.stream().max((r1, r2) -> Integer.compare(r1.getId(), r2.getId())).get().getId());
        reviewsBefore.add(reviewData);
        assertEquals(reviewsAfter, reviewsBefore, "Collections aren't equal!");
    }
}
