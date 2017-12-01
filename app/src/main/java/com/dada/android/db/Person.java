package com.dada.android.db;

import org.litepal.crud.DataSupport;

import java.io.File;

import cn.bmob.v3.BmobObject;

/**
 * Created by asus1 on 2017/11/23.
 */

public class Person extends BmobObject {
    private String name;
    private String sex;
    private String email;
    private String phone;
    private String password;
    private File head;
    public File getTouxiang() {
        return head;
    }

    public void setTouxiang(File head) {
       this.head=head;
    }


    public void setName(String name){
        this.name=name;
    }
    public String getName(){
        return  name;
    }
    public void setSex(String sex){
        this.sex=sex;
    }
    public String getSex(){
        return sex;
    }
    public void setId(String id){
        this.email=id;
    }
    public String getId(){
        return email;
    }
    public void setPhone(String phone){
        this.phone=phone;
    }
    public String getPhone(){
        return phone;
    }
    public void setPassword(String password){
        this.password=password;
    }
    public String getPassword(){
        return password;
    }
    public String toString(){
        return "姓名"+name+"e_mail"+email;
    }
}
