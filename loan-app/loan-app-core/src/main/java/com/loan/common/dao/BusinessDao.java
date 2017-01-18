package com.loan.common.dao;

import com.loan.common.model.Business;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BusinessDao extends BaseDao<Business, Long> {


    /**
     * 查询首页商户信息
     * @return
     */
    public List<Business> findIndexBusInfo();
}