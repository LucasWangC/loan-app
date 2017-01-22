package com.loan.common.service;

import com.loan.common.dto.AppVersionDto;
import com.loan.common.model.Appinfo;

/**
 * Copyright (C), 2011-2015 ���ݴ�
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
     * ����app�İ汾��Ϣ
     * @return
     */
    public AppVersionDto findAppVersionInfo();
}
