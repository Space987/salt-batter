package com.example.finalproject;

import java.io.Serializable;
import java.util.Arrays;

public class User implements Serializable {

    private int id;
    private String username;
    private String password;
    private int total;
    private byte[] image;

    public User(int id, String username, String password, int total, byte[] image) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.total = total;
        this.image = image;
    }

    public User() {
    }

    public byte[] getImage() {
        return image;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public int getTotal() {
        return total;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", total=" + total +
                ", image=" + Arrays.toString(image) +
                '}';
    }
}
