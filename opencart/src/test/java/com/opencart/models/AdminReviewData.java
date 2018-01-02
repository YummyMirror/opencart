package com.opencart.models;

import com.google.gson.annotations.Expose;

public class AdminReviewData {
    private int id = Integer.MAX_VALUE;
    @Expose
    private String author;
    @Expose
    private String product;
    @Expose
    private String text;
    @Expose
    private int rating;
    private int date;
    private Boolean status;

    //Getters
    public int getId() {
        return id;
    }

    public String getAuthor() {
        return author;
    }

    public String getProduct() {
        return product;
    }

    public String getText() {
        return text;
    }

    public int getRating() {
        return rating;
    }

    public int getDate() {
        return date;
    }

    public Boolean getStatus() {
        return status;
    }

    //Setters
    public AdminReviewData setId(int id) {
        this.id = id;
        return this;
    }

    public AdminReviewData setAuthor(String author) {
        this.author = author;
        return this;
    }

    public AdminReviewData setProduct(String product) {
        this.product = product;
        return this;
    }

    public AdminReviewData setText(String text) {
        this.text = text;
        return this;
    }

    public AdminReviewData setRating(int rating) {
        this.rating = rating;
        return this;
    }

    public AdminReviewData setDate(int date) {
        this.date = date;
        return this;
    }

    public AdminReviewData setStatus(Boolean status) {
        this.status = status;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AdminReviewData that = (AdminReviewData) o;

        if (id != that.id) return false;
        return author != null ? author.equals(that.author) : that.author == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (author != null ? author.hashCode() : 0);
        return result;
    }
}
