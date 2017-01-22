package com.loan.common.dao;

import com.loan.common.model.Appinfo;
import org.springframework.stereotype.Repository;

@Repository
public interface AppinfoDao extends BaseDao<Appinfo, Integer> {

    /**
     * 查找安卓的版本号
     * @return
     */
    public Appinfo findAndroidVersion();

    /**
     * 查找IOS的版本号
     * @return
     */
    public Appinfo findIOSVersion();

}