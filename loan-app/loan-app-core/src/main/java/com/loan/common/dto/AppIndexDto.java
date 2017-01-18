package com.loan.common.dto;

import com.loan.common.model.Banner;

import java.io.Serializable;
import java.util.List;

/**
 * Copyright (C), 2016-2020 助你贷
 * FileName: com.loan.common.dto.AppIndexDto.java
 * Author: lucasWang
 * Email: m13803851175@163.com
 * Date: 2017/1/8 13:44
 * Description:
 * History:
 * <Author>      <Time>    <version>    <desc>
 * lucasWang   13:44    1.0          Create
 */
public class AppIndexDto implements Serializable{

    //banner集合
    private List<Banner> banners;
    //推荐
    private List<IndexBussinessDto> recommends;
    //猜你需要
    private List<IndexBussinessDto> likes;

    public List<Banner> getBanners() {
        return banners;
    }

    public void setBanners(List<Banner> banners) {
        this.banners = banners;
    }

    public List<IndexBussinessDto> getRecommends() {
        return recommends;
    }

    public void setRecommends(List<IndexBussinessDto> recommends) {
        this.recommends = recommends;
    }

    public List<IndexBussinessDto> getLikes() {
        return likes;
    }

    public void setLikes(List<IndexBussinessDto> likes) {
        this.likes = likes;
    }
}
