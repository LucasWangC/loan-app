package com.loan.common.enums;

/**
 * FileName: com.loan.common.enums.ResponseCode.java
 * Author: wangyingjie
 * Email: m13803851175@163.com
 * Date: 2017/1/3 15:56
 * Description:
 * History:
 * <Author>      <Time>    <version>    <desc>
 * wangyingjie   15:56    1.0          Create
 */
public enum  ResponseCode {

    SUCCESS("1","请求数据成功"),    //成功返回数据
    FAILURE("0","请求数据失败"),    //业务异常，数据返回失败
    PARAM_EXCEPTION("-1","请求参数错误"),  //请求参数错误
    NO_LOGIN("-99","未登录"),      //登录错误
    SYSTEM_ERROR("-100","服务器内部错误");    //服务器内部异常

    private String code;
    private String msg;

    ResponseCode(String code,String msg){
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
