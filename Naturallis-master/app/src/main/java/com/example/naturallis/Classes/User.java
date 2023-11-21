package com.example.naturallis.Classes;

public class User {

    public String username;
    public String email;
    public  Boolean veggie;

    public User(){

    }

    public User(String username,String email, Boolean veggie){

        this.username = username;
        this.email = email;
        this.veggie = veggie;

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getVeggie() {
        return veggie;
    }

    public void setVeggie(Boolean veggie) {
        this.veggie = veggie;
    }
}
