package com.loan.app.web.resolver;

import com.alibaba.fastjson.JSON;
import com.loan.app.vo.BaseResponse;
import com.loan.common.enums.ResponseCode;
import com.loan.common.exception.BusinessException;
import com.loan.common.exception.LoanException;
import com.loan.common.exception.NoLoginException;
import com.loan.common.exception.LoanRuntimeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Copyright (C), 2011-2015 ���ݴ�
 * FileName: com.loan.app.web.resolver.UnifiedExceptionResolver.java
 * Author: wangyingjie
 * Email: m13803851175@163.com
 * Date: 2016/12/30 11:19
 * Description:
 * History:
 * <Author>      <Time>    <version>    <desc>
 * wangyingjie   11:19    1.0          Create
 */
public class UnifiedExceptionResolver extends SimpleMappingExceptionResolver {

    private static final Logger logger = LoggerFactory.getLogger(UnifiedExceptionResolver.class);
    private static final String callbackKey = "callback";
    private boolean returnJson = true;
    private String defaultMessage = "服务器繁忙，请稍后再试!";

    @Override
    protected ModelAndView doResolveException(HttpServletRequest request, HttpServletResponse response,
                                              Object handler, Exception ex) {

        if (returnJson) {//json返回

            String callback = request.getParameter(callbackKey);
            if(ex instanceof NoLoginException){
                writeJson(response, callback,
                        BaseResponse.newInstance(ResponseCode.NO_LOGIN.getCode(),ResponseCode.NO_LOGIN.getMsg()));

            }else if(ex instanceof BusinessException){
                writeJson(response, callback,
                        BaseResponse.newInstance(String.valueOf(((BusinessException)ex).getCode()),ex.getMessage()));

            }else if (ex instanceof LoanException) {
                writeJson(response, callback,
                        BaseResponse.newInstance(ResponseCode.FAILURE.getCode(), ex.getMessage()));

            }else if (ex instanceof LoanRuntimeException) {
                logger.error(ex.getMessage(), ex);
                writeJson(response, callback,
                        BaseResponse.newInstance(ResponseCode.SYSTEM_ERROR.getCode(), ResponseCode.SYSTEM_ERROR.getMsg()));

            }else if (ex instanceof MaxUploadSizeExceededException) {
                writeScript(response, "alert('上传文件过大')");
                response.setStatus(413);

            }else{
                logger.error(ex.getMessage(), ex);
                writeJson(response, callback,
                        BaseResponse.newInstance(String.valueOf(ResponseCode.SYSTEM_ERROR.getCode()),
                                ResponseCode.SYSTEM_ERROR.getMsg()));
            }

            return new ModelAndView();
        } else {//普通返回

            return super.doResolveException(request, response, handler, ex);
        }

    }

    /**
     * json 结果返回
     *
     * @param response
     * @param callback
     * @param obj
     */
    private void writeJson(HttpServletResponse response, String callback, BaseResponse obj) {
        try {
            String json = JSON.toJSONString(obj);
            if (callback == null) {
                response.setContentType("application/json;charset=UTF-8");
                response.getWriter().write(json);
            } else {
                response.setContentType("application/javascript;charset=UTF-8");
                response.getWriter().write(callback + "(" + json + ")");
            }

            response.setStatus(200);
        } catch (Exception e) {
            logger.warn(e.getMessage());
        }
    }

    /**
     * 脚本信息
     *
     * @param response
     */
    private void writeScript(HttpServletResponse response, String msg) {
        try {
            response.getWriter().write("<script>" + msg + "</script>");
            response.setContentType("text/javascript;charset=UTF-8");
            response.setStatus(200);
        } catch (Exception e) {
            logger.warn(e.getMessage());
        }
    }

    /**
     * 文本信息
     *
     * @param response
     */
    private void writeString(HttpServletResponse response, String msg) {
        try {
            response.getWriter().write(msg);
            response.setContentType("text/html;charset=UTF-8");
            response.setStatus(200);
        } catch (Exception e) {
            logger.warn(e.getMessage());
        }
    }

    public boolean isReturnJson() {
        return returnJson;
    }

    public void setReturnJson(boolean returnJson) {
        this.returnJson = returnJson;
    }

    public String getDefaultMessage() {
        return defaultMessage;
    }

    public void setDefaultMessage(String defaultMessage) {
        this.defaultMessage = defaultMessage;
    }
}

