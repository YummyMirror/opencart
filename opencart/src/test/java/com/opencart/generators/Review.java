package com.opencart.generators;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.opencart.models.AdminReviewData;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Review {
    public static void main(String[] args) throws IOException {
        int number = Integer.parseInt(args[0]);
        String filePath = args[1];
        String fileName = args[2];
        String format = args[3];
        File file = new File(filePath + fileName + "." + format);

        List<AdminReviewData> reviews = createReviews(number);
        saveJson(file, reviews);
    }

    public static List<AdminReviewData> createReviews(int number) {
        List<AdminReviewData> list = new ArrayList<AdminReviewData>(0);
        int count = 1;
        for (int i = 0; i < number; i++) {
            list.add(new AdminReviewData().setAuthor("Author " + count)
                                          .setProduct("E")
                                          .setText("Text " + count)
                                          .setRating(5));
            count++;
        }
        return list;
    }

    public static void saveJson(File file, List<AdminReviewData> reviews) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
        String json = gson.toJson(reviews);
        FileWriter writer = new FileWriter(file);
        writer.write(json);
        writer.flush();
        writer.close();
    }
}
