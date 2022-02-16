package com.example.inventory.data.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.lang.annotation.Native;

@Entity
public class User {
    public static final String TAG = "user";

    @PrimaryKey(autoGenerate = true)
    private int id;
    @NonNull
    private String email;
    @NonNull
    private String password;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setEmail(@NonNull String email) {
        this.email = email;
    }

    public void setPassword(@NonNull String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
