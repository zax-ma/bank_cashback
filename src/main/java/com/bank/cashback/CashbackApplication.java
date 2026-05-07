package com.bank.cashback;

import com.bank.cashback.model.ClientTransaction;
import com.bank.cashback.model.TransactionCategory;
import com.bank.cashback.service.CashbackService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.jdbc.autoconfigure.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class CashbackApplication {

	public static void main(String[] args) {
		SpringApplication.run(CashbackApplication.class, args);
	}
	@Bean
	public CommandLineRunner demoTips(CashbackService myService) {
		return args -> {
			System.out.println("--- Запуск проверки сервиса ---");

			ClientTransaction t1 = new ClientTransaction(UUID.randomUUID(), TransactionCategory.FOOD, 1000, 0L);
			ClientTransaction t2 = new ClientTransaction(UUID.randomUUID(), TransactionCategory.TRAVEL, 3000, 0L);
			List<ClientTransaction> list = new ArrayList<>();
			list.add(t1);
			list.add(t2);
			var resultTips = myService.applyCashback(UUID.randomUUID(), list);
			System.out.println("Результат: " + resultTips.toString());

			System.out.println("--- Проверка завершена ---");
		};
	}
}
