package com.loan.common.dataSource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * FileName: com.loan.common.dataSource.MultipleDataSource.java
 * Author: wangyingjie
 * Email: m13803851175@163.com
 * Date: 2016/12/30 11:04
 * Description:
 * History:
 * <Author>      <Time>    <version>    <desc>
 * wangyingjie   11:04    1.0          Create
 */
public class MultipleDataSource extends AbstractRoutingDataSource {
    public static final String WRITE_DATASOURCE_KEY = "writeDataSource";
    public static final String READONLY__DATASOURCE_KEY = "readOnlyDataSource";

    private static final ThreadLocal<String> dataSourceKey = new InheritableThreadLocal<String>();

    public static void setDataSourceKey(String dataSource) {
        dataSourceKey.set(dataSource);
    }

    @Override
    protected Object determineCurrentLookupKey() {

        return dataSourceKey.get();
    }
}
