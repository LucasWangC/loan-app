package com.loan.common.mybatis;

import java.io.Serializable;
import java.util.Date;

/**
 * FileName: com.loan.common.mybatis.AuditEntity.java
 * Author: wangyingjie
 * Email: m13803851175@163.com
 * Date: 2016/12/30 10:43
 * Description:
 * History:
 * <Author>      <Time>    <version>    <desc>
 * wangyingjie   10:43    1.0          Create
 */
public interface AuditEntity <T extends Serializable> extends Serializable{

    public T getCreator();

    public void setCreator(T creator);

    public Date getCreateTime();

    public void setCreateTime(Date createTime);

    public T getModifier();

    public void setModifier(T modifier);

    public Date getModifyTime();

    public void setModifyTime(Date modifyTime) ;
}
