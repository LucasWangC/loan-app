package com.loan.common.model;

import java.io.Serializable;


public class BusinessVisit implements Serializable{

    /**  */
    private Long id;
    /** 平台ID */
    private String busId;
    /** 访问页面URL */
    private String pageUrl;
    /** 设备ID */
    private String deviceId;
    /** 添加时间 */
    private Long addtime;
    /** 访问IP地址 */
    private String visitIp;
    /**  */
    private String extend1;
    /**  */
    private String extend2;
    /**  */
    private String extend3;
    /**  */
    private String extend4;
    /**  */
    private String extend5;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBusId() {
        return busId;
    }

    public void setBusId(String busId) {
        this.busId = busId;
    }

    public String getPageUrl() {
        return pageUrl;
    }

    public void setPageUrl(String pageUrl) {
        this.pageUrl = pageUrl;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public Long getAddtime() {
        return addtime;
    }

    public void setAddtime(Long addtime) {
        this.addtime = addtime;
    }

    public String getVisitIp() {
        return visitIp;
    }

    public void setVisitIp(String visitIp) {
        this.visitIp = visitIp;
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
        return "BusinessVisit{" +
                    "id=" + id +
                    ", busId='" + busId + "\'" +
                    ", pageUrl='" + pageUrl + "\'" +
                    ", deviceId='" + deviceId + "\'" +
                    ", addtime=" + addtime +
                    ", visitIp='" + visitIp + "\'" +
                    ", extend1='" + extend1 + "\'" +
                    ", extend2='" + extend2 + "\'" +
                    ", extend3='" + extend3 + "\'" +
                    ", extend4='" + extend4 + "\'" +
                    ", extend5='" + extend5 + "\'" +
                    "}";
    }
}