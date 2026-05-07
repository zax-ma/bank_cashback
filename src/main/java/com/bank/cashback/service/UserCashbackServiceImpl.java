package com.bank.cashback.service;

import com.bank.cashback.strategy.CashBackStrategyType;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserCashbackServiceImpl implements UserCashbackService {
    @Override
    public CashBackStrategyType getCashbackTypeByUserId(UUID userId) {
        return CashBackStrategyType.FIXED;
    }
}
