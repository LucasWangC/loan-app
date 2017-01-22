package com.loan.common.model;

import java.io.Serializable;


public class BusinessConfig implements Serializable{

    /** ���� */
    private Long id;
    /** �̻�ƽ̨code */
    private String busCode;
    /** �̻�˽Կ */
    private String busKey;
    /** �Ƿ�ر�ƽ̨ 1�ر� 0����ʹ�� */
    private String isClose;
    /** ���ʱ�� */
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