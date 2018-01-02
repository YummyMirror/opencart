package com.opencart.tests;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.opencart.listeners.MyRetryAnalyzer;
import com.opencart.models.AdminReviewData;
import org.openqa.selenium.By;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.io.*;
import java.lang.reflect.Type;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import static org.testng.Assert.*;

public class AdminReviewsTests extends TestBase {
    @DataProvider
    public Iterator<Object[]> validReviewDataJson() throws IOException {
        File file = new File("src/test/resources/dataProviders/validReviewData.json");
        FileReader reader = new FileReader(file);
        BufferedReader buffReader = new BufferedReader(reader);
        String line = buffReader.readLine();
        String json = "";
        while (line != null) {
            json += line;
            line = buffReader.readLine();
        }
        Gson gson = new Gson();
        Type colType = new TypeToken<List<AdminReviewData>>() { }.getType();
        List<AdminReviewData> list = gson.fromJson(json, colType);
        buffReader.close();
        reader.close();
        return list.stream().map(r -> new Object[] {r}).iterator();
    }

    @BeforeMethod
    public void precondition() {
        if (!app.getAdminProductPage().areElementsPresent(By.xpath("//img[@title = 'OpenCart']")))
            app.getAdminProductPage().openAdminSideAndLogin();
        if (!app.getAdminProductPage().areElementsPresent(By.xpath("//a[contains(@href, 'catalog/review/add')]"))) {
            app.getAdminNaviPage().openMenuItem("Catalog", "Reviews", "");
            assertEquals(app.getAdminNaviPage().getMenuItemHeaderTitle(), "Reviews", "Reviews menu item isn't opened!");
        }
    }

    @Test(enabled = true, dataProvider = "validReviewDataJson", priority = 1, retryAnalyzer = MyRetryAnalyzer.class)
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
