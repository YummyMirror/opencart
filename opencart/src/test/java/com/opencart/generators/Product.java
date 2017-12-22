package com.opencart.generators;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.opencart.models.AdminProductData;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Product {
    public static void main(String[] args) throws IOException {
        int number = Integer.parseInt(args[0]);
        String filePath = args[1];
        String fileName = args[2];
        String format = args[3];
        File file = new File(filePath + fileName + "." + format);

        List<AdminProductData> productDataList = createProdData(number);
        saveJson(file, productDataList);
    }

    public static void saveJson(File file, List<AdminProductData> productData) throws IOException {
        if (file.exists())
            file.delete();
        Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
        String json = gson.toJson(productData);
        FileWriter writer = new FileWriter(file);
        writer.write(json);
        writer.flush();
        writer.close();
    }

    public static List<AdminProductData> createProdData(int number) {
        List<AdminProductData> list = new ArrayList<AdminProductData>(0);
        for (int i = 0; i < number; i++) {
            list.add(new AdminProductData().setName("ProductName")
                                           .setMetaTagTitle("Tag")
                                           .setModel("TestModel")
                                           .setDateAvailable(12));
        }
        return list;
    }
}
