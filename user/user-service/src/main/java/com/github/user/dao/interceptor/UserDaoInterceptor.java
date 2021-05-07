package com.github.user.dao.interceptor;

import com.alibaba.dubbo.config.annotation.Reference;
import com.github.common.response.Response;
import com.github.common.response.ResponseUtils;
import com.github.support.api.IDApi;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import java.util.Properties;

/**
 * Copyright (c) 2017-2018 github Company LTD.
 * All rights reserved.
 *
 * @Description: sql拦截器，作用：insert操作自动添加SnowflakeID，暂不支持批量插入操作
 * @Date: Created in 2018 2018/1/20 17:55
 * @Author: pengnian
 */
@Intercepts(value = {
        @Signature(method = "query", type = Executor.class, args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class}),
        @Signature(method = "update", type = Executor.class, args = {MappedStatement.class, Object.class})
})
public class UserDaoInterceptor implements Interceptor {

    @Reference(check = false)
    private IDApi idApi;

    @Value("${snowflakeIDColumn}")
    private String snowflakeIDColumn;

    private String username;

    private static final String INSERT = "INSERT";

    private static final String UPDATE = "UPDATE";

    private static final String DELETE = "DELETE";

    private static final String SELECT = "SELECT";

    private static final Logger LOGGER = LoggerFactory.getLogger(UserDaoInterceptor.class);

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        Object[] args = invocation.getArgs();
        MappedStatement mappedStatement = (MappedStatement) args[0];
        String sqlType = mappedStatement.getSqlCommandType().name();
        switch (sqlType) {
            case INSERT:
                addKid(invocation, mappedStatement);
                break;
            case DELETE:

                break;
            case UPDATE:

                break;
            case SELECT:

                break;
        }

        Object result = invocation.proceed();
        System.out.println("输出applicationName:" + username);
        return result;
    }

    private void addKid(Invocation invocation, MappedStatement mappedStatement){
        Response<Long> response = idApi.getSnowflakeID();
        Long kid = ResponseUtils.getDateNotNull(response);

        Object object = invocation.getArgs()[1];
        BoundSql boundSql = mappedStatement.getBoundSql(object);
        String sql = boundSql.getSql();

        String[] split = sql.split("\\(");
        if (split != null && split.length > 0 && split.length == 3) {
            //批量暂不支持
            sql = split[0] + "(" + snowflakeIDColumn +  ", " + split[1] + "(" + kid + ", " + split[2];
        }

        BoundSql newBoundSql = new BoundSql(mappedStatement.getConfiguration(), sql, boundSql.getParameterMappings(), boundSql.getParameterObject());
        MappedStatement newStatement = copyFromMappedStatement(mappedStatement, new BoundSqlSqlSource(newBoundSql));
        invocation.getArgs()[0] = newStatement;
    }

    private MappedStatement copyFromMappedStatement(MappedStatement mappedStatement, SqlSource sqlSource) {
        MappedStatement.Builder builder = new MappedStatement.Builder(mappedStatement.getConfiguration(), mappedStatement.getId(), sqlSource, mappedStatement.getSqlCommandType());

        builder.resource(mappedStatement.getResource());
        builder.fetchSize(mappedStatement.getFetchSize());
        builder.statementType(mappedStatement.getStatementType());
        builder.keyGenerator(mappedStatement.getKeyGenerator());
        if (mappedStatement.getKeyProperties() != null && mappedStatement.getKeyProperties().length > 0) {
            builder.keyProperty(mappedStatement.getKeyProperties()[0]);
        }
        builder.timeout(mappedStatement.getTimeout());
        builder.parameterMap(mappedStatement.getParameterMap());
        builder.resultMaps(mappedStatement.getResultMaps());
        builder.resultSetType(mappedStatement.getResultSetType());

        builder.cache(mappedStatement.getCache());
        builder.flushCacheRequired(mappedStatement.isFlushCacheRequired());
        builder.useCache(mappedStatement.isUseCache());

        return builder.build();
    }

    @Override
    public Object plugin(Object o) {
        return Plugin.wrap(o, this);
    }

    @Override
    public void setProperties(Properties properties) {
        username = (String) properties.get("applicationName");
        System.out.println(username);
    }


    public static class BoundSqlSqlSource implements SqlSource{

        private BoundSql boundSql;

        public BoundSqlSqlSource(BoundSql boundSql){
            this.boundSql = boundSql;
        }

        @Override
        public BoundSql getBoundSql(Object o) {
            return boundSql;
        }
    }
}
