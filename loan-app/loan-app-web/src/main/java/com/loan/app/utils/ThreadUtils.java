package com.loan.app.utils;

/**
 * Copyright (C), 2011-2015 ���ݴ�
 * FileName: com.loan.app.utils.ThreadUtils.java
 * Author: wangyingjie
 * Email: m13803851175@163.com
 * Date: 2017/1/3 16:31
 * Description:
 * History:
 * <Author>      <Time>    <version>    <desc>
 * wangyingjie   16:31    1.0          Create
 */
public class ThreadUtils {

    public static void sleep(Long millis){
        try{
            Thread.sleep(millis);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
