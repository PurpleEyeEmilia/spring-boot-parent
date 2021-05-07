package com.github.common.mongodb;

import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.Collection;
import java.util.List;

/**
 * Copyright (c) 2017-2018 github Company LTD.
 * All rights reserved.
 *
 * @Description: MongoDB模板接口
 * @Date: Created in 2018 2018/1/20 17:55
 * @Author: pengnian
 */
public interface BaseMongoDao<T> {
    /**
     * 查列表
     *
     * @param query
     * @return
     */
    List<T> findList(Query query);

    /**
     * 查单条
     *
     * @param query
     * @return
     */
    T findOne(Query query);

    /**
     * 更新数据
     *
     * @param query
     * @param update
     * @return
     */
    T update(Query query, Update update);

    /**
     * 插入数据
     *
     * @param entity
     * @return
     */
    T insert(T entity);

    /**
     * 根据id查询数据
     *
     * @param id
     * @return
     */
    T findById(String id);

    /**
     * 根据id和集合名（相当于MySQL中的表名）查询数据
     *
     * @param id
     * @param collectionName
     * @return
     */
    T findById(String id, String collectionName);

    /**
     * 分页查询
     *
     * @param page
     * @param query
     * @return
     */
    Page<T> findPage(Page<T> page, Query query);

    /**
     * 查询计数
     *
     * @param query
     * @return
     */
    Long count(Query query);

    /**
     * 批量插入
     *
     * @param collection
     */
    void batchInsert(Collection<T> collection);
}
