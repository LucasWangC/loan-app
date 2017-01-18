package com.loan.common.dao;

import com.loan.common.mybatis.Page;
import org.apache.ibatis.annotations.Param;

import java.io.Serializable;
import java.util.List;

/**
 * Date: 2016/12/29
 * Description: 所有的Mapper接口类都要继承的该接口
 * History:
 * <Author>      <Time>    <version>    <desc>
 * wangyingjie   15:08    1.0          Create
 */
public interface BaseDao<T, ID extends Serializable> {

    /**
     * 获取所有entity集合
     *
     * @return
     */
    List<T> findAll();

    /**
     * 根据id获取entity
     *
     * @param id
     * @return
     */
    T findById(ID id);

    /**
     * 根据entity内的属性值作为条件，查询出符合条件的entity集合
     *
     * @param entity
     * @return
     */
    List<T> findByCondition(T entity);

    /**
     * 获取分页的entity集合
     * @param page
     * @return
     */
    List<T> findPage(Page<T> page);

    /**
     * 获取分页的entity集合
     * @param page
     * @param entity
     * @return
     */
    List<T> findPageByCondition(Page<T> page, @Param("query") T entity);

    /**
     * 根据entity内的id，更新entity对象
     *
     * @param entity
     * @return
     */
    long updateById(T entity);

    /**
     * 插入一条entity到表中
     *
     * @param entity
     * @return
     */
    void save(T entity);

    /**
     * 统计所有数据的总和
     *
     * @return
     */
    long count();

    /**
     * 根据entity内的属性值作为条件，统计符合条件数据的总和
     *
     * @param entity
     * @return
     */
    long countByCondition(T entity);

    /**
     * 根据id，逻辑删除对应的数据
     *
     * @param id
     */
    long deleteById(ID id);


    /**
     * 根据id，删除对应的数据
     *
     * @param id
     */
    long deleteDataById(ID id);
}

