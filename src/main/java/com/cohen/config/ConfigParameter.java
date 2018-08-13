package com.cohen.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author 林金成
 * @date 2018/8/139:27
 */
@Component
@ConfigurationProperties
public class ConfigParameter {

    private String nettyHost;
    private Integer nettyPort;

    public String getNettyHost() {
        return nettyHost;
    }

    public void setNettyHost(String nettyHost) {
        this.nettyHost = nettyHost;
    }

    public Integer getNettyPort() {
        return nettyPort;
    }

    public void setNettyPort(Integer nettyPort) {
        this.nettyPort = nettyPort;
    }
}
