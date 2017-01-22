package com.loan.app.web.controller;

import com.loan.app.utils.CommonUtils;
import com.loan.common.exception.LoanException;
import com.loan.common.service.BusinessTradeService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.TimeUnit;

/**
 * FileName: com.loan.app.web.controller.AppTradeController.java
 * Author: lucasWang
 * Email: m13803851175@163.com
 * Date: 2017/1/22 15:09
 * Description:
 * History:
 * <Author>      <Time>    <version>    <desc>
 * wangyingjie   15:09    1.0          Create
 */
@RequestMapping("trade")
@Controller
public class AppTradeController {

    private static final Logger logger = Logger.getLogger(AppTradeController.class);

    @Autowired
    private BusinessTradeService businessTradeService;

    /**
     * 商户注册通知
     * @param request
     * @param response
     * @return
     * @throws com.loan.common.exception.LoanException
     */
    @RequestMapping("/register")
    public void registerNotify(HttpServletRequest request,HttpServletResponse response)
            throws LoanException {
        logger.info("//==================商户注册通知开始===============================//");
        String notifyInfo = CommonUtils.readRequestStream(request, null);
        logger.info("//==================商户注册通知报文:"+notifyInfo+"===============================//");
        Object result = businessTradeService.registerNotify(notifyInfo);
        CommonUtils.writeResponse(response, result, null);
        logger.info("//==================商户注册通知结束===============================//");
    }

    /**
     * 商户借款通知
     * @param request
     * @param response
     * @return
     * @throws com.loan.common.exception.LoanException
     */
    @RequestMapping("/borrow")
    public void borrowNotify(HttpServletRequest request,HttpServletResponse response)
            throws LoanException {
        logger.info("//==================商户借款通知开始===============================//");
        String notifyInfo = CommonUtils.readRequestStream(request, null);
        logger.info("//==================商户借款通知报文:"+notifyInfo+"===============================//");
        Object result = businessTradeService.borrowNotify(notifyInfo);
        CommonUtils.writeResponse(response, result, null);
        logger.info("//==================商户借款通知结束===============================//");
    }
}
