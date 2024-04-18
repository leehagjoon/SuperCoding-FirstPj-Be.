//package com.firstpj.config;
//
//import com.zaxxer.hikari.HikariConfig;
//import com.zaxxer.hikari.HikariDataSource;
//import org.springframework.boot.autoconfigure.orm.jpa.HibernateProperties;
//import org.springframework.boot.autoconfigure.orm.jpa.HibernateSettings;
//import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.boot.jdbc.DataSourceBuilder;
//import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//import org.springframework.orm.jpa.JpaTransactionManager;
//import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
//import org.springframework.transaction.PlatformTransactionManager;
//
//import javax.sql.DataSource;
//import java.util.Map;
//import java.util.Objects;
//
///**
// * packageName    : com.firstpj.config
// * fileName       : JpaConfig
// * author         : hagjoon
// * date           : 2024-04-16
// * description    :
// * ===========================================================
// * DATE              AUTHOR             NOTE
// * -----------------------------------------------------------
// * 2024-04-16        hagjoon       최초 생성
// */
//@Configuration
//@EnableJpaRepositories(
//        basePackages = {"com.firstpj"},
//        entityManagerFactoryRef = "entityManagerFactoryBean",
//        transactionManagerRef = "tmJpa"
//
//)
//public class JpaConfig {
//
//    private final JpaProperties jpaProperties;
//
//    private final HibernateProperties hibernateProperties;
//
//    public JpaConfig(JpaProperties jpaProperties, HibernateProperties hibernateProperties) {
//        this.jpaProperties = jpaProperties;
//        this.hibernateProperties = hibernateProperties;
//    }
//
//    @Bean(name = "dataSource")
//    @Primary
//    @ConfigurationProperties(prefix = "spring.datasource.control")
//    public DataSource dataSource(){
//        HikariConfig config = new HikariConfig();
//        config.setJdbcUrl("jdbc:mysql://localhost:3306/FirstProject?useSSL=false&serverTimezone=Asia/Seoul&characterEncoding=UTF-8");
//        config.setUsername("root");
//        config.setPassword("12341234");
//        return new HikariDataSource(config);
//
//    }
//
//    @Bean(name = "entityManagerFactoryBean")
//    @Primary
//    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(EntityManagerFactoryBuilder builder){
//        Map<String, Object> prop = hibernateProperties.determineHibernateProperties(jpaProperties.getProperties(),
//                new HibernateSettings());
//        return builder.dataSource(dataSource())
//                .properties(prop)
//                .packages("com.firstpj")
//                .build();
//    }
//
//
//
//    @Bean(name = "tmJpa")
//    @Primary
//    public PlatformTransactionManager transactionManager(EntityManagerFactoryBuilder builder){
//        return new JpaTransactionManager(Objects.requireNonNull(entityManagerFactoryBean(builder).getObject()));
//    }
//}
