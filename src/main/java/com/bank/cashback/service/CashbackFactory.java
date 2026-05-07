package com.bank.cashback.service;

import com.bank.cashback.model.TotalCashback;
import com.bank.cashback.strategy.CashBackStrategyType;
import com.bank.cashback.strategy.CashbackStrategy;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class CashbackFactory {
    private final Map<CashBackStrategyType, CashbackStrategy> strategies;

    public CashbackFactory(List<CashbackStrategy> strategyList) {
        this.strategies = strategyList.stream()
                .collect(Collectors.toMap(CashbackStrategy::getStrategyType,
                        strategy -> strategy));
    }

    public CashbackStrategy getCashbackStrategy(CashBackStrategyType type){
        return strategies.getOrDefault(type,
                ( pack) -> {return new TotalCashback(pack, 0L);
        });
    }
}
