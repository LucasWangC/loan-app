package com.loan.common.model;

import java.io.Serializable;


public class Appinfo implements Serializable{

    /** id */
    private Integer id;
    /** 设备类型：1 安卓 2 IOS */
    private Integer device;
    /** 版本号 */
    private String versionkey;
    /** 版本更新信息 */
    private String versioninfo;
    /** 更新版本的下载地址 */
    private String downloadurl;
    /** 添加时间 */
    private String addtime;
    /** 添加IP */
    private String addip;
    /** 是否开启强制更新 1 强制 0不强制 */
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