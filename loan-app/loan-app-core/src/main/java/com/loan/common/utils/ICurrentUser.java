package com.loan.common.utils;

import java.io.Serializable;

/**
 * FileName: com.loan.common.utils.ICurrentUser.java
 * Author: wangyingjie
 * Email: m13803851175@163.com
 * Date: 2016/12/30 11:09
 * Description:
 * History:
 * <Author>      <Time>    <version>    <desc>
 * wangyingjie   11:09    1.0          Create
 */
public interface ICurrentUser <ID extends Serializable> {

    public ID getCurrentUserID();
}
