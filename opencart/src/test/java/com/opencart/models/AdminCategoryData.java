package com.opencart.models;

import com.google.gson.annotations.Expose;

public class AdminCategoryData {
    @Expose
    private String catName;
    @Expose
    private String catDesc;
    @Expose
    private String metaTagTitle;
    @Expose
    private String metaTagDesc;
    @Expose
    private String metaTagKeywords;
    @Expose
    private String parent;
    @Expose
    private String filters;
    @Expose
    private Boolean top;
    @Expose
    private String columns;
    @Expose
    private String sortOrder;
    @Expose
    private Boolean status;
    @Expose
    private String seoKeywords;
    @Expose
    private String designLayout;

    //Getters
    public String getCatName() {
        return catName;
    }

    public String getCatDesc() {
        return catDesc;
    }

    public String getMetaTagTitle() {
        return metaTagTitle;
    }

    public String getMetaTagDesc() {
        return metaTagDesc;
    }

    public String getMetaTagKeywords() {
        return metaTagKeywords;
    }

    public String getParent() {
        return parent;
    }

    public String getFilters() {
        return filters;
    }

    public Boolean getTop() {
        return top;
    }

    public String getColumns() {
        return columns;
    }

    public String getSortOrder() {
        return sortOrder;
    }

    public Boolean getStatus() {
        return status;
    }

    public String getSeoKeywords() {
        return seoKeywords;
    }

    public String getDesignLayout() {
        return designLayout;
    }

    //Setters
    public AdminCategoryData setCatName(String catName) {
        this.catName = catName;
        return this;
    }

    public AdminCategoryData setCatDesc(String catDesc) {
        this.catDesc = catDesc;
        return this;
    }

    public AdminCategoryData setMetaTagTitle(String metaTagTitle) {
        this.metaTagTitle = metaTagTitle;
        return this;
    }

    public AdminCategoryData setMetaTagDesc(String metaTagDesc) {
        this.metaTagDesc = metaTagDesc;
        return this;
    }

    public AdminCategoryData setMetaTagKeywords(String metaTagKeywords) {
        this.metaTagKeywords = metaTagKeywords;
        return this;
    }

    public AdminCategoryData setParent(String parent) {
        this.parent = parent;
        return this;
    }

    public AdminCategoryData setFilters(String filters) {
        this.filters = filters;
        return this;
    }

    public AdminCategoryData setTop(Boolean top) {
        this.top = top;
        return this;
    }

    public AdminCategoryData setColumns(String columns) {
        this.columns = columns;return this;

    }

    public AdminCategoryData setSortOrder(String sortOrder) {
        this.sortOrder = sortOrder;
        return this;
    }

    public AdminCategoryData setStatus(Boolean status) {
        this.status = status;
        return this;
    }

    public AdminCategoryData setSeoKeywords(String seoKeywords) {
        this.seoKeywords = seoKeywords;
        return this;
    }

    public AdminCategoryData setDesignLayout(String designLayout) {
        this.designLayout = designLayout;
        return this;
    }
}
