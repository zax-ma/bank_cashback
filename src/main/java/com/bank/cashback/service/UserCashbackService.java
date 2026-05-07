package com.bank.cashback.service;

import com.bank.cashback.strategy.CashBackStrategyType;

import java.util.UUID;

public interface UserCashbackService {
    CashBackStrategyType getCashbackTypeByUserId(UUID userId);
}
