package com.opencart.models;

import com.google.gson.annotations.Expose;

public class AdminProductData {
    private int id = Integer.MAX_VALUE;
    @Expose
    private String name;
    @Expose
    private String metaTagTitle;
    @Expose
    private String model;
    @Expose
    private int dateAvailable;

    //Getters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getMetaTagTitle() {
        return metaTagTitle;
    }

    public String getModel() {
        return model;
    }

    public int getDateAvailable() {
        return dateAvailable;
    }

    //Setters
    public AdminProductData setId(int id) {
        this.id = id;
        return this;
    }

    public AdminProductData setName(String name) {
        this.name = name;
        return this;
    }

    public AdminProductData setMetaTagTitle(String metaTagTitle) {
        this.metaTagTitle = metaTagTitle;
        return this;
    }

    public AdminProductData setModel(String model) {
        this.model = model;
        return this;
    }

    public AdminProductData setDateAvailable(int dateAvailable) {
        this.dateAvailable = dateAvailable;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AdminProductData that = (AdminProductData) o;

        if (id != that.id) return false;
        return name != null ? name.equals(that.name) : that.name == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}
