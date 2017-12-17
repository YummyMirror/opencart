package com.opencart.generators;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.opencart.models.PublicLoginData;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Login {
    public static void main(String[] args) throws IOException {
        int number = Integer.parseInt(args[0]);
        String filePath = args[1];
        String fileName = args[2];
        String format = args[3];
        File file = new File(filePath + fileName + "." + format);

        List<PublicLoginData> publicLoginPageList = createLoginDate(number);
        switch (format) {
            case "csv":
                saveCsv(file, publicLoginPageList);
                break;
            case "json":
                saveJson(file, publicLoginPageList);
                break;
            default:
                System.out.println("Incorrect file format");
        }
    }

    public static void saveCsv(File file, List<PublicLoginData> publicLoginDataList) throws IOException {
        if (file.exists())
            file.delete();
        FileWriter writer = new FileWriter(file);
        publicLoginDataList.forEach(l -> {
            try {
                writer.write(String.format("%s;%s\n", l.getEmail(), l.getPassword()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        writer.flush();
        writer.close();
    }

    public static void saveJson(File file, List<PublicLoginData> publicLoginDataList) throws IOException {
        if (file.exists())
            file.delete();
        Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
        String json = gson.toJson(publicLoginDataList);
        FileWriter writer = new FileWriter(file);
        writer.write(json);
        writer.flush();
        writer.close();
    }

    public static List<PublicLoginData> createLoginDate(int number) {
        List<PublicLoginData> list = new ArrayList<PublicLoginData>(0);
        for (int i = 0; i < number; i++)
            list.add(new PublicLoginData().setEmail("test@test.test")
                                          .setPassword("test"));
        return list;
    }
}
