package com.loan.common.exception;

/**
 * FileName: com.loan.common.exception.BusinessException.java
 * Author: wangyingjie
 * Email: m13803851175@163.com
 * Date: 2017/1/3 17:10
 * Description:
 * History:
 * <Author>      <Time>    <version>    <desc>
 * wangyingjie   17:10    1.0          Create
 */
public class BusinessException extends LoanException{

    private int code;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public BusinessException() {
        super();
    }

    public BusinessException(int code) {
        super();
        this.code = code;
    }

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(int code, String message) {
        super(message);
        this.code = code;
    }

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }

    public BusinessException(Throwable cause) {
        super(cause);
    }

    public BusinessException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}