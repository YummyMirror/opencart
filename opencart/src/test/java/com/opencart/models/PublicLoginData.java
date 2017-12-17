package com.opencart.models;

import com.google.gson.annotations.Expose;

public class PublicLoginData {
    @Expose
    private String email;
    @Expose
    private String password;

    //Getters
    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    //Setters
    public PublicLoginData setEmail(String email) {
        this.email = email;
        return this;
    }

    public PublicLoginData setPassword(String password) {
        this.password = password;
        return this;
    }
}
