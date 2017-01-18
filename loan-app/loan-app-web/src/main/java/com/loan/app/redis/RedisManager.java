package com.loan.app.redis;

import org.springframework.data.redis.core.BoundHashOperations;

import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Copyright (C), 2011-2015 ���ݴ�
 * FileName: com.loan.app.redis.RedisManager.java
 * Author: wangyingjie
 * Email: m13803851175@163.com
 * Date: 2017/1/3 16:11
 * Description:
 * History:
 * <Author>      <Time>    <version>    <desc>
 * wangyingjie   16:11    1.0          Create
 */
public class RedisManager {

    /**
     * ��ѯ�����µļ�����
     * @param domainKey
     * @return
     */
    public static String[] keys(String domainKey) {
        return(String[]) RedisUtils.boundHashOps(domainKey).keys().toArray(new String[]{});
    }

    /**
     * ���������µļ�ֵ
     * @param domainKey ����
     * @param key   ��
     * @param value ֵ
     */
    public static void put(String domainKey, String key, Object value) {
        boundHashOps(domainKey).put(key,value);
    }

    public static void delete(String domainKey, String key) {
        boundHashOps(domainKey).delete(key);
    }

    public static void invalidate(String domainKey) {
        RedisUtils.delete(domainKey);
    }

    public static Object get(String domainKey, String key) {
        return boundHashOps(domainKey).get(key);
    }

    public static <T> T get(String domainKey, String key, T defaultValue) {
        Object value = boundHashOps(domainKey).get(key);
        if( null == value )
            return defaultValue;
        return (T) value;
    }

    public static Map<String, Object> entries(String domainKey) {
        return boundHashOps(domainKey).entries();
    }

    public static void expire(String domainKey,long timeout, TimeUnit timeUnit) {
        boundHashOps(domainKey).expire( timeout ,timeUnit );
    }

    public static double increment(String domainKey,String key,double delta) {
        return boundHashOps(domainKey).increment(key,delta);
    }

    public static boolean hasKey(String domainKey) {
        return RedisUtils.hasKey(domainKey);
    }

    private static BoundHashOperations<String ,String, Object> boundHashOps(String domainKey){
        BoundHashOperations<String ,String, Object> boundHashOps = (BoundHashOperations) RedisUtils.boundHashOps(domainKey);
        return boundHashOps;
    }
}
