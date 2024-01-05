package com.ruoyi.common.configure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

@Component
@ConfigurationProperties(prefix = "environmentParam")
//@EnableTransactionManagement
public class RYConfiguration {
    @Value("${environmentParam.test}")
    public  boolean TEST;

    public static Boolean TEST_FLAG;

    @Bean
    // 定义一个事务管理器
    public PlatformTransactionManager transactionManager(DataSource dataSource) { //@2
        return new DataSourceTransactionManager(dataSource);
    }



    @PostConstruct
    public void init(){
        RYConfiguration.TEST_FLAG = TEST;
    }

    public  boolean isTestFlag() {
        return TEST;
    }






}
