package com.bank.cashback.configuration;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Data
public class FixedCashbackConfig {
    @Value("${cashback.fixed}")
    private long fixed;
    @Value("${cashback.cap}")
    private long cap;
}
