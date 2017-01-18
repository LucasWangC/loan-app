package com.loan.app.utils;

import org.springframework.core.NamedThreadLocal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Copyright (C), 2011-2015 ���ݴ�
 * FileName: com.loan.app.utils.HolderUtils.java
 * Author: wangyingjie
 * Email: m13803851175@163.com
 * Date: 2017/1/3 16:30
 * Description:
 * History:
 * <Author>      <Time>    <version>    <desc>
 * wangyingjie   16:30    1.0          Create
 */
public class HolderUtils {

    private static final ThreadLocal<HttpServletRequest> requestHolder = new NamedThreadLocal<HttpServletRequest>("native request");
    private static final ThreadLocal<HttpServletResponse> responseHolder = new NamedThreadLocal<HttpServletResponse>("native response");

    public static void setRequest(HttpServletRequest servletRequest){
        requestHolder.set(servletRequest);
    }

    public static void setResponse(HttpServletResponse servletResponse){
        responseHolder.set(servletResponse);
    }

    public static HttpServletRequest getRequest() {
        return requestHolder.get();
    }

    public static HttpServletResponse getResponse() {
        return responseHolder.get();
    }
}
