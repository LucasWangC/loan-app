package com.loan.common.model;

import java.io.Serializable;
import java.util.Date;


public class Business implements Serializable{

    /** 主键 */
    private Long id;
    /** 首页列表类型 like,recommend,common */
    private String titleType;
    /** 排序等级 1-10 */
    private Integer indexOrder;
    /** 是否在首页上显示 0 不显示 1 显示 */
    private Integer isIndex;
    /** 链接类型 1 详情页原生内页 2 web网站 */
    private Integer linkType;
    /** 链接地址 */
    private String linkPath;
    /** 图片地址 */
    private String iconPath;
    /** 大标题 */
    private String titleBig;
    /** 小标题 */
    private String titleSmall;
    /** 引导文字 */
    private String guideWord;
    /** 平台费率 */
    private Double channelApr;
    /** 平台简述 */
    private String channelDesc;
    /** 平台等级 */
    private Integer channelLevel;
    /** 平台标识 */
    private String channelTag;
    /**  */
    private String cornerPath;
    /** 点击量 */
    private Long clickTotal;
    /** 创建时间 */
    private Date createTime;
    /** 创建人 */
    private Integer createUser;
    /** 创建ip */
    private String createIp;
    /** 拓展字段 */
    private String extend1;
    /** 拓展字段 */
    private String extend2;
    /** 拓展字段 */
    private String extend3;
    /** 拓展字段 */
    private String extend4;
    /** 拓展字段 */
    private String extend5;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitleType() {
        return titleType;
    }

    public void setTitleType(String titleType) {
        this.titleType = titleType;
    }

    public Integer getIndexOrder() {
        return indexOrder;
    }

    public void setIndexOrder(Integer indexOrder) {
        this.indexOrder = indexOrder;
    }

    public Integer getIsIndex() {
        return isIndex;
    }

    public void setIsIndex(Integer isIndex) {
        this.isIndex = isIndex;
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

    public Double getChannelApr() {
        return channelApr;
    }

    public void setChannelApr(Double channelApr) {
        this.channelApr = channelApr;
    }

    public String getChannelDesc() {
        return channelDesc;
    }

    public void setChannelDesc(String channelDesc) {
        this.channelDesc = channelDesc;
    }

    public Integer getChannelLevel() {
        return channelLevel;
    }

    public void setChannelLevel(Integer channelLevel) {
        this.channelLevel = channelLevel;
    }

    public String getChannelTag() {
        return channelTag;
    }

    public void setChannelTag(String channelTag) {
        this.channelTag = channelTag;
    }

    public String getCornerPath() {
        return cornerPath;
    }

    public void setCornerPath(String cornerPath) {
        this.cornerPath = cornerPath;
    }

    public Long getClickTotal() {
        return clickTotal;
    }

    public void setClickTotal(Long clickTotal) {
        this.clickTotal = clickTotal;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getCreateUser() {
        return createUser;
    }

    public void setCreateUser(Integer createUser) {
        this.createUser = createUser;
    }

    public String getCreateIp() {
        return createIp;
    }

    public void setCreateIp(String createIp) {
        this.createIp = createIp;
    }

    public String getExtend1() {
        return extend1;
    }

    public void setExtend1(String extend1) {
        this.extend1 = extend1;
    }

    public String getExtend2() {
        return extend2;
    }

    public void setExtend2(String extend2) {
        this.extend2 = extend2;
    }

    public String getExtend3() {
        return extend3;
    }

    public void setExtend3(String extend3) {
        this.extend3 = extend3;
    }

    public String getExtend4() {
        return extend4;
    }

    public void setExtend4(String extend4) {
        this.extend4 = extend4;
    }

    public String getExtend5() {
        return extend5;
    }

    public void setExtend5(String extend5) {
        this.extend5 = extend5;
    }

    @Override
    public String toString() {
        return "Business{" +
                    "id=" + id +
                    ", titleType='" + titleType + "\'" +
                    ", indexOrder=" + indexOrder +
                    ", isIndex=" + isIndex +
                    ", linkType=" + linkType +
                    ", linkPath='" + linkPath + "\'" +
                    ", iconPath='" + iconPath + "\'" +
                    ", titleBig='" + titleBig + "\'" +
                    ", titleSmall='" + titleSmall + "\'" +
                    ", guideWord='" + guideWord + "\'" +
                    ", channelApr=" + channelApr +
                    ", channelDesc='" + channelDesc + "\'" +
                    ", channelLevel=" + channelLevel +
                    ", channelTag='" + channelTag + "\'" +
                    ", cornerPath='" + cornerPath + "\'" +
                    ", clickTotal=" + clickTotal +
                    ", createTime=" + createTime +
                    ", createUser=" + createUser +
                    ", createIp='" + createIp + "\'" +
                    ", extend1='" + extend1 + "\'" +
                    ", extend2='" + extend2 + "\'" +
                    ", extend3='" + extend3 + "\'" +
                    ", extend4='" + extend4 + "\'" +
                    ", extend5='" + extend5 + "\'" +
                    "}";
    }
}