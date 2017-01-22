package com.loan.common.model;

import java.io.Serializable;


public class BusinessUser implements Serializable{

    /** 主键 */
    private Long id;
    /** 手机号 */
    private String phoneNo;
    /** 商户code */
    private String businessCode;
    /** 用户在商户平台的唯一ID */
    private String busUserId;
    /** 用户在商户平台注册时间 */
    private Long registerTime;
    /** 创建时间 */
    private Integer createTime;
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

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getBusinessCode() {
        return businessCode;
    }

    public void setBusinessCode(String businessCode) {
        this.businessCode = businessCode;
    }

    public String getBusUserId() {
        return busUserId;
    }

    public void setBusUserId(String busUserId) {
        this.busUserId = busUserId;
    }

    public Long getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(Long registerTime) {
        this.registerTime = registerTime;
    }

    public Integer getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Integer createTime) {
        this.createTime = createTime;
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
        return "BusinessUser{" +
                    "id=" + id +
                    ", phoneNo='" + phoneNo + "\'" +
                    ", businessCode='" + businessCode + "\'" +
                    ", busUserId='" + busUserId + "\'" +
                    ", registerTime=" + registerTime +
                    ", createTime=" + createTime +
                    ", extend1='" + extend1 + "\'" +
                    ", extend2='" + extend2 + "\'" +
                    ", extend3='" + extend3 + "\'" +
                    ", extend4='" + extend4 + "\'" +
                    ", extend5='" + extend5 + "\'" +
                    "}";
    }
}