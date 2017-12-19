package com.opencart.generators;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.opencart.models.AdminCategoryData;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Category {
    public static void main(String[] args) throws IOException {
        int number = Integer.parseInt(args[0]);
        String filePath = args[1];
        String fileName = args[2];
        String format = args[3];
        File file = new File(filePath + fileName + "." + format);

        List<AdminCategoryData> categoryDataList = createCatData(number);
        saveJson(file, categoryDataList);
    }

    public static void saveJson(File file, List<AdminCategoryData> categoryData) throws IOException {
        if (file.exists())
            file.delete();
        Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
        String json = gson.toJson(categoryData);
        FileWriter writer = new FileWriter(file);
        writer.write(json);
        writer.flush();
        writer.close();
    }

    public static List<AdminCategoryData> createCatData(int number) {
        List<AdminCategoryData> list = new ArrayList<AdminCategoryData>(0);
        for (int i = 0; i < number; i++) {
            list.add(new AdminCategoryData().setCatName("CatName")
                                            .setCatDesc("CatDesc")
                                            .setMetaTagTitle("MetaTagTitle")
                                            .setMetaTagDesc("MetaTagDesc")
                                            .setMetaTagKeywords("MetaTagKeywords")
                                            .setParent("")
                                            .setTop(true)
                                            .setColumns("1")
                                            .setSortOrder("1")
                                            .setStatus(true)
                                            .setSeoKeywords("abc")
                                            .setDesignLayout("Account"));
        }
        return list;
    }
}
