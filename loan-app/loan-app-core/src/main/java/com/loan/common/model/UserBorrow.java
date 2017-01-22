package com.loan.common.model;

import java.io.Serializable;
import java.math.BigDecimal;


public class UserBorrow implements Serializable{

    /** 主键 */
    private Long id;
    /** 手机号 */
    private String phoneNo;
    /** 商户code */
    private String businessCode;
    /** 商户平台用户ID */
    private String busUserid;
    /** 用户在商户平台借款的订单号 */
    private String busOrderNo;
    /** 用户借款金额 */
    private BigDecimal busOrderMoney;
    /** 用户在商户平台借款时间 */
    private Long busOrderTime;
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

    public String getBusUserid() {
        return busUserid;
    }

    public void setBusUserid(String busUserid) {
        this.busUserid = busUserid;
    }

    public String getBusOrderNo() {
        return busOrderNo;
    }

    public void setBusOrderNo(String busOrderNo) {
        this.busOrderNo = busOrderNo;
    }

    public BigDecimal getBusOrderMoney() {
        return busOrderMoney;
    }

    public void setBusOrderMoney(BigDecimal busOrderMoney) {
        this.busOrderMoney = busOrderMoney;
    }

    public Long getBusOrderTime() {
        return busOrderTime;
    }

    public void setBusOrderTime(Long busOrderTime) {
        this.busOrderTime = busOrderTime;
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
        return "UserBorrow{" +
                    "id=" + id +
                    ", phoneNo='" + phoneNo + "\'" +
                    ", businessCode='" + businessCode + "\'" +
                    ", busUserid='" + busUserid + "\'" +
                    ", busOrderNo='" + busOrderNo + "\'" +
                    ", busOrderMoney=" + busOrderMoney +
                    ", busOrderTime=" + busOrderTime +
                    ", createTime=" + createTime +
                    ", extend1='" + extend1 + "\'" +
                    ", extend2='" + extend2 + "\'" +
                    ", extend3='" + extend3 + "\'" +
                    ", extend4='" + extend4 + "\'" +
                    ", extend5='" + extend5 + "\'" +
                    "}";
    }
}