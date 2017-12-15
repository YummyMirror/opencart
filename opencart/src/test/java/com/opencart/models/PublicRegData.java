package com.opencart.models;

import com.google.gson.annotations.Expose;

public class PublicRegData {
    @Expose
    private String firstName;
    @Expose
    private String lastName;
    @Expose
    private String email;
    @Expose
    private String phone;
    @Expose
    private String password;
    @Expose
    private String rePassword;
    @Expose
    private Boolean subscribe;
    @Expose
    private Boolean policy;

    //Getters
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getPassword() {
        return password;
    }

    public String getRePassword() {
        return rePassword;
    }

    public Boolean getSubscribe() {
        return subscribe;
    }

    public Boolean getPolicy() {
        return policy;
    }

    //Setters
    public PublicRegData setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public PublicRegData setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public PublicRegData setEmail(String email) {
        this.email = email;
        return this;
    }

    public PublicRegData setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public PublicRegData setPassword(String password) {
        this.password = password;
        return this;
    }

    public PublicRegData setRePassword(String rePassword) {
        this.rePassword = rePassword;
        return this;
    }

    public PublicRegData setSubscribe(Boolean subscribe) {
        this.subscribe = subscribe;
        return this;
    }

    public PublicRegData setPolicy(Boolean policy) {
        this.policy = policy;
        return this;
    }

    @Override
    public String toString() {
        return "PublicRegData{" +
                "firstName='" + firstName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
