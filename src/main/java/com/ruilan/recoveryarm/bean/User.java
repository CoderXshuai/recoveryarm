package com.ruilan.recoveryarm.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

@Alias("user")
@Data
@AllArgsConstructor //全参构造函数
@NoArgsConstructor  //无参构造函数
public class User {
    private int userId;
    private String username;
    private String password;
    private String note = "患者";
    private int admin;

    public User(String username, String password, String note) {
        this.username = username;
        this.password = password;
        this.note = note;
    }
}
