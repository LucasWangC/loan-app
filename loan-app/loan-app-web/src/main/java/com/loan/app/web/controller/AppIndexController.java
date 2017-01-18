package com.loan.app.web.controller;

import com.loan.app.vo.BaseResponse;
import com.loan.common.service.AppInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Author: miaoyusong
 * Email: m13803851175@163.com
 * Date: 2016/12/28 17:11
 * Version: 1.0
 * Description:
 * FileName: com.loan.app.web.controller.AppIndexController.java
 */
@RequestMapping("app")
@Controller
public class AppIndexController {

    @Autowired
    private AppInfoService appInfoService;

    @RequestMapping("/index")
    public Object findAppIndexInfo (){
        Object appIndexDto = appInfoService.findIndexBusinessInfo();
        return BaseResponse.success(appIndexDto);
    }

}
