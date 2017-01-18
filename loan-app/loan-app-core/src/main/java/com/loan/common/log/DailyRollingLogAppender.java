package com.loan.common.log;

import org.apache.log4j.DailyRollingFileAppender;
import org.apache.log4j.Priority;

/**
 * Copyright (C), 2016-2020 助你贷
 * FileName: com.loan.common.log.DailyRollingLogAppender.java
 * Author: lucasWang
 * Email: m13803851175@163.com
 * Date: 2017/1/11 22:45
 * Description:
 * History:
 * <Author>      <Time>    <version>    <desc>
 * lucasWang   22:45    1.0          Create
 */
public class DailyRollingLogAppender extends DailyRollingFileAppender {

    @Override
    public boolean isAsSevereAsThreshold(Priority priority) {

        //只判断是否相等，而不判断优先级
        return this.getThreshold() == null || priority.equals(this.getThreshold());
    }
}
