package com.github.common.mongodb;

import javax.persistence.criteria.CriteriaBuilder;
import java.io.Serializable;
import java.util.Collection;

/**
 * Copyright (c) 2017-2018 github Company LTD.
 * All rights reserved.
 *
 * @Description:
 * @Date: Created in 2018 2018/1/20 17:55
 * @Author: pengnian
 */
public class Page<T> implements Serializable {

    private Integer pageNo;

    private Integer pageSize;

    private Collection<T> list;

    private Long count;

    public Page() {
    }

    public Page(Integer pageNo, Integer pageSize, Collection<T> list, Long count) {
        this.pageNo = pageNo;
        this.pageSize = pageSize;
        this.list = list;
        this.count = count;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Collection<T> getList() {
        return list;
    }

    public void setList(Collection<T> list) {
        this.list = list;
    }
}
