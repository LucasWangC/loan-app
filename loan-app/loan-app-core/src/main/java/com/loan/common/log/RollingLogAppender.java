package com.loan.common.log;

import org.apache.log4j.Priority;
import org.apache.log4j.RollingFileAppender;

/**
 * Copyright (C), 2016-2020 助你贷
 * FileName: com.loan.common.log.RollingLogAppender.java
 * Author: lucasWang
 * Email: m13803851175@163.com
 * Date: 2017/1/11 22:47
 * Description:
 * History:
 * <Author>      <Time>    <version>    <desc>
 * lucasWang   22:47    1.0          Create
 */
public class RollingLogAppender extends RollingFileAppender {

    @Override
    public boolean isAsSevereAsThreshold(Priority priority) {

        //只判断是否相等，而不判断优先级
        return this.getThreshold() == null || priority.equals(this.getThreshold());
    }
}
