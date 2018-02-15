package com.opencart.tests;

import com.opencart.annotations.DataSource;
import com.opencart.dataProviders.AllDataProviders;
import com.opencart.listeners.RetryAnalyzer;
import com.opencart.models.AdminProductData;
import org.openqa.selenium.By;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.io.IOException;
import java.util.Set;
import static org.testng.Assert.*;

public class AdminProductsTests extends TestBase{
    @BeforeMethod
    public void precondition() throws IOException {
        if (!app.getAdminProductPage().areElementsPresent(By.xpath("//img[@title = 'OpenCart']")))
            app.getAdminProductPage().openAdminSideAndLogin();
        if (!app.getAdminProductPage().areElementsPresent(By.xpath("//a[contains(@href, 'catalog/product/add')]"))) {
            app.getAdminNaviPage().openMenuItem("Catalog", "Products", "");
            assertEquals(app.getAdminNaviPage().getMenuItemHeaderTitle(), "Products", "Products menu item isn't opened!");
        }
    }

    @DataSource(value = "src/test/resources/dataProviders/validProductData.json", format = DataSource.Format.JSON)
    @Test(priority = 0, groups = {"smoke"}, dataProviderClass = AllDataProviders.class, dataProvider = "productData", retryAnalyzer = RetryAnalyzer.class)
    public void createProductTest(AdminProductData productData) {
        Set<AdminProductData> productsBefore = app.getAdminProductPage().getProducts();
        app.getAdminProductPage().createProduct(productData);
        //Asserting by Browser's logs
        //assertEquals(app.getAdminProductPage().getSevereBrowserLogs().size(), 0);

        Set<AdminProductData> productsAfter = app.getAdminProductPage().getProducts();
        //Asserting by SIZEs
        assertEquals(productsAfter.size(), productsBefore.size() + 1, "Size of collections aren't equal!");
        //Asserting by COLLECTIONs
        productData.setId(productsAfter.stream().max((p1, p2) -> Integer.compare(p1.getId(), p2.getId())).get().getId());
        productsBefore.add(productData);
        assertEquals(productsAfter, productsBefore, "Collections aren't equal!");
    }

    @Test(priority = 1, groups = {"criticalPath"}, retryAnalyzer = RetryAnalyzer.class)
    public void deleteProductTest() {
        Set<AdminProductData> productsBefore = app.getAdminProductPage().getProducts();
        AdminProductData deletedProduct = productsBefore.stream().findAny().get();
        app.getAdminProductPage().deleteProduct(deletedProduct);
        //Asserting by Browser's logs
        //assertEquals(app.getAdminProductPage().getSevereBrowserLogs().size(), 0);

        Set<AdminProductData> productsAfter = app.getAdminProductPage().getProducts();
        //Asserting by SIZEs
        assertEquals(productsAfter.size(), productsBefore.size() - 1, "Size of collections aren't equal!");
        //Asserting by COLLECTIONs
        productsBefore.remove(deletedProduct);
        assertEquals(productsAfter, productsBefore, "Collections aren't equal!");
    }

    @DataSource(value = "src/test/resources/dataProviders/validProductDataEdit.json", format = DataSource.Format.JSON)
    @Test(priority = 2, groups = {"regression"}, dataProviderClass = AllDataProviders.class, dataProvider = "productData", retryAnalyzer = RetryAnalyzer.class)
    public void editProductTest(AdminProductData productData) {
        Set<AdminProductData> productsBefore = app.getAdminProductPage().getProducts();
        AdminProductData editedProduct = productsBefore.stream().findAny().get();
        productData.setId(editedProduct.getId());
        app.getAdminProductPage().editProduct(productData);
        //Asserting by Browser's logs
        //assertEquals(app.getAdminProductPage().getSevereBrowserLogs().size(), 0);

        Set<AdminProductData> productsAfter = app.getAdminProductPage().getProducts();
        //Asserting by SIZEs
        assertEquals(productsAfter.size(), productsBefore.size(), "Size of collections aren't equal!");
        //Asserting by COLLECTIONs
        productsBefore.remove(editedProduct);
        productsBefore.add(productData);
        assertEquals(productsAfter, productsBefore, "Collections aren't equal!");
    }

    @Test(priority = 2, groups = {"regression"}, retryAnalyzer = RetryAnalyzer.class)
    public void compareOutsideVsInsideData() {
        Set<AdminProductData> products = app.getAdminProductPage().getProducts();
        AdminProductData outsideData = products.stream().findAny().get();
        AdminProductData insideData = app.getAdminProductPage().getInsideData(outsideData);

        //Asserting by both Name and Model
        assertEquals(insideData.getName(), outsideData.getName(), "Names aren't equal!");
        assertEquals(insideData.getModel(), outsideData.getModel(), "Models aren't equal!");
    }
}
