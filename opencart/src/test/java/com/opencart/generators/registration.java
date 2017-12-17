package com.opencart.generators;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.opencart.models.PublicRegData;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Registration {
    public static void main(String[] args) throws IOException {
        int number = Integer.parseInt(args[0]);
        String filePath = args[1];
        String fileName = args[2];
        String format = args[3];
        File file = new File(filePath + fileName + "." + format);

        List<PublicRegData> publicRegDataList = createRegData(number);
        switch (format) {
            case "csv":
                saveCsv(file, publicRegDataList);
                break;
            case "json":
                saveJson(file, publicRegDataList);
                break;
            default:
                System.out.println("Incorrect file format");
        }
    }

    public static void saveCsv(File file, List<PublicRegData> publicRegDataList) throws IOException {
        if (file.exists())
            file.delete();
        FileWriter writer = new FileWriter(file);
        publicRegDataList.forEach(el -> {
            try {
                writer.write(String.format("%s;%s;%s;%s;%s;%s;%b;%b\n", el.getFirstName(),
                                                                        el.getLastName(),
                                                                        el.getEmail(),
                                                                        el.getPhone(),
                                                                        el.getPassword(),
                                                                        el.getRePassword(),
                                                                        el.getSubscribe(),
                                                                        el.getPolicy()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        writer.flush();
        writer.close();
    }

    public static void saveJson(File file, List<PublicRegData> publicRegDataList) throws IOException {
        if (file.exists())
            file.delete();
        Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
        String json = gson.toJson(publicRegDataList);
        FileWriter writer = new FileWriter(file);
        writer.write(json);
        writer.flush();
        writer.close();
    }

    public static List<PublicRegData> createRegData(int number) {
        List<PublicRegData> list = new ArrayList<PublicRegData>(0);
        for (int i = 0; i < number; i++) {
            list.add(new PublicRegData().setFirstName("Kobe")
                                        .setLastName("Bryant")
                                        .setEmail("kobe.bryant@gmail.com")
                                        .setPhone("12345")
                                        .setPassword("12345")
                                        .setRePassword("12345")
                                        .setSubscribe(true)
                                        .setPolicy(true));
        }
        return list;
    }
}
