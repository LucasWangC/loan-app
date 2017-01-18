package com.loan.common.mybatis;

import java.util.Comparator;

/**
 * Author: wangyingjie
 * Email: m13803851175@163.com
 * Date: 2016/12/30 10:43
 * Description:
 * History:
 * <Author>      <Time>    <version>    <desc>
 * wangyingjie   10:43    1.0          Create
 */
public class EntityListenerComparator implements Comparator<EntityListener> {

    @Override
    public int compare(EntityListener o1, EntityListener o2) {
        if( null == o1 || null == o2){
            return 0;
        }
        return Integer.compare(o1.getOrder(),o2.getOrder());
    }
}
