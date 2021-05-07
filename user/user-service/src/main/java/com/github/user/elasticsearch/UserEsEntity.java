package com.github.user.elasticsearch;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

import java.io.Serializable;

/**
 * Copyright (c) 2017-2018 github Company LTD.
 * All rights reserved.
 *
 * @Description:
 * indexName 相当于数据库名，建议以项目名称命名,
 * type 相当于表名，建议以实体类命名
 * shards 分片数，默认5片
 * replicas 每个分区默认备份数
 * refreshInterval 刷新间隔，默认1s
 * indexStoreType 索引未见存储类型
 * @Date: Created in 2018 2018/1/20 17:55
 * @Author: pengnian
 */
@Document(indexName = "github", type = "user", shards = 5, replicas = 1, refreshInterval = "1s", indexStoreType = "fs")
public class UserEsEntity implements Serializable {

    @Id
    private Long id;

    @Field
    private String name;

    @Field
    private String phone;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "UserEsEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
