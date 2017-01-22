package com.loan.common.dao;

import com.loan.common.model.Appinfo;
import org.springframework.stereotype.Repository;

@Repository
public interface AppinfoDao extends BaseDao<Appinfo, Integer> {

    /**
     * ���Ұ�׿�İ汾��
     * @return
     */
    public Appinfo findAndroidVersion();

    /**
     * ����IOS�İ汾��
     * @return
     */
    public Appinfo findIOSVersion();

}