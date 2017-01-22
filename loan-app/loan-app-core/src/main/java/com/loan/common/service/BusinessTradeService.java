package com.loan.common.service;

import com.loan.common.exception.LoanException;

/**
 * FileName: com.loan.common.service.BusinessTradeService.java
 * Author: lucasWang
 * Email: m13803851175@163.com
 * Date: 2017/1/22 16:25
 * Description:
 * History:
 * <Author>      <Time>    <version>    <desc>
 * wangyingjie   16:25    1.0          Create
 */
public interface BusinessTradeService {


    /**
     *  用户注册通知
     * @param result
     * @return
     */
    public Object registerNotify(String result) throws LoanException;

    /**
     *  用户借款通知
     * @param result
     * @return
     */
    public Object borrowNotify(String result) throws LoanException;
}
