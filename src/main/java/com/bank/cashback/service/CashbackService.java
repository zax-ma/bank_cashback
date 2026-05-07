package com.bank.cashback.service;

import com.bank.cashback.exception.CashbackCalculationException;
import com.bank.cashback.exception.CashbackStrategyNotFoundException;
import com.bank.cashback.model.ClientTransaction;
import com.bank.cashback.model.TotalCashback;
import com.bank.cashback.strategy.CashBackStrategyType;
import com.bank.cashback.strategy.CashbackStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CashbackService {
    private final UserCashbackService userCashbackService;
    private final CashbackFactory factory;

    public TotalCashback applyCashback(UUID userId, List<ClientTransaction> pack){
        if(userId == null){
            throw new IllegalArgumentException("Id пользователя не может быть пустым");
        }
        if(pack.isEmpty()) {
            return new TotalCashback(pack, 0L);
        }
        CashBackStrategyType cashbackType = userCashbackService.getCashbackTypeByUserId(userId);
        if(cashbackType == null){
            throw new CashbackStrategyNotFoundException("Стратегия для расчета кэшбэка не найдена");
        }
        CashbackStrategy cashbackStrategy = factory.getCashbackStrategy(cashbackType);
        try {
            return cashbackStrategy.applyCashback(pack);
        } catch (Exception e){
            throw new CashbackCalculationException("Ошибка в методе расчета кэшбэка", e);
        }
    }
}
