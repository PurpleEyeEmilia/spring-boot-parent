package com.github.common.utils;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

/**
 * Copyright (c) 2017-2018 github Company LTD.
 * All rights reserved.
 *
 * @Description:
 * @Date: Created in 2018 2018/1/20 17:55
 * @Author: pengnian
 */
@Configuration
public class Context implements EnvironmentAware {

    public static final Logger LOGGER = LoggerFactory.getLogger(Context.class);

    public static String webRootPath;

    private static Environment environment;

    static {
        setWebRootRealPath(getWebRealPath());
    }

    private static void setWebRootRealPath(String webRealPath) {
        Context.webRootPath = webRealPath;
    }

    private static String getWebRealPath() {
        String path = null;
        String folderPath = Context.class.getProtectionDomain().getCodeSource().getLocation().getPath();
        LOGGER.info("~~~~~~~~~~~~~~~~~~~~~~~~~~~{}~~~~~~~~~~~~~~~~~~~~~~~~", folderPath);
        if (folderPath.indexOf("WEB-INF/lib") > 0) {
            path = folderPath.substring(0, folderPath.indexOf("WEB-INF/lib")) + "/classes/";
        }
        return path == null ? folderPath : path;
    }

    public static String getProperty(String propertyName) {
        return environment.getProperty(propertyName) == null ? "" : environment.getProperty(propertyName);
    }

    public static String getProperty(String propertyName, String defaultValue) {
        return StringUtils.isNotBlank(environment.getProperty(propertyName)) ? defaultValue : environment.getProperty(propertyName);
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    public Context() {
    }

    public static void main(String[] args){
        String webRealPath = getWebRealPath();
        System.out.println(webRealPath);
    }
}
