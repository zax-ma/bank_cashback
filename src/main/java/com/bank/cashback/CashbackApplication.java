package com.bank.cashback;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.jdbc.autoconfigure.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class CashbackApplication {

	public static void main(String[] args) {
		SpringApplication.run(CashbackApplication.class, args);
	}

}
