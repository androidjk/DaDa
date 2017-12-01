package com.dada.android.db;

import org.litepal.crud.DataSupport;

import java.io.File;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobFile;

/**
 * Created by asus1 on 2017/11/23.
 */

public class Cark extends BmobObject {
    private String name;
    private String cid;
    private Integer num;
    private Integer price;
    private String type;
    private Integer id;
    private BmobFile drawable;
    public BmobFile getDrawable() {
        return drawable;
    }

    public void setDrawable(BmobFile pic) {
        this.drawable = pic;
    }



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public void setName(String cname) {
        this.name = cname;
    }

    public String getName() {
        return name;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getCid() {
        return cid;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Integer getNum() {
        return num;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getPrice() {
        return price;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

}
