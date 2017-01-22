package com.loan.common.utils;

import org.apache.commons.lang.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * FileName: com.loan.common.utils.IpUtils.java
 * Author: wangyingjie
 * Email: m13803851175@163.com
 * Date: 2017/1/20 18:03
 * Description:
 * History:
 * <Author>      <Time>    <version>    <desc>
 * wangyingjie   18:03    1.0          Create
 */
public class IpUtils {

    /**
     * ��ȡ������IP
     *
     * ��һ�������ʹ��Request.getRemoteAddr()���ɣ����Ǿ���nginx�ȷ��������������������ʧЧ��
     *
     * �������ȴ�Header�л�ȡX-Real-IP������������ٴ�X-Forwarded-For��õ�һ��IP(��,�ָ�)��
     * ����������������Request .getRemoteAddr()��
     *
     * @param request
     * @return
     */
    public static String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("X-Real-IP");
        if (!StringUtils.isBlank(ip) && !"unknown".equalsIgnoreCase(ip)) {
            return ip;
        }
        ip = request.getHeader("X-Forwarded-For");
        if (!StringUtils.isBlank(ip) && !"unknown".equalsIgnoreCase(ip)) {
            // ��η���������ж��IPֵ����һ��Ϊ��ʵIP��
            int index = ip.indexOf(',');
            if (index != -1) {
                return ip.substring(0, index);
            } else {
                return ip;
            }
        } else {
            return request.getRemoteAddr();
        }
    }
}
