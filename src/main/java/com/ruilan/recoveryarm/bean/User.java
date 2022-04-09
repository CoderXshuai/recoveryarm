package com.ruilan.recoveryarm.bean;

import org.apache.ibatis.type.Alias;

@Alias("user")
public class User {
    private int userId;
    private String username;
    private String password;
    private String note = "患者";
    private int admin;

    public User() {
    }

    public User(String username, String password, String note) {
        this.username = username;
        this.password = password;
        this.note = note;
    }

    public User(int userId, String username, String password, String note) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.note = note;
    }

    public User(int userId, String username, String password, String note, int admin) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.note = note;
        this.admin = admin;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public int getAdmin() {
        return admin;
    }

    public void setAdmin(int admin) {
        this.admin = admin;
    }
}
