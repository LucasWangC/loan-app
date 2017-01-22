package com.loan.common.model;

import java.io.Serializable;


public class Appinfo implements Serializable{

    /** id */
    private Integer id;
    /** �豸���ͣ�1 ��׿ 2 IOS */
    private Integer device;
    /** �汾�� */
    private String versionkey;
    /** �汾������Ϣ */
    private String versioninfo;
    /** ���°汾�����ص�ַ */
    private String downloadurl;
    /** ���ʱ�� */
    private String addtime;
    /** ���IP */
    private String addip;
    /** �Ƿ���ǿ�Ƹ��� 1 ǿ�� 0��ǿ�� */
    private Integer forcestatus;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDevice() {
        return device;
    }

    public void setDevice(Integer device) {
        this.device = device;
    }

    public String getVersionkey() {
        return versionkey;
    }

    public void setVersionkey(String versionkey) {
        this.versionkey = versionkey;
    }

    public String getVersioninfo() {
        return versioninfo;
    }

    public void setVersioninfo(String versioninfo) {
        this.versioninfo = versioninfo;
    }

    public String getDownloadurl() {
        return downloadurl;
    }

    public void setDownloadurl(String downloadurl) {
        this.downloadurl = downloadurl;
    }

    public String getAddtime() {
        return addtime;
    }

    public void setAddtime(String addtime) {
        this.addtime = addtime;
    }

    public String getAddip() {
        return addip;
    }

    public void setAddip(String addip) {
        this.addip = addip;
    }

    public Integer getForcestatus() {
        return forcestatus;
    }

    public void setForcestatus(Integer forcestatus) {
        this.forcestatus = forcestatus;
    }

    @Override
    public String toString() {
        return "Appinfo{" +
                    "id=" + id +
                    ", device=" + device +
                    ", versionkey='" + versionkey + "\'" +
                    ", versioninfo='" + versioninfo + "\'" +
                    ", downloadurl='" + downloadurl + "\'" +
                    ", addtime='" + addtime + "\'" +
                    ", addip='" + addip + "\'" +
                    ", forcestatus=" + forcestatus +
                    "}";
    }
}