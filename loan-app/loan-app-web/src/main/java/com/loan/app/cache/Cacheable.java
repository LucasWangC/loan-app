package com.loan.app.cache;

import java.util.concurrent.TimeUnit;

/**
 * Copyright (C), 2011-2015 ���ݴ�
 * FileName: com.loan.app.cache.Cacheable.java
 * Author: wangyingjie
 * Email: m13803851175@163.com
 * Date: 2017/1/3 16:07
 * Description:
 * History:
 * <Author>      <Time>    <version>    <desc>
 * wangyingjie   16:07    1.0          Create
 */
public interface Cacheable {

    String[] keys(String domainKey) ;

    void put(String domainKey, String key, Object o);

    void delete(String domainKey,String key);

    void invalidate(String domainKey);

    Object get(String domainKey, String key);

    <T> T get(String domainKey, String key, T defaultValue);

    java.util.Map<String,Object> entries(String domainKey);

    void expire(String domainKey,long timeOut,TimeUnit timeUnit);

    boolean hasKey(String domainKey);
}
