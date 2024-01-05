package com.ruoyi.common.configure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@ConfigurationProperties(prefix = "environmentParam")
public class RYConfiguration {
    @Value("${environmentParam.test}")
    public  boolean TEST;

    public static Boolean TEST_FLAG;
    @PostConstruct
    public void init(){
        RYConfiguration.TEST_FLAG = TEST;
    }

    public  boolean isTestFlag() {
        return TEST;
    }

}
