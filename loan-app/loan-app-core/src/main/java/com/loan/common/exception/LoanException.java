package com.loan.common.exception;

/**
 * FileName: com.loan.common.exception.LoanException.java
 * Author: wangyingjie
 * Email: m13803851175@163.com
 * Date: 2017/1/3 17:08
 * Description:
 * History:
 * <Author>      <Time>    <version>    <desc>
 * wangyingjie   17:08    1.0          Create
 */
public class LoanException extends Exception {

    public LoanException() {
        super();
    }

    public LoanException(String message) {
        super(message);
    }

    public LoanException(String message, Throwable cause) {
        super(message, cause);
    }

    public LoanException(Throwable cause) {
        super(cause);
    }

    public LoanException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
