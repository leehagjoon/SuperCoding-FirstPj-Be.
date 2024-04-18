package com.firstpj.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
public class JdbcConfig1 { // jdbc 관련된 Bean들을 등록하는 config 파일이다.

    @Bean
    public DataSource dataSource1() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUsername("root");
        dataSource.setPassword("12341234");
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/firstproject?useUnicode=true&characterEncoding=UTF-8");
        return dataSource;
    }

    @Bean
    public DataSource dataSource2() { // 데이터소스 분리.
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUsername("root");
        dataSource.setPassword("12341234");
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/chapter_97?useUnicode=true&characterEncoding=UTF-8");
        return dataSource;
    }

    @Bean
    public JdbcTemplate jdbcTemplate1() {
        return new JdbcTemplate(dataSource1());
    }

    @Bean
    public JdbcTemplate jdbcTemplate2() {
        return new JdbcTemplate(dataSource2());
    }

    @Bean(name = "tm1") // 트랜잭션 사용을 위해 Bean에 등록.
    public PlatformTransactionManager transactionManager1() {
        return new DataSourceTransactionManager(dataSource1());
    }

    @Bean(name = "tm2") // 트랜잭션 사용을 위해 Bean에 등록.
    public PlatformTransactionManager transactionManager2() {
        return new DataSourceTransactionManager(dataSource2());
    }
}
