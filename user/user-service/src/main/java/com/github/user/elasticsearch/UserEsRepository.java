package com.github.user.elasticsearch;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Copyright (c) 2017-2018 github Company LTD.
 * All rights reserved.
 *
 * @Description:
 * @Date: Created in 2018 2018/1/20 17:55
 * @Author: pengnian
 */
public interface UserEsRepository extends ElasticsearchRepository<UserEsEntity, Long> {


}
