package com.dada.android.db;

import cn.bmob.v3.BmobObject;

/**
 * Created by asus1 on 2017/12/22.
 */

public class _User extends BmobObject {
    private String username;
    private String password;
    private String mobilephoneNumber;
    private String sex;
    private String email;

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

    public String getMobilephoneNumber() {
        return mobilephoneNumber;
    }

    public void setMobilephoneNumber(String mobilephoneNumber) {
        this.mobilephoneNumber = mobilephoneNumber;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
