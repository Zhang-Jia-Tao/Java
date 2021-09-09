package com.sdkj.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

/*
*  声明式事务：
*   1.导入相关依赖
*   2.配置数据源、JdbcTemplate(Spring提供的简化数据库操作的工具)操作数据
*
*
* */

@ComponentScan({"com.sdkj.dao","com.sdkj.service"})
@Configuration
public class MainConfigOfTx {

    @Bean
    public DataSource dataSource() throws Exception{
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setUser("root");
        dataSource.setPassword("20010326");
        dataSource.setDriverClass("com.mysql.cj.jdbc.Driver");
        dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/bjpowernode");
        return dataSource;
    }

    @Bean
    public JdbcTemplate jdbcTemplate() throws Exception{
        //Spring对@Configuratio类会特殊处理，只要是容器中添加组件的方法，多次调用只会从容器中寻找组件
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource());
        return  jdbcTemplate;
    }

}
