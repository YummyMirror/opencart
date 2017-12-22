package com.opencart.tests;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.opencart.models.AdminCategoryData;
import com.opencart.models.AdminProductData;
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

    @Test(enabled = true, dataProvider = "validProdDataJson", priority = 1)
    public void createProductTest(AdminProductData productData) {
        app.getAdminProductPage().openAdminSideAndLogin();
        app.getAdminNaviPage().openMenuItem("Catalog", "Products", "");
        assertEquals(app.getAdminNaviPage().getMenuItemHeaderTitle(), "Products", "Products menu item isn't opened!");

        Set<AdminProductData> productsBefore = app.getAdminProductPage().getProducts();
        app.getAdminProductPage().createProduct(productData);

        Set<AdminProductData> productsAfter = app.getAdminProductPage().getProducts();
        //Asserting by SIZEs
        assertEquals(productsAfter.size(), productsBefore.size() + 1, "Size of collections aren't equal!");
        //Asserting by COLLECTIONs
        productData.setId(productsAfter.stream().max((p1, p2) -> Integer.compare(p1.getId(), p2.getId())).get().getId());
        productsBefore.add(productData);
        assertEquals(productsAfter, productsBefore, "Collections aren't equal!");
    }
}
