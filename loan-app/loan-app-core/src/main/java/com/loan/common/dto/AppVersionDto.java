package com.loan.common.dto;

import java.io.Serializable;

/**
 * Copyright (C), 2011-2015 ���ݴ�
 * FileName: com.loan.common.dto.AppVersionDto.java
 * Author: wangyingjie
 * Email: wangyingjie@wzdai.com
 * Date: 2017/1/20 16:21
 * Description:
 * History:
 * <Author>      <Time>    <version>    <desc>
 * wangyingjie   16:21    1.0          Create
 */
public class AppVersionDto implements Serializable{

    //��׿�汾��
    private String androidVersion;
    //��׿�汾��Ϣ
    private String androidVersionInfo;
    //��׿���ص�ַ
    private String androidDownloadUrl;
    //��׿�Ƿ�ǿ������ 1ǿ�� 0��ǿ��
    private String androidIsForce;
    //IOS�汾��
    private String iOSVersion;
    //IOS�汾��Ϣ
    private String iOSVersionInfo;
    //IOS���ص�ַ
    private String iOSDownloadUrl;
    //IOS�Ƿ�ǿ������ 1ǿ�� 0��ǿ��
    private String iosIsForce;


    public String getAndroidVersion() {
        return androidVersion;
    }

    public void setAndroidVersion(String androidVersion) {
        this.androidVersion = androidVersion;
    }

    public String getAndroidVersionInfo() {
        return androidVersionInfo;
    }

    public void setAndroidVersionInfo(String androidVersionInfo) {
        this.androidVersionInfo = androidVersionInfo;
    }

    public String getAndroidDownloadUrl() {
        return androidDownloadUrl;
    }

    public void setAndroidDownloadUrl(String androidDownloadUrl) {
        this.androidDownloadUrl = androidDownloadUrl;
    }

    public String getAndroidIsForce() {
        return androidIsForce;
    }

    public void setAndroidIsForce(String androidIsForce) {
        this.androidIsForce = androidIsForce;
    }

    public String getiOSVersion() {
        return iOSVersion;
    }

    public void setiOSVersion(String iOSVersion) {
        this.iOSVersion = iOSVersion;
    }

    public String getiOSVersionInfo() {
        return iOSVersionInfo;
    }

    public void setiOSVersionInfo(String iOSVersionInfo) {
        this.iOSVersionInfo = iOSVersionInfo;
    }

    public String getiOSDownloadUrl() {
        return iOSDownloadUrl;
    }

    public void setiOSDownloadUrl(String iOSDownloadUrl) {
        this.iOSDownloadUrl = iOSDownloadUrl;
    }

    public String getIosIsForce() {
        return iosIsForce;
    }

    public void setIosIsForce(String iosIsForce) {
        this.iosIsForce = iosIsForce;
    }
}
