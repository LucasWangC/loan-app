package com.loan.app.web.controller;

import com.loan.app.vo.BaseResponse;
import com.loan.common.exception.LoanException;
import com.loan.common.mybatis.Page;
import com.loan.common.service.AppInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Copyright (C), 2016-2020 助你贷
 * FileName: com.loan.app.web.controller.AppAllBusinessController.java
 * Author: lucasWang
 * Email: m13803851175@163.com
 * Date: 2017/1/12 0:15
 * Description:
 * History:
 * <Author>      <Time>    <version>    <desc>
 * lucasWang   0:15    1.0          Create
 */
@RequestMapping("app/business")
@Controller
public class AppAllBusinessController {

    @Autowired
    private AppInfoService appInfoService;

    @RequestMapping("all")
    public Object getAllAppInfo (Page page){
        Object busInfoDto = appInfoService.findDaQuanBusinessInfo(page);
        return BaseResponse.success(busInfoDto);
    }

    @RequestMapping("increaseClick")
    public Object increaseClick (Long busId) throws LoanException{
        if(busId == null || busId<=0){
            throw new LoanException("平台ID不正确！");
        }
        appInfoService.saveBusClick(busId);
        return BaseResponse.success();
    }
}
