package com.bank.cashback.configuration;

import com.bank.cashback.model.TransactionCategory;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@ConfigurationProperties(prefix = "cashback")
@Data
public class CategoryCashbackConfig {
    Map<TransactionCategory, Long> category;
}
