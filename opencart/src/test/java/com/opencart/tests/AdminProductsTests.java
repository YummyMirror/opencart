package com.opencart.tests;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.opencart.listeners.MyRetryAnalyzer;
import com.opencart.models.AdminProductData;
import org.openqa.selenium.By;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import static org.testng.Assert.*;

public class AdminProductsTests extends TestBase{
    @DataProvider
    public Iterator<Object[]> validProdDataJson() throws IOException {
        File file = new File("src/test/resources/dataProviders/validProductData.json");
        FileReader reader = new FileReader(file);
        BufferedReader buffReader = new BufferedReader(reader);
        String line = buffReader.readLine();
        String json = "";
        while (line != null) {
            json += line;
            line = buffReader.readLine();
        }
        Gson gson = new Gson();
        Type collectionType = new TypeToken<List<AdminProductData>>(){}.getType();
        List<AdminProductData> list = gson.fromJson(json, collectionType);
        reader.close();
        buffReader.close();
        return list.stream().map(l -> new Object[] {l}).iterator();
    }

    @DataProvider
    public Iterator<Object[]> validProdDataEditJson() throws IOException {
        File file = new File("src/test/resources/dataProviders/validProductDataEdit.json");
        FileReader reader = new FileReader(file);
        BufferedReader buffReader = new BufferedReader(reader);
        String line = buffReader.readLine();
        String json = "";
        while (line != null) {
            json += line;
            line = buffReader.readLine();
        }
        Gson gson = new Gson();
        Type collectionType = new TypeToken<List<AdminProductData>>(){}.getType();
        List<AdminProductData> list = gson.fromJson(json, collectionType);
        reader.close();
        buffReader.close();
        return list.stream().map(l -> new Object[] {l}).iterator();
    }

    @BeforeMethod
    public void precondition() throws IOException {
        if (!app.getAdminProductPage().areElementsPresent(By.xpath("//img[@title = 'OpenCart']"))) {
            app.getAdminProductPage().openAdminSideAndLogin();
            if (!app.getAdminProductPage().areElementsPresent(By.xpath("//a[contains(@href, 'catalog/product/add')]"))) {
                app.getAdminNaviPage().openMenuItem("Catalog", "Products", "");
                assertEquals(app.getAdminNaviPage().getMenuItemHeaderTitle(), "Products", "Products menu item isn't opened!");
            }
        }
    }

    @Test(enabled = true, dataProvider = "validProdDataJson", priority = 0, retryAnalyzer = MyRetryAnalyzer.class)
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

    @Test(enabled = true, priority = 1, retryAnalyzer = MyRetryAnalyzer.class)
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

    @Test(enabled = true, dataProvider = "validProdDataEditJson", priority = 2, retryAnalyzer = MyRetryAnalyzer.class)
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

    @Test(enabled = true, priority = 2, retryAnalyzer = MyRetryAnalyzer.class)
    public void compareOutsideVsInsideData() {
        Set<AdminProductData> products = app.getAdminProductPage().getProducts();
        AdminProductData outsideData = products.stream().findAny().get();
        AdminProductData insideData = app.getAdminProductPage().getInsideData(outsideData);

        //Asserting by both Name and Model
        assertEquals(insideData.getName(), outsideData.getName(), "Names aren't equal!");
        assertEquals(insideData.getModel(), outsideData.getModel(), "Models aren't equal!");
    }
}
