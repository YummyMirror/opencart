package com.opencart.models;

public class PublicRegistrationData {
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String password;
    private String rePassword;
    private Boolean subscribe;
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
    public PublicRegistrationData setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public PublicRegistrationData setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public PublicRegistrationData setEmail(String email) {
        this.email = email;
        return this;
    }

    public PublicRegistrationData setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public PublicRegistrationData setPassword(String password) {
        this.password = password;
        return this;
    }

    public PublicRegistrationData setRePassword(String rePassword) {
        this.rePassword = rePassword;
        return this;
    }

    public PublicRegistrationData setSubscribe(Boolean subscribe) {
        this.subscribe = subscribe;
        return this;
    }

    public PublicRegistrationData setPolicy(Boolean policy) {
        this.policy = policy;
        return this;
    }
}
