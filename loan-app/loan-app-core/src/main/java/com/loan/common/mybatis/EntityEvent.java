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
public class EntityEvent<T> extends java.util.EventObject{

    public enum TriggerType{
        UNKNOWN, INSERT, UPDATE, DELETE, SELECT
    }

    private TriggerType triggerType;


    public EntityEvent(T source,TriggerType triggerType) {
        super(source);
        this.triggerType= triggerType;
    }

    public TriggerType getTriggerType() {
        return triggerType;
    }

    @Override
    public T getSource() {
        return (T)super.getSource();
    }
}

