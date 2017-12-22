package com.opencart.tests;

import com.opencart.models.AdminProductData;
import org.testng.annotations.Test;
import java.util.Set;
import static org.testng.Assert.*;

public class AdminProductsTests extends TestBase{
    @Test
    public void createProductTest() {
        app.getAdminProductPage().openAdminSideAndLogin();

        app.getAdminNaviPage().openMenuItem("Catalog", "Products", "");
        assertEquals(app.getAdminNaviPage().getMenuItemHeaderTitle(), "Products", "Products menu item isn't opened!");

        Set<AdminProductData> productsBefore = app.getAdminProductPage().getProducts();
        AdminProductData productData = new AdminProductData().setName("Name")
                                                             .setMetaTagTitle("Title")
                                                             .setModel("Model")
                                                             .setDateAvailable(5);
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
