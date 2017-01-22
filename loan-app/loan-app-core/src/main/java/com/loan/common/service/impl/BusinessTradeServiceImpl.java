package com.loan.common.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.loan.common.dao.BusinessConfigDao;
import com.loan.common.dao.BusinessUserDao;
import com.loan.common.dao.UserBorrowDao;
import com.loan.common.exception.LoanException;
import com.loan.common.model.BusinessConfig;
import com.loan.common.model.BusinessUser;
import com.loan.common.model.UserBorrow;
import com.loan.common.service.BusinessTradeService;
import com.loan.common.utils.NotifyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

/**
 * FileName: com.loan.common.service.impl.BusinessTradeServiceImpl.java
 * Author: lucasWang
 * Email: m13803851175@163.com
 * Date: 2017/1/22 16:31
 * Description:
 * History:
 * <Author>      <Time>    <version>    <desc>
 * wangyingjie   16:31    1.0          Create
 */
@Service
public class BusinessTradeServiceImpl implements BusinessTradeService{

    @Autowired
    private BusinessConfigDao businessConfigDao;
    @Autowired
    private BusinessUserDao businessUserDao;
    @Autowired
    private UserBorrowDao userBorrowDao;

    /**
     *  用户注册通知
     * @param result
     * @return
     */
    @Transactional(rollbackFor = {LoanException.class,RuntimeException.class})
    public Object registerNotify(String result) throws LoanException {

        Long nowTime = System.currentTimeMillis()/1000;

        //查询平台key
        JSONObject reqObj = JSON.parseObject(NotifyUtils.getJsonStr(result));
        if(reqObj == null){
            throw new LoanException("通知数据为空");
        }

        BusinessConfig businessConfig = new BusinessConfig();
        businessConfig.setBusCode(reqObj.getString("bus_code"));

        List<BusinessConfig> bgs = businessConfigDao.findByCondition(businessConfig);
        if(bgs != null && bgs.size()>0){
            businessConfig = bgs.get(0);
        }
        boolean check = NotifyUtils.registerCheckSign(result, businessConfig.getBusKey());

        if(!check){
            throw new LoanException("签名错误");
        }

        BusinessUser businessUser = new BusinessUser();
        businessUser.setBusinessCode(reqObj.getString("bus_code"));
        businessUser.setBusUserId(reqObj.getString("user_id"));
        businessUser.setCreateTime(nowTime.intValue());
        businessUser.setPhoneNo(reqObj.getString("phone_no"));
        businessUser.setRegisterTime(reqObj.getString("register_time") == null ? 0 : Long.valueOf(reqObj.getString("register_time")));
        businessUserDao.save(businessUser);

        return "The request processing success !";
    }


    /**
     *  用户借款通知
     * @param result
     * @return
     */
    @Transactional(rollbackFor = {LoanException.class,RuntimeException.class})
    public Object borrowNotify(String result) throws LoanException {

        Long nowTime = System.currentTimeMillis()/1000;

        //查询平台key
        JSONObject reqObj = JSON.parseObject(NotifyUtils.getJsonStr(result));
        if(reqObj == null){
            throw new LoanException("通知数据为空");
        }

        BusinessConfig businessConfig = new BusinessConfig();
        businessConfig.setBusCode(reqObj.getString("bus_code"));

        List<BusinessConfig> bgs = businessConfigDao.findByCondition(businessConfig);
        if(bgs != null && bgs.size()>0){
            businessConfig = bgs.get(0);
        }
        boolean check = NotifyUtils.borrowCheckSign(result,businessConfig.getBusKey());

        if(!check){
            throw new LoanException("签名错误");
        }

        UserBorrow userBorrow = new UserBorrow();
        userBorrow.setBusinessCode(reqObj.getString("bus_code"));
        userBorrow.setBusUserid(reqObj.getString("user_id"));
        userBorrow.setCreateTime(nowTime.intValue());
        userBorrow.setPhoneNo(reqObj.getString("phone_no"));
        userBorrow.setBusOrderNo(reqObj.getString("bus_order_no"));
        userBorrow.setBusOrderMoney(reqObj.getString("bus_order_money") == null ? BigDecimal.ZERO : new BigDecimal(reqObj.getString("bus_order_money")));
        userBorrow.setBusOrderTime(reqObj.getString("bus_order_time") == null?0:Long.valueOf(reqObj.getString("bus_order_time")));
        userBorrowDao.save(userBorrow);

        return "The request processing success !";
    }
}
