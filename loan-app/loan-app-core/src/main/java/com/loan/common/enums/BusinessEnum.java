package com.loan.common.enums;

/**
 * Copyright (C), 2016-2020 助你贷
 * FileName: com.loan.common.enums.LabelEnum.java
 * Author: lucasWang
 * Email: m13803851175@163.com
 * Date: 2017/1/12 0:49
 * Description:
 * History:
 * <Author>      <Time>    <version>    <desc>
 * lucasWang   0:49    1.0          Create
 */
public enum BusinessEnum {

    BUSINESS_PPD("ppd","拍拍贷"),
    BUSINESS_XJBK("xjbk","现金白卡"),
            ;

    private String code;
    private String desc;

    BusinessEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public static String getlabelDesc(String code){
        for(BusinessEnum labelEnum : BusinessEnum.values()){
            if(code.equals(labelEnum.getCode())){
                return labelEnum.getDesc();
            }
        }

        return "未知状态";
    }
}
