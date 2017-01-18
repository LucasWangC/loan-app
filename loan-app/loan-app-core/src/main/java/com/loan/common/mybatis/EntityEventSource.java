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
public class EntityEventSource {


    public enum triggerType{
        UNKNOWN, INSERT, UPDATE, DELETE, SELECT
    }

    private Object trigger;
    private triggerType triggerType;

    public EntityEventSource(Object trigger, EntityEventSource.triggerType triggerType) {
        this.trigger = trigger;
        this.triggerType = triggerType;
    }

    public Object getTrigger() {
        return trigger;
    }

    public EntityEventSource.triggerType getTriggerType() {
        return triggerType;
    }
}

