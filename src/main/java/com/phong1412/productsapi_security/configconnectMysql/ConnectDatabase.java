package com.phong1412.productsapi_security.configconnectMysql;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class ConnectDatabase {
    @Bean
    public DataSource dataSource() {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/product_db?useSSL=false");
        dataSource.setUsername("root");
        dataSource.setPassword("");
        return dataSource;
    }

}
