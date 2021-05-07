package com.github.common.mongodb;

import com.github.common.utils.ReflectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Copyright (c) 2017-2018 github Company LTD.
 * All rights reserved.
 *
 * @Description:
 * @Date: Created in 2018 2018/1/20 17:55
 * @Author: pengnian
 */
public abstract class AbsBaseMongo<T> implements BaseMongoDao<T> {

    @Autowired
    protected MongoTemplate mongoTemplate;

    protected abstract void setMongoTemplate(MongoTemplate mongoTemplate);

    @Override
    public List<T> findList(Query query) {
        return mongoTemplate.find(query, this.getEntityClass());
    }

    private Class<T> getEntityClass() {
        return ReflectUtils.getSuperClassGenricType(getClass());
    }

    @Override
    public T findOne(Query query) {
        return mongoTemplate.findOne(query, this.getEntityClass());
    }

    @Override
    public T update(Query query, Update update) {
        return mongoTemplate.findAndModify(query, update, this.getEntityClass());
    }

    @Override
    public T insert(T entity) {
        mongoTemplate.insert(entity);
        return entity;
    }

    @Override
    public T findById(String id) {
        return mongoTemplate.findById(id, this.getEntityClass());
    }

    @Override
    public T findById(String id, String collectionName) {
        return mongoTemplate.findById(id, this.getEntityClass(), collectionName);
    }

    @Override
    public Page<T> findPage(Page<T> page, Query query) {
        Long count = this.count(query);
        page.setCount(count == null ? 0L : count);

        Integer pageNo = page.getPageNo() == null || page.getPageNo() <= 0 ? 1 : page.getPageNo();
        Integer pageSize = page.getPageSize() == null || page.getPageSize() < 0 ? 10 : page.getPageSize();

        query.skip((pageNo - 1) * pageSize).limit(pageSize);
        List<T> list = this.findList(query);
        page.setList(list == null ? new ArrayList<>() : list);

        return page;
    }

    @Override
    public Long count(Query query) {
        return mongoTemplate.count(query, this.getEntityClass());
    }

    @Override
    public void batchInsert(Collection<T> collection) {
        mongoTemplate.insert(collection, this.getEntityClass());
    }
}
