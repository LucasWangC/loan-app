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
public class MysqlDialect implements Dialect{

    @Override
    public String pageSql(String sql, Page page) {

        StringBuffer querySql =
                new StringBuffer("SELECT * FROM ( ").append(sql).append(" ) AS COUNT_PAGE")
                        .append(" LIMIT ").append( page.getStart() ).append(" , ").append(page.getPageSize());
        return querySql.toString();
    }
}
