package com.loan.app.utils;

import com.alibaba.fastjson.JSON;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;

/**
 * FileName: com.loan.app.utils.CommonUtils.java
 * Author: lucasWang
 * Email: m13803851175@163.com
 * Date: 2017/1/22 15:23
 * Description:
 * History:
 * <Author>      <Time>    <version>    <desc>
 * wangyingjie   15:23    1.0          Create
 */
public class CommonUtils {

    private static final Logger logger = Logger.getLogger(CommonUtils.class);

    /**
     * 读取request流
     * @param request
     * @param charset 编码格式
     * @return
     * @author autumn
     */
    public static String readRequestStream(HttpServletRequest request,String charset) {
        charset =  charset == null ? "UTF-8" : charset;
        BufferedReader reader = null;
        StringBuilder sb = new StringBuilder();
        try {
            reader = new BufferedReader(new InputStreamReader(request.getInputStream(), charset));
            String line = null;

            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        } finally {
            try {
                if (null != reader) {
                    reader.close();
                }
            } catch (IOException e) {
                logger.error(e.getMessage(), e);
            }
        }
        return sb.toString();
    }


    /**
     * 响应输出
     * @param response
     * @param resultObj 输出对象
     * @param charset 编码格式
     */
    public static void writeResponse(HttpServletResponse response,Object resultObj,String charset){


        if(resultObj == null) return;

        charset =  charset == null ? "UTF-8" : charset;
        String responseContent = null;

        if(resultObj.getClass() == String.class){
            response.setCharacterEncoding("UTF-8");
            response.setContentType("text/html;charset="+charset);
            responseContent = (String)resultObj;
        }else{
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json;charset="+charset);
            responseContent = JSON.toJSONString(resultObj);
        }

        OutputStream outputStream = null;
        try {
            outputStream = response.getOutputStream();
            outputStream.write(responseContent.getBytes("UTF-8"));
            outputStream.flush();
        }catch (IOException e){
            logger.error(e.getMessage(), e);
        }finally {
            if(outputStream != null){
                try {
                    outputStream.close();
                } catch (IOException e) {
                    logger.error(e.getMessage(), e);
                }
            }
        }
    }
}
