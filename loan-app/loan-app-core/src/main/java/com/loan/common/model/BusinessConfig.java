package com.loan.common.model;

import java.io.Serializable;


public class BusinessConfig implements Serializable{

    /** 主键 */
    private Long id;
    /** 商户平台code */
    private String busCode;
    /** 商户私钥 */
    private String busKey;
    /** 是否关闭平台 1关闭 0正常使用 */
    private String isClose;
    /** 添加时间 */
    private Long addtime;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBusCode() {
        return busCode;
    }

    public void setBusCode(String busCode) {
        this.busCode = busCode;
    }

    public String getBusKey() {
        return busKey;
    }

    public void setBusKey(String busKey) {
        this.busKey = busKey;
    }

    public String getIsClose() {
        return isClose;
    }

    public void setIsClose(String isClose) {
        this.isClose = isClose;
    }

    public Long getAddtime() {
        return addtime;
    }

    public void setAddtime(Long addtime) {
        this.addtime = addtime;
    }

    @Override
    public String toString() {
        return "BusinessConfig{" +
                    "id=" + id +
                    ", busCode='" + busCode + "\'" +
                    ", busKey='" + busKey + "\'" +
                    ", isClose='" + isClose + "\'" +
                    ", addtime=" + addtime +
                    "}";
    }
}