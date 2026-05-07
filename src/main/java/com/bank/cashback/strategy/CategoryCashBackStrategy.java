package com.bank.cashback.strategy;

import com.bank.cashback.configuration.CategoryCashbackConfig;
import com.bank.cashback.model.ClientTransaction;
import com.bank.cashback.model.TotalCashback;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class CategoryCashBackStrategy implements CashbackStrategy {
    private final CategoryCashbackConfig config;

    @Override
    public TotalCashback applyCashback(List<ClientTransaction> pack) {
        List<ClientTransaction> resultPack = new ArrayList<>();
        long sum = 0;
        for (ClientTransaction clientTransaction : pack) {
            long percent = config.getCategory().getOrDefault(clientTransaction.getCategory(), 0L);
            long amount = clientTransaction.getAmount();
            long cashback = amount * percent / 100;
            ClientTransaction copy = clientTransaction.toBuilder()
                    .cashback(cashback)
                    .build();
            resultPack.add(copy);
            sum += cashback;
        }
        return new TotalCashback(resultPack, sum);
    }

    @Override
    public CashBackStrategyType getStrategyType() {
        return CashBackStrategyType.CATEGORY;
    }
}
