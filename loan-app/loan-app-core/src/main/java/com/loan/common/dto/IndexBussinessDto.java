package com.loan.common.dto;

import java.io.Serializable;

/**
 * Copyright (C), 2016-2020 助你贷
 * FileName: com.loan.common.dto.IndexBussinessDto.java
 * Author: lucasWang
 * Email: m13803851175@163.com
 * Date: 2017/1/13 0:41
 * Description:
 * History:
 * <Author>      <Time>    <version>    <desc>
 * lucasWang   0:41    1.0          Create
 */
public class IndexBussinessDto implements Serializable {

    //平台ID
    private Long busId;
    //链接类型 1 详情页原生内页 2 web网站
    private Integer linkType;
    //链接地址
    private String linkPath;
    //icon图片地址
    private String iconPath;
    //recommond 特别推荐 like 猜你喜欢
    private String titleType;
    //大标题
    private String titleBig;
    //小标题
    private String titleSmall;
    //导航文字
    private String guideWord;

    public Long getBusId() {
        return busId;
    }

    public void setBusId(Long busId) {
        this.busId = busId;
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

    public String getTitleType() {
        return titleType;
    }

    public void setTitleType(String titleType) {
        this.titleType = titleType;
    }

    public String getTitleBig() {
        return titleBig;
    }

    public void setTitleBig(String titleBig) {
        this.titleBig = titleBig;
    }

    public String getTitleSmall() {
        return titleSmall;
    }

    public void setTitleSmall(String titleSmall) {
        this.titleSmall = titleSmall;
    }

    public String getGuideWord() {
        return guideWord;
    }

    public void setGuideWord(String guideWord) {
        this.guideWord = guideWord;
    }
}
