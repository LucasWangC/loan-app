package com.loan.common.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * Copyright (C), 2016-2020 助你贷
 * FileName: com.loan.common.dto.BusinessDto.java
 * Author: lucasWang
 * Email: m13803851175@163.com
 * Date: 2017/1/12 0:27
 * Description:
 * History:
 * <Author>      <Time>    <version>    <desc>
 * lucasWang   0:27    1.0          Create
 */
public class BusinessDto implements Serializable{

    //平台ID
    private Long busId;
    //角标
    private String cornerPath;
    //链接类型 1 详情页原生内页 2 web网站
    private Integer linkType;
    //链接地址
    private String linkPath;
    //icon图片地址
    private String iconPath;
    //简述文字
    private String shortWord;
    //平台名称
    private String busName;
    //月费率
    private String fee;

    public Long getBusId() {
        return busId;
    }

    public void setBusId(Long busId) {
        this.busId = busId;
    }

    public String getCornerPath() {
        return cornerPath;
    }

    public void setCornerPath(String cornerPath) {
        this.cornerPath = cornerPath;
    }

    public Integer getLinkType() {
        return linkType;
    }

    public void setLinkType(Integer linkType) {
        this.linkType = linkType;
    }

    public String getLinkPath() {
        return linkPath;
    }

    public void setLinkPath(String linkPath) {
        this.linkPath = linkPath;
    }

    public String getIconPath() {
        return iconPath;
    }

    public void setIconPath(String iconPath) {
        this.iconPath = iconPath;
    }

    public String getShortWord() {
        return shortWord;
    }

    public void setShortWord(String shortWord) {
        this.shortWord = shortWord;
    }

    public String getBusName() {
        return busName;
    }

    public void setBusName(String busName) {
        this.busName = busName;
    }

    public String getFee() {
        return fee;
    }

    public void setFee(String fee) {
        this.fee = fee;
    }
}
