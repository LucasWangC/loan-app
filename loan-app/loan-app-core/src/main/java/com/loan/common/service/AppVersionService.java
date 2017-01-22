package com.loan.common.service;

import com.loan.common.dto.AppVersionDto;
import com.loan.common.model.Appinfo;

/**
 * Copyright (C), 2011-2015 温州贷
 * FileName: com.loan.common.service.AppVersionService.java
 * Author: wangyingjie
 * Email: wangyingjie@wzdai.com
 * Date: 2017/1/20 16:26
 * Description:
 * History:
 * <Author>      <Time>    <version>    <desc>
 * wangyingjie   16:26    1.0          Create
 */
public interface AppVersionService {

    /**
     * 查找app的版本信息
     * @return
     */
    public AppVersionDto findAppVersionInfo();
}
