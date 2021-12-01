package com.example.skylar.model;

public  class UserInformation {

    String email, name, password, number, publicKey;


    public UserInformation() {
    }

    public UserInformation(String email, String name, String password, String number, String publicKey) {
        this.email = email;
        this.name = name;
        this.password = password;
        this.number = number;
        this.publicKey = publicKey;

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }


}


