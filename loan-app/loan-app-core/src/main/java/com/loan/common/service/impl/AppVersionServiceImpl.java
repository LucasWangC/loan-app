package com.loan.common.service.impl;

import com.loan.common.dao.AppinfoDao;
import com.loan.common.dto.AppVersionDto;
import com.loan.common.model.Appinfo;
import com.loan.common.service.AppVersionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * FileName: com.loan.common.service.impl.AppVersionServiceImpl.java
 * Author: wangyingjie
 * Email: wangyingjie@wzdai.com
 * Date: 2017/1/20 16:26
 * Description:
 * History:
 * <Author>      <Time>    <version>    <desc>
 * wangyingjie   16:26    1.0          Create
 */
@Service
public class AppVersionServiceImpl implements AppVersionService{

    @Autowired
    private AppinfoDao appinfoDao;


    /**
     * 获取版本信息
     * @return
     */
    public AppVersionDto findAppVersionInfo() {

        AppVersionDto appVersionDto = new AppVersionDto();
        Appinfo android = appinfoDao.findAndroidVersion();
        Appinfo ios = appinfoDao.findIOSVersion();

        if(android != null){
            appVersionDto.setAndroidDownloadUrl(android.getDownloadurl());
            appVersionDto.setAndroidIsForce(android.getForcestatus().toString());
            appVersionDto.setAndroidVersion(android.getVersionkey());
            appVersionDto.setAndroidVersionInfo(android.getVersioninfo());
        }

        if(ios != null){
            appVersionDto.setiOSDownloadUrl(ios.getDownloadurl());
            appVersionDto.setIosIsForce(ios.getForcestatus().toString());
            appVersionDto.setiOSVersion(ios.getVersionkey());
            appVersionDto.setiOSVersionInfo(ios.getVersioninfo());
        }
        return appVersionDto;
    }
}
