package com.loan.common.mybatis;

/**
 * Author: wangyingjie
 * Email: m13803851175@163.com
 * Date: 2016/12/30 10:43
 * Description:
 * History:
 * <Author>      <Time>    <version>    <desc>
 * wangyingjie   10:43    1.0          Create
 */
public interface EntityListener<T> extends java.util.EventListener{

    public void beforeChange(EntityEvent<T> event);
    public void afterChange(EntityEvent<T> event);

    public int getOrder();
}

