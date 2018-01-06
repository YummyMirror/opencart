package com.opencart.dataProviders;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.opencart.models.*;
import org.testng.annotations.DataProvider;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class AllDataProviders {
    //Registration Data
    @DataProvider
    public static Iterator<Object[]> validRegDataCsv() throws IOException {
        List<Object[]> list = new ArrayList<Object[]>(0);
        File file = new File("src/test/resources/dataProviders/validRegData.csv");
        FileReader reader = new FileReader(file);
        BufferedReader buffReader = new BufferedReader(reader);
        String line = buffReader.readLine();
        while (line != null) {
            String[] splitData = line.split(";");
            list.add(new Object[] {new PublicRegData().setFirstName(splitData[0])
                                                      .setLastName(splitData[1])
                                                      .setEmail(splitData[2])
                                                      .setPhone(splitData[3])
                                                      .setPassword(splitData[4])
                                                      .setRePassword(splitData[5])
                                                      .setSubscribe(Boolean.parseBoolean(splitData[6]))
                                                      .setPolicy(Boolean.parseBoolean(splitData[7]))});
            line = buffReader.readLine();
        }
        reader.close();
        buffReader.close();
        return list.iterator();
    }

    @DataProvider
    public static Iterator<Object[]> validRegDataJson() throws IOException {
        File file = new File("src/test/resources/dataProviders/validRegData.json");
        FileReader reader = new FileReader(file);
        BufferedReader buffReader = new BufferedReader(reader);
        String line = buffReader.readLine();
        String json = "";
        while (line != null) {
            json += line;
            line = buffReader.readLine();
        }
        Gson gson = new Gson();
        Type collectionType = new TypeToken<List<PublicRegData>>(){}.getType();
        List<PublicRegData> list = gson.fromJson(json, collectionType);
        reader.close();
        buffReader.close();
        return list.stream().map(r -> new Object[] {r}).iterator();
    }

    @DataProvider
    public static Iterator<Object[]> invalidRegDataJson() throws IOException {
        File file = new File("src/test/resources/dataProviders/invalidRegData.json");
        FileReader reader = new FileReader(file);
        BufferedReader buffReader = new BufferedReader(reader);
        String line = buffReader.readLine();
        String json = "";
        while (line != null) {
            json += line;
            line = buffReader.readLine();
        }
        Gson gson = new Gson();
        Type collectionType = new TypeToken<List<PublicRegData>>(){}.getType();
        List<PublicRegData> list = gson.fromJson(json, collectionType);
        reader.close();
        buffReader.close();
        return list.stream().map(r -> new Object[] {r}).iterator();
    }

    //Login Data
    @DataProvider
    public static Iterator<Object[]> validLoginDataJson() throws IOException {
        File file = new File("src/test/resources/dataProviders/validLoginData.json");
        FileReader reader = new FileReader(file);
        BufferedReader buffReader = new BufferedReader(reader);
        String line = buffReader.readLine();
        String json = "";
        while (line != null) {
            json += line;
            line = buffReader.readLine();
        }
        Gson gson = new Gson();
        Type collectionType = new TypeToken<List<PublicLoginData>>(){}.getType();
        List<PublicLoginData> list = gson.fromJson(json, collectionType);
        reader.close();
        buffReader.close();
        return list.stream().map(l -> new Object[] {l}).iterator();
    }

    @DataProvider
    public static Iterator<Object[]> invalidLoginDataJson() throws IOException {
        File file = new File("src/test/resources/dataProviders/invalidLoginData.json");
        FileReader reader = new FileReader(file);
        BufferedReader buffReader = new BufferedReader(reader);
        String line = buffReader.readLine();
        String json = "";
        while (line != null) {
            json += line;
            line = buffReader.readLine();
        }
        Gson gson = new Gson();
        Type collectionType = new TypeToken<List<PublicLoginData>>(){}.getType();
        List<PublicLoginData> list = gson.fromJson(json, collectionType);
        reader.close();
        buffReader.close();
        return list.stream().map(l -> new Object[] {l}).iterator();
    }

    //Review Data
    @DataProvider
    public static Iterator<Object[]> validReviewDataJson() throws IOException {
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

    //Product Data
    @DataProvider
    public static Iterator<Object[]> validProdDataJson() throws IOException {
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
    public static Iterator<Object[]> validProdDataEditJson() throws IOException {
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

    //Category Data
    @DataProvider
    public static Iterator<Object[]> validCatDataJson() throws IOException {
        File file = new File("src/test/resources/dataProviders/validCategoryData.json");
        FileReader reader = new FileReader(file);
        BufferedReader buffReader = new BufferedReader(reader);
        String line = buffReader.readLine();
        String json = "";
        while (line != null) {
            json += line;
            line = buffReader.readLine();
        }
        Gson gson = new Gson();
        Type collectionType = new TypeToken<List<AdminCategoryData>>(){}.getType();
        List<AdminCategoryData> list = gson.fromJson(json, collectionType);
        reader.close();
        buffReader.close();
        return list.stream().map(l -> new Object[] {l}).iterator();
    }
}
