package com.loan.common.exception;

/**
 * FileName: com.loan.common.exception.LoanRuntimeException.java
 * Author: wangyingjie
 * Email: m13803851175@163.com
 * Date: 2017/1/3 17:10
 * Description:
 * History:
 * <Author>      <Time>    <version>    <desc>
 * wangyingjie   17:10    1.0          Create
 */
public class LoanRuntimeException extends RuntimeException{
    public LoanRuntimeException() {
    }

    public LoanRuntimeException(String message) {
        super(message);
    }

    public LoanRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public LoanRuntimeException(Throwable cause) {
        super(cause);
    }

    public LoanRuntimeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}