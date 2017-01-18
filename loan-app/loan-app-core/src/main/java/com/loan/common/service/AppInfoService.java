package com.loan.common.service;

import com.loan.common.dto.AppIndexDto;
import com.loan.common.dto.BusinessDto;
import com.loan.common.dto.IndexBussinessDto;
import com.loan.common.exception.LoanException;
import com.loan.common.model.Banner;
import com.loan.common.mybatis.Page;

import java.util.List;

/**
 * FileName: com.loan.common.service.impl.AppInfoService.java
 * Author: wangyingjie
 * Email: m13803851175@163.com
 * Date: 2016/12/29 15:55
 * Description:
 * History:
 * <Author>      <Time>    <version>    <desc>
 * wangyingjie   15:55    1.0          Create
 */
public interface AppInfoService {


    /**
     * 查询大全中平台信息
     * @return
     */
    public Page<BusinessDto> findDaQuanBusinessInfo(Page page);

    /**
     * 查询首页平台信息
     * @return
     */
    public AppIndexDto findIndexBusinessInfo();

    /**
     * 更新平台点击量
     * @param busId
     */
    public void saveBusClick(Long busId) throws LoanException;

}
