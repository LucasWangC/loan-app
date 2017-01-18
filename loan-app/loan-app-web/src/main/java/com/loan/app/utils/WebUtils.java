package com.loan.app.utils;

import com.google.common.base.Strings;
import com.loan.app.listener.FileUploadListener;
import com.loan.app.session.ShareSession;
import org.slf4j.Logger;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Copyright (C), 2011-2015 ���ݴ�
 * FileName: com.loan.app.utils.WebUtils.java
 * Author: wangyingjie
 * Email: m13803851175@163.com
 * Date: 2017/1/3 15:58
 * Description:
 * History:
 * <Author>      <Time>    <version>    <desc>
 * wangyingjie   15:58    1.0          Create
 */
public class WebUtils {

    private static final Logger logger = org.slf4j.LoggerFactory.getLogger(ShareSession.class);
    private static final long interval = 200l;

    public static void fileUploadProgress(HttpServletRequest request,HttpServletResponse response,String callback)  {

        fileUploadProgress(request, response, callback,WebUtils.interval);
    }

    public static void fileUploadProgress(HttpServletRequest request,HttpServletResponse response,String callback,long interval){

        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");

        PrintWriter printWriter =null ;
        try {
            response.reset();
            printWriter=response.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Ĭ�Ͻű�(��ʾ���100%)
        String script="<script> try{ " + callback + "(1,1,0) }catch(e){ console.log(e) } </script>";

        if( MultipartResolver.isUploading(request) ){//�Ƿ������ϴ���

            if( !MultipartResolver.isNative(request) ){ //����ԭʼ�ϴ��ļ��ķ�����
                try {
                    //�ض�����ͬURL(  ȥ�����������ڵ��ȡ�ϴ����� )
                    response.sendRedirect(request.getRequestURL().toString());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            //��ȡ������
            FileUploadListener listener = MultipartResolver.findFileUploadProgressListener(request, new FileUploadListener());

            if( null != listener ){//��� listener ������,˵��������ϴ�(���μ���Ƿ�����ϴ�)

                while (!listener.isComplete()){
                    script = "<script> try{ " + callback + "(" + listener.getBytesRead() + "," + listener.getContentLength() + "," + listener.getItems() + ") }catch(e){ console.log(e) } </script>";
                    printWriter.println(script);
                    printWriter.flush();
                    ThreadUtils.sleep(interval);
                }
                script = "<script> try{ " + callback + "(" + listener.getContentLength() + "," + listener.getContentLength() + "," + listener.getItems() + ") }catch(e){ console.log(e) } </script>";
            }
        }

        printWriter.println(script);
        printWriter.flush();
    }

    public static HttpServletRequest getRequest(){
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if( null != requestAttributes && null != requestAttributes.getRequest()){
            return requestAttributes.getRequest();
        }
        return (HttpServletRequest)HolderUtils.getRequest();
    }

    public static HttpSession getSession(){

        HttpServletRequest request =getRequest();
        if( null != request )
            return getRequest().getSession();
        return null;
    }

    public static Object getSessionAttribute(String key){
        HttpSession session = getSession();
        if( null != session )
            return  session.getAttribute(key);
        return null;
    }

    public static boolean isShareSession(){
        return getSession() instanceof ShareSession;
    }

    public static String getShareSessionDomain(){
        HttpSession session = getSession();
        if( session instanceof ShareSession ){
            return (String)session.getAttribute(ShareSession.domainKey);
        }else if(logger.isWarnEnabled()){
            logger.warn("û����������session");
        }
        return null;
    }

    public static String getParameter(ServletRequest servletRequest,String paramName,String defaultValue){
        String value=servletRequest.getParameter(paramName);
        if( Strings.isNullOrEmpty(value) )
            return defaultValue;
        else
            return value;
    }
}
