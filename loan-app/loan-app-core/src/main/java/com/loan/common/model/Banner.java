package com.loan.common.model;

import java.io.Serializable;

/**
 * Copyright (C), 2016-2020 助你贷
 * FileName: com.loan.common.model.Banner.java
 * Author: lucasWang
 * Email: m13803851175@163.com
 * Date: 2017/1/8 13:45
 * Description:
 * History:
 * <Author>      <Time>    <version>    <desc>
 * lucasWang   13:45    1.0          Create
 */
public class Banner implements Serializable{

    private int id;//编号ID

    private String name;//banner名称

    private String type;//banner连接类型：1原生页面 2 web页面连接

    private String link;//连接地址

    private String picPath;//banner图片地址

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getPicPath() {
        return picPath;
    }

    public void setPicPath(String picPath) {
        this.picPath = picPath;
    }
}
