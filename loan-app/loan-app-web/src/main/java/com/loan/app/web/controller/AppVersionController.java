package com.loan.app.web.controller;

import com.loan.app.vo.BaseResponse;
import com.loan.common.dto.AppVersionDto;
import com.loan.common.service.AppVersionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * FileName: com.loan.app.web.controller.AppVersionController.java
 * Author: lucasWang
 * Email: m13803851175@163.com
 * Date: 2017/1/20 15:58
 * Description:
 * History:
 * <Author>      <Time>    <version>    <desc>
 * wangyingjie   15:58    1.0          Create
 */
@RequestMapping("app/version")
@Controller
public class AppVersionController {

    @Autowired
    private AppVersionService appVersionService;

    @RequestMapping("info")
    public Object getVersionInfo(){
        AppVersionDto appVersionDto = appVersionService.findAppVersionInfo();
        return BaseResponse.success(appVersionDto);
    }

}
