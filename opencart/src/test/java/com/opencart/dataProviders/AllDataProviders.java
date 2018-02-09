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
    public static Iterator<Object[]> regDataCsv(Method method) throws IOException {
        List<Object[]> list = new ArrayList<Object[]>(0);
        if (method.isAnnotationPresent(DataSource.class)) {
            DataSource file = method.getAnnotation(DataSource.class);
            BufferedReader buffReader = new BufferedReader(new FileReader( new File(file.value())));
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
            buffReader.close();
        }
        return list.iterator();
    }

    @DataProvider
    public static Iterator<Object[]> registrationData(Method method) throws IOException {
        List<PublicRegData> list = new ArrayList<PublicRegData>(0);
        if (method.isAnnotationPresent(DataSource.class)) {
            DataSource file = method.getAnnotation(DataSource.class);
            BufferedReader buffReader = new BufferedReader(new FileReader(new File(file.value())));
            String line = buffReader.readLine();
            StringBuilder jsonB = new StringBuilder("");
            while (line != null) {
                jsonB.append(line);
                line = buffReader.readLine();
            }
            String json = jsonB.toString();
            Gson gson = new Gson();
            Type collectionType = new TypeToken<List<PublicRegData>>(){}.getType();
            list = gson.fromJson(json, collectionType);
            buffReader.close();
        }
        return list.stream().map(r -> new Object[] {r}).collect(Collectors.toList()).iterator();
    }

    //Login Data
    @DataProvider
    public static Iterator<Object[]> loginData(Method method) throws IOException {
        List<PublicLoginData> list = new ArrayList<PublicLoginData>(0);
        if (method.isAnnotationPresent(DataSource.class)) {
            DataSource file = method.getAnnotation(DataSource.class);
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
        }
        return list.stream().map(l -> new Object[] {l}).collect(Collectors.toList()).iterator();
    }

    //Review Data
    @DataProvider
    public static Iterator<Object[]> reviewData(Method method) throws IOException {
        List<AdminReviewData> list = new ArrayList<AdminReviewData>(0);
        if (method.isAnnotationPresent(DataSource.class)) {
            DataSource file = method.getAnnotation(DataSource.class);
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
        }
        return list.stream().map(r -> new Object[] {r}).collect(Collectors.toList()).iterator();
    }

    //Product Data
    @DataProvider
    public static Iterator<Object[]> productData(Method method) throws IOException {
        List<AdminProductData> list = new ArrayList<AdminProductData>(0);
        if (method.isAnnotationPresent(DataSource.class)) {
            DataSource file = method.getAnnotation(DataSource.class);
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
        }
        return list.stream().map(l -> new Object[] {l}).collect(Collectors.toList()).iterator();
    }

    //Category Data
    @DataProvider
    public static Iterator<Object[]> categoryData(Method method) throws IOException {
        List<AdminCategoryData> list = new ArrayList<AdminCategoryData>(0);
        if (method.isAnnotationPresent(DataSource.class)) {
            DataSource file = method.getAnnotation(DataSource.class);
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
        }
        return list.stream().map(l -> new Object[] {l}).collect(Collectors.toList()).iterator();
    }
}
