package com.dada.android.db;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobObject;

/**
 * Created by asus1 on 2017/11/26.
 */

public class DingDan extends BmobObject {
    private Integer num;
    private String cid;
    private String pid;

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }
    public void setCid(String cid){
        this.cid=cid;
    }
    public String getCid(){
        return cid;
    }
    public void setPid(String pid){
        this.pid=pid;
    }
    public String getPid(){
        return pid;
    }
}
