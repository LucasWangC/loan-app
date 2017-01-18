package com.loan.app.cache;

import com.loan.app.redis.RedisUtils;
import org.springframework.data.redis.core.BoundHashOperations;

import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Copyright (C), 2011-2015 ÎÂÖÝ´û
 * FileName: com.loan.app.cache.RedisCache.java
 * Author: wangyingjie
 * Email: m13803851175@163.com
 * Date: 2017/1/3 16:08
 * Description:
 * History:
 * <Author>      <Time>    <version>    <desc>
 * wangyingjie   16:08    1.0          Create
 */
public class RedisCache implements Cacheable {
    @Override
    public String[] keys(String domainKey) {

        return(String[]) RedisUtils.boundHashOps(domainKey).keys().toArray(new String[]{});
    }

    @Override
    public void put(String domainKey, String key, Object value) {
        boundHashOps(domainKey).put(key,value);
    }

    @Override
    public void delete(String domainKey, String key) {
        boundHashOps(domainKey).delete(key);
    }

    @Override
    public void invalidate(String domainKey) {
        RedisUtils.delete(domainKey);
    }

    @Override
    public Object get(String domainKey, String key) {
        return boundHashOps(domainKey).get(key);
    }

    @Override
    public <T> T get(String domainKey, String key, T defaultValue) {
        Object value = boundHashOps(domainKey).get(key);
        if( null == value )
            return defaultValue;
        return (T) value;
    }

    @Override
    public Map<String, Object> entries(String domainKey) {
        return boundHashOps(domainKey).entries();
    }

    @Override
    public void expire(String domainKey,long timeout, TimeUnit timeUnit) {
        boundHashOps(domainKey).expire( timeout ,timeUnit );
    }

    @Override
    public boolean hasKey(String domainKey) {
        return RedisUtils.hasKey(domainKey);
    }

    private BoundHashOperations<String ,String, Object> boundHashOps(String domainKey){
        BoundHashOperations<String ,String, Object> boundHashOps =(BoundHashOperations) RedisUtils.boundHashOps(domainKey);
        return boundHashOps;
    }
}
