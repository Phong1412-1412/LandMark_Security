package com.phong1412.productsapi_security;

import com.phong1412.productsapi_security.configconnectMysql.CheckConnectDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.sql.DataSource;

@SpringBootApplication
public class ProductsApiSecurityApplication implements CommandLineRunner {

	@Autowired
	private DataSource dataSource;
	public static void main(String[] args) {
		SpringApplication.run(ProductsApiSecurityApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		CheckConnectDB checkConnectDB = new CheckConnectDB(dataSource);
		if(checkConnectDB.checkConnect()) {
			System.out.println("Connected to database successfully!!");
		}
		else  {
			System.out.println("Connected to database failure!!!");
		}
	}
}
