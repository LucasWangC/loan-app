package com.loan.common.mybatis;

import com.google.common.collect.Maps;
import com.loan.common.utils.CurrentUserHelp;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.slf4j.Logger;

import java.io.Serializable;
import java.util.Date;

/**
 * FileName: com.loan.common.mybatis.AuditListener.java
 * Author: wangyingjie
 * Email: m13803851175@163.com
 * Date: 2016/12/30 10:43
 * Description:
 * History:
 * <Author>      <Time>    <version>    <desc>
 * wangyingjie   10:43    1.0          Create
 */
public class AuditListener implements EntityListener<AuditEntity> {

    private static final Logger logger = org.slf4j.LoggerFactory.getLogger(AuditListener.class);
    private int order;
    private java.util.Map<Class,Class> classPapping= Maps.newConcurrentMap();

    public AuditListener(){
        if( logger.isInfoEnabled() ){
            logger.info("实体审计侦听器启动!");
        }
    }

    @Override
    public void beforeChange(EntityEvent<AuditEntity> event) {

        try{
            AuditEntity auditEntity=event.getSource();
            switch ( event.getTriggerType() ){
                case INSERT:{
                    auditEntity.setCreateTime(new Date());
                    auditEntity.setCreator(getCurrentUserID(auditEntity));
                }break;
                case UPDATE:{
                    auditEntity.setModifyTime(new Date());
                    auditEntity.setModifier(getCurrentUserID(auditEntity));
                }break;
            }
        }catch (Exception e){
            if (logger.isWarnEnabled()){
                logger.warn(e.getMessage(),e);
            }
        }

    }

    private Serializable getCurrentUserID(AuditEntity auditEntity){

        try{

            Class type = classPapping.get(auditEntity.getClass());
            if( null == type ){
                type = PropertyUtils.getPropertyType(auditEntity, "creator");
                classPapping.put(auditEntity.getClass(),type );
            }
            Object userId = CurrentUserHelp.getCurrentUserID();
            if( null != userId && userId.getClass() == type )
                return (Serializable)userId;
            return (Serializable) ConvertUtils.convert(userId, type);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void afterChange(EntityEvent<AuditEntity> event) {

    }

    public static Logger getLogger() {
        return logger;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }


}
