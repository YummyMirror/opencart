package com.opencart.dataProviders;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.opencart.annotations.DataSource;
import com.opencart.models.*;
import org.testng.annotations.DataProvider;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class AllDataProviders {
    //Registration Data
    @DataProvider
    public static Iterator<Object[]> registrationData(Method method) throws Exception {
        List<PublicRegData> list = new ArrayList<PublicRegData>(0);
        if (method.isAnnotationPresent(DataSource.class)) {
            DataSource file = method.getAnnotation(DataSource.class);
            switch (file.format()) {
                case CSV:
                    BufferedReader bReader = new BufferedReader(new FileReader( new File(file.value())));
                    String line1 = bReader.readLine();
                    while (line1 != null) {
                        String[] split = line1.split(";");
                        list.add(new PublicRegData().setFirstName(split[0])
                                                                  .setLastName(split[1])
                                                                  .setEmail(split[2])
                                                                  .setPhone(split[3])
                                                                  .setPassword(split[4])
                                                                  .setRePassword(split[5])
                                                                  .setSubscribe(Boolean.parseBoolean(split[6]))
                                                                  .setPolicy(Boolean.parseBoolean(split[7])));
                        line1 = bReader.readLine();
                    }
                    bReader.close();
                    break;
                case JSON:
                    BufferedReader buffReader = new BufferedReader(new FileReader(new File(file.value())));
                    String line2 = buffReader.readLine();
                    StringBuilder jsonB = new StringBuilder("");
                    while (line2 != null) {
                        jsonB.append(line2);
                        line2 = buffReader.readLine();
                    }
                    String json = jsonB.toString();
                    Gson gson = new Gson();
                    Type collectionType = new TypeToken<List<PublicRegData>>(){}.getType();
                    list = gson.fromJson(json, collectionType);
                    buffReader.close();
                    break;
                default:
                    throw new Exception("Unrecognized format " + file.format().toString());
            }
        }
        return list.stream().map(r -> new Object[] {r}).collect(Collectors.toList()).iterator();
    }

    //Login Data
    @DataProvider
    public static Iterator<Object[]> loginData(Method method) throws Exception {
        List<PublicLoginData> list = new ArrayList<PublicLoginData>(0);
        if (method.isAnnotationPresent(DataSource.class)) {
            DataSource file = method.getAnnotation(DataSource.class);
            switch (file.format()) {
                case JSON:
                    BufferedReader buffReader = new BufferedReader(new FileReader(new File(file.value())));
                    String line = buffReader.readLine();
                    StringBuilder jsonB = new StringBuilder("");
                    while (line != null) {
                        jsonB.append(line);
                        line = buffReader.readLine();
                    }
                    String json = jsonB.toString();
                    Gson gson = new Gson();
                    Type collectionType = new TypeToken<List<PublicLoginData>>(){}.getType();
                    list = gson.fromJson(json, collectionType);
                    buffReader.close();
                    break;
                default:
                    throw new Exception("Unrecognized format " + file.format().toString());
            }
        }
        return list.stream().map(l -> new Object[] {l}).collect(Collectors.toList()).iterator();
    }

    //Review Data
    @DataProvider
    public static Iterator<Object[]> reviewData(Method method) throws Exception {
        List<AdminReviewData> list = new ArrayList<AdminReviewData>(0);
        if (method.isAnnotationPresent(DataSource.class)) {
            DataSource file = method.getAnnotation(DataSource.class);
            switch (file.format()) {
                case JSON:
                    BufferedReader buffReader = new BufferedReader(new FileReader(new File(file.value())));
                    String line = buffReader.readLine();
                    StringBuilder jsonB = new StringBuilder("");
                    while (line != null) {
                        jsonB.append(line);
                        line = buffReader.readLine();
                    }
                    String json = jsonB.toString();
                    Gson gson = new Gson();
                    Type colType = new TypeToken<List<AdminReviewData>>() { }.getType();
                    list = gson.fromJson(json, colType);
                    buffReader.close();
                    break;
                default:
                    throw new Exception("Unrecognized format " + file.format().toString());
            }
        }
        return list.stream().map(r -> new Object[] {r}).collect(Collectors.toList()).iterator();
    }

    //Product Data
    @DataProvider
    public static Iterator<Object[]> productData(Method method) throws Exception {
        List<AdminProductData> list = new ArrayList<AdminProductData>(0);
        if (method.isAnnotationPresent(DataSource.class)) {
            DataSource file = method.getAnnotation(DataSource.class);
            switch (file.format()) {
                case JSON:
                    BufferedReader buffReader = new BufferedReader(new FileReader(new File(file.value())));
                    String line = buffReader.readLine();
                    StringBuilder jsonB = new StringBuilder("");
                    while (line != null) {
                        jsonB.append(line);
                        line = buffReader.readLine();
                    }
                    String json = jsonB.toString();
                    Gson gson = new Gson();
                    Type collectionType = new TypeToken<List<AdminProductData>>(){}.getType();
                    list = gson.fromJson(json, collectionType);
                    buffReader.close();
                    break;
                default:
                    throw new Exception("Unrecognized format " + file.format().toString());
            }
        }
        return list.stream().map(l -> new Object[] {l}).collect(Collectors.toList()).iterator();
    }

    //Category Data
    @DataProvider
    public static Iterator<Object[]> categoryData(Method method) throws Exception {
        List<AdminCategoryData> list = new ArrayList<AdminCategoryData>(0);
        if (method.isAnnotationPresent(DataSource.class)) {
            DataSource file = method.getAnnotation(DataSource.class);
            switch (file.format()) {
                case JSON:
                    BufferedReader buffReader = new BufferedReader(new FileReader(new File(file.value())));
                    String line = buffReader.readLine();
                    StringBuilder jsonB = new StringBuilder("");
                    while (line != null) {
                        jsonB.append(line);
                        line = buffReader.readLine();
                    }
                    String json = jsonB.toString();
                    Gson gson = new Gson();
                    Type collectionType = new TypeToken<List<AdminCategoryData>>(){}.getType();
                    list = gson.fromJson(json, collectionType);
                    buffReader.close();
                    break;
                default:
                    throw new Exception("Unrecognized format " + file.format().toString());
            }
        }
        return list.stream().map(l -> new Object[] {l}).collect(Collectors.toList()).iterator();
    }
}
