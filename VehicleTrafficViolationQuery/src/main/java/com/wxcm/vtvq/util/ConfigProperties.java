package com.wxcm.vtvq.util;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author GZH
 * @date 2020-08-21
 */
@Component
@ConfigurationProperties(prefix = "config")
public class ConfigProperties {
    //如果配置项较少，可以不使用@ConfigurationProperties(prefix = "config")
    //在属性上单独使用@Value("${config.prefix}")

    private String prefix;

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }
}
