package com.loan.app.cache;

import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Copyright (C), 2011-2015 ÎÂÖÝ´û
 * FileName: com.loan.app.cache.MockCache.java
 * Author: wangyingjie
 * Email: m13803851175@163.com
 * Date: 2017/1/3 16:07
 * Description:
 * History:
 * <Author>      <Time>    <version>    <desc>
 * wangyingjie   16:07    1.0          Create
 */
public class MockCache implements Cacheable{

    @Override
    public String[] keys(String domainKey) {
        return new String[0];
    }

    @Override
    public void put(String domainKey, String key, Object o) {

    }

    @Override
    public void delete(String domainKey, String key) {

    }

    @Override
    public void invalidate(String domainKey) {

    }

    @Override
    public Object get(String domainKey, String key) {
        return null;
    }

    @Override
    public <T> T get(String domainKey, String key, T defaultValue) {
        return null;
    }

    @Override
    public Map<String, Object> entries(String domainKey) {
        return null;
    }

    @Override
    public void expire(String domainKey, long timeOut, TimeUnit timeUnit) {

    }

    @Override
    public boolean hasKey(String domainKey) {
        return true;
    }
}
