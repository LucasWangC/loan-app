package com.loan.common.dao;

import com.loan.common.model.Banner;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BannerDao extends BaseDao<Banner, Integer> {


    /**
     * 根据banner类型查询banner
     * @param type
     * @return
     */
    public List<Banner> findBannerByDisabled(@Param("type") String type);
}