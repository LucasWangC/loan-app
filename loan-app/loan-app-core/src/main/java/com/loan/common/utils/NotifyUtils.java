package com.loan.common.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.log4j.Logger;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * FileName: com.loan.common.utils.NotifyUtils.java
 * Author: lucasWang
 * Email: m13803851175@163.com
 * Date: 2017/1/22 16:44
 * Description:
 * History:
 * <Author>      <Time>    <version>    <desc>
 * wangyingjie   16:44    1.0          Create
 */
public class NotifyUtils {


    private static final Logger logger = Logger.getLogger(NotifyUtils.class);


    /**
     * 验签
     * @param signInfo
     * @param key
     * @return
     */
    public static boolean checkSign(JSONObject reqObj,String key){

        if (reqObj == null)
        {
            return false;
        }

        String sign = reqObj.getString("sign");
        String sign_src = genSignData(reqObj);
        sign_src += "&sign=" + key;
        try
        {
            if (sign.equals(SecurityUtils.getInstance().md5Digest(sign_src.getBytes("utf-8"))))
            {
                logger.info("商户[" + reqObj.getString("bus_code") + "]MD5签名验证通过!");
                return true;
            } else
            {
                logger.info("商户[" + reqObj.getString("bus_code")+ "]MD5签名验证未通过!");
                return false;
            }
        } catch (UnsupportedEncodingException e)
        {
            return false;
        }
    }


    /**
     * 注册检查签名
     *
     * @param signInfo  签名字串
     * @return
     */
    public static boolean registerCheckSign(String signInfo,String key) {

        JSONObject info = JSON.parseObject(NotifyUtils.getJsonStr(signInfo));

        String signSrc = "bus_code="+info.get("bus_code")+"&user_id="+info.get("user_id")+
                "&phone_no="+info.get("phone_no")+"&register_time="+info.get("register_time")
                +"&key="+key;

        String v_md5str = info.getString("sign");		// MD5校验码

        String v_md5text = Md5Util.getInstance().getMD5ofStr(signSrc).toUpperCase();
        if (v_md5str.equals(v_md5text)){
            return true;
        }

        return false;
    }

    /**
     * 借款检查签名
     *
     * @param signInfo  签名字串
     * @return
     */
    public static boolean borrowCheckSign(String signInfo,String key) {

        JSONObject info = JSON.parseObject(NotifyUtils.getJsonStr(signInfo));

        String signSrc = "bus_code="+info.get("bus_code")+"&user_id="+info.get("user_id")+
                "&phone_no="+info.get("phone_no")+"&bus_order_no="+info.get("bus_order_no")
                +"&bus_order_money="+info.get("bus_order_money")+"&bus_order_time="+info.get("bus_order_time")
                +"&key="+key;

        String v_md5str = info.getString("sign");		// MD5校验码

        String v_md5text = Md5Util.getInstance().getMD5ofStr(signSrc).toUpperCase();
        if (v_md5str.equals(v_md5text)){
            return true;
        }

        return false;
    }

    /**
     * 生成待签名串
     * @param jsonObject
     * @return
     * @author
     */
    public static String genSignData(JSONObject jsonObject)
    {
        StringBuffer content = new StringBuffer();

        // 按照key做首字母升序排列
        List<String> keys = new ArrayList<String>(jsonObject.keySet());
        for (int i = 0; i < keys.size(); i++)
        {
            String key = (String) keys.get(i);
            if ("sign".equals(key))
            {
                continue;
            }
            String value = jsonObject.getString(key);
            // 空串不参与签名
            if (isnull(value))
            {
                continue;
            }
            content.append((i == 0 ? "" : "&") + key + "=" + value);

        }
        String signSrc = content.toString();
        if (signSrc.startsWith("&"))
        {
            signSrc = signSrc.replaceFirst("&", "");
        }
        return signSrc;
    }

    /**
     * str空判断
     * @param str
     * @return
     * @author
     */
    public static boolean isnull(String str)
    {
        if (null == str || str.equalsIgnoreCase("null") || str.equals(""))
        {
            return true;
        } else
            return false;
    }

    /**
     * 获取json串
     * @param sign
     * @return
     */
    public static String getJsonStr(String sign){
        String si = sign.replaceAll("=","\":\"");
        String signs [] = si.split("&");
        String jsonStr = "";
        for(int i=0;i<signs.length;i++){
            jsonStr += signs[i]+",";
        }
        String mm = jsonStr.substring(0, jsonStr.length() - 1);
        String js = "{\""+mm+"\"}";
        String toJson = js.replaceAll(",","\",\"");
        return toJson;
    }

}
