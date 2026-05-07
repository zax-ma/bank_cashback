package com.bank.cashback.strategy;

import com.bank.cashback.model.ClientTransaction;
import com.bank.cashback.model.TotalCashback;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class NoneCashbackStrategy implements CashbackStrategy{
    @Override
    public TotalCashback applyCashback(List<ClientTransaction> pack) {
        return new TotalCashback(pack, 0L);
    }

    @Override
    public CashBackStrategyType getStrategyType() {
        return CashBackStrategyType.NONE;
    }
}
