package com.example.finalproject;

import java.io.Serializable;
import java.util.Arrays;

public class Messages {

    private int id;
    private int profile_id;
    private String message;
    private String email;

    public Messages(int id, int profile_id, String message, String email) {
        this.id = id;
        this.profile_id = profile_id;
        this.message = message;
        this.email = email;
    }

    public Messages() {
    }

    public int getId() {
        return id;
    }

    public int getProfile_id() {
        return profile_id;
    }

    public String getMessage() {
        return message;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "Messages{" +
                "id=" + id +
                ", profile_id=" + profile_id +
                ", message='" + message + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
