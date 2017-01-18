package com.loan.app.vo;

import com.loan.app.utils.WebUtils;
import com.loan.common.enums.ResponseCode;

import javax.servlet.http.HttpSession;

/**
 * Copyright (C), 2011-2015 ÎÂÖÝ´û
 * FileName: com.loan.app.vo.BaseResponse.java
 * Author: wangyingjie
 * Email: m13803851175@163.com
 * Date: 2017/1/3 15:55
 * Description:
 * History:
 * <Author>      <Time>    <version>    <desc>
 * wangyingjie   15:55    1.0          Create
 */
public class BaseResponse {

    private String status;
    private Object data;
    private String detail;
    private String sid;

    public BaseResponse() {
        HttpSession session = WebUtils.getSession();
    }

    public BaseResponse(String status) {
        this();
        this.status = status;
        try {
            this.sid = WebUtils.getSession().getId();
        }catch (Exception e){
        }
    }

    public BaseResponse(String status, Object data) {
        this();
        this.status = status;
        try {
            this.sid = WebUtils.getSession().getId();
        }catch (Exception e){
        }
        this.setData(data);

    }

    private BaseResponse(String status, Object data, String detail) {
        this();
        this.status = status;
        this.detail = detail;
        try {
            this.sid = WebUtils.getSession().getId();
        }catch (Exception e){
        }
        this.setData(data);
    }

    public static BaseResponse success() {
        return newInstance(ResponseCode.SUCCESS.getCode(), "", ResponseCode.SUCCESS.getMsg());
    }

    public static BaseResponse success(Object data) {
        return newInstance(ResponseCode.SUCCESS.getCode(), data, ResponseCode.SUCCESS.getMsg());
    }

    public static BaseResponse error() {
        return newInstance(ResponseCode.SYSTEM_ERROR.getCode(), "", ResponseCode.SYSTEM_ERROR.getMsg());
    }

    public static BaseResponse error(String msg) {
        return newInstance(ResponseCode.SYSTEM_ERROR.getCode(), "", msg);
    }

    public static BaseResponse paramError() {
        return newInstance(ResponseCode.PARAM_EXCEPTION.getCode(), "", ResponseCode.PARAM_EXCEPTION.getMsg());
    }

    public static BaseResponse paramError(String msg) {
        return newInstance(ResponseCode.PARAM_EXCEPTION.getCode(), "", msg);
    }


    public static BaseResponse bussinessError(String msg) {
        return newInstance(ResponseCode.FAILURE.getCode(), "", msg);
    }

    public static BaseResponse bussinessError(Object data, String msg) {
        return newInstance(ResponseCode.FAILURE.getCode(), data, msg);
    }

    public static BaseResponse newInstance(String status) {
        return new BaseResponse(status);
    }

    public static BaseResponse newInstance(String status,String detail) {
        return new BaseResponse(status,"",detail);
    }

    public static BaseResponse newInstance(String status, Object data) {
        return new BaseResponse(status, data);
    }

    public static BaseResponse newInstance(String status, Object data, String detail) {
        return new BaseResponse(status, data, detail);
    }


    public String getStatus() {
        return status;
    }

    public BaseResponse setStatus(String status) {
        if (null == status)
            this.status = "";
        else
            this.status = status;
        return this;
    }

    public Object getData() {
        return data;
    }


    public BaseResponse setData(Object data) {
        this.data = data;
        return this;
    }

    public String getDetail() {
        return detail;
    }

    public BaseResponse setDetail(String message) {
        if (null == message)
            this.detail = "";
        else
            this.detail = message;
        return this;
    }

    public String getSid() {
        return sid;
    }
}
