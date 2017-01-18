package com.loan.common.service.impl;

import com.loan.common.dao.BannerDao;
import com.loan.common.dao.BusinessDao;
import com.loan.common.dto.AppIndexDto;
import com.loan.common.dto.BusinessDto;
import com.loan.common.dto.IndexBussinessDto;
import com.loan.common.enums.BusinessEnum;
import com.loan.common.enums.LabelEnum;
import com.loan.common.exception.LoanException;
import com.loan.common.model.Banner;
import com.loan.common.model.Business;
import com.loan.common.mybatis.Page;
import com.loan.common.service.AppInfoService;
import com.loan.common.utils.ConstantUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * FileName: com.loan.common.service.impl.AppInfoServiceImpl.java
 * Author: wangyingjie
 * Email: m13803851175@163.com
 * Date: 2016/12/29 15:56
 * Description:
 * History:
 * <Author>      <Time>    <version>    <desc>
 * wangyingjie   15:56    1.0          Create
 */
@Service
public class AppInfoServiceImpl implements AppInfoService {

    private static final Logger logger = Logger.getLogger(AppInfoServiceImpl.class);

    @Autowired
    private BannerDao userDao;
    @Autowired
    private BusinessDao businessDao;


    @Override
    public Page<BusinessDto> findDaQuanBusinessInfo(Page page) {
        List<BusinessDto> businessDtos = new ArrayList<BusinessDto>();
        List<Business> businesses = businessDao.findAll();
        if(businesses != null && businesses.size()>0){
            for(Business business : businesses){
                BusinessDto businessDto = new BusinessDto();
                businessDto.setIconPath(business.getIconPath());
                businessDto.setBusName(BusinessEnum.getlabelDesc(business.getChannelTag()));
                businessDto.setFee(business.getChannelApr() * 100+"%");
                businessDto.setIconPath(business.getIconPath());
                businessDto.setShortWord(business.getChannelDesc());
                businessDto.setLinkType(1);
                businessDto.setLinkPath(business.getLinkPath());
                businessDto.setBusId(business.getId());
                businessDtos.add(businessDto);
            }
        }
        page.setData(businessDtos);
        return page;
    }

    @Override
    public AppIndexDto findIndexBusinessInfo() {
        List<IndexBussinessDto> indexBusinessRecommendDtos = new ArrayList<IndexBussinessDto>();
        List<IndexBussinessDto> indexBusinessLikeDtos = new ArrayList<IndexBussinessDto>();
        AppIndexDto appIndexDto = new AppIndexDto();
        List<Banner> banners = userDao.findBannerByDisabled(ConstantUtil.BANNER_DISABLED);
        List<Business> businesses = businessDao.findIndexBusInfo();
        if(businesses != null && businesses.size()>0){
            for(Business business : businesses){
                IndexBussinessDto indexBussinessDto = new IndexBussinessDto();
                indexBussinessDto.setIconPath(business.getIconPath());
                indexBussinessDto.setLinkPath(business.getLinkPath());
                indexBussinessDto.setTitleType(LabelEnum.getlabelDesc(business.getTitleType()));
                indexBussinessDto.setLinkType(business.getLinkType());
                indexBussinessDto.setGuideWord(business.getGuideWord());
                indexBussinessDto.setTitleBig(business.getTitleBig());
                indexBussinessDto.setTitleSmall(business.getTitleSmall());
                indexBussinessDto.setBusId(business.getId());
                if(business.getTitleType().equals(LabelEnum.LABEL_RECOMMEND.getCode())){
                    indexBusinessRecommendDtos.add(indexBussinessDto);
                }
                if(business.getTitleType().equals(LabelEnum.LABEL_LIKE.getCode())){
                    indexBusinessLikeDtos.add(indexBussinessDto);
                }
            }
        }
        appIndexDto.setBanners(banners);
        appIndexDto.setRecommends(indexBusinessRecommendDtos);
        appIndexDto.setLikes(indexBusinessLikeDtos);
        return appIndexDto;
    }

    @Override
    @Transactional(rollbackFor = {LoanException.class,RuntimeException.class})
    public void saveBusClick(Long busId) throws LoanException{
        Business business = businessDao.findById(busId);
        if(business == null){
            throw new LoanException("此平台不存在！");
        }
        business.setClickTotal(business.getClickTotal()+1);
        long n = businessDao.updateById(business);
        if(n<=0){
            throw new LoanException("点击量更新失败！");
        }
    }
}
