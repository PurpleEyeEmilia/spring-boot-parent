package com.github.common.utils;

import com.github.common.mongodb.Page;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * Copyright (c) 2017-2018 github Company LTD.
 * All rights reserved.
 *
 * @Description:
 * @Date: Created in 2018 2018/1/20 17:55
 * @Author: pengnian
 */
public class PageUtils {

    public static <T> Page<T> getPage(List<T> list) {
        PageInfo<T> pageInfo = new PageInfo<>(list);
        return new Page<>(pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getList(), pageInfo.getTotal());
    }

}
