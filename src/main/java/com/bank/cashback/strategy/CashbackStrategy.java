package com.bank.cashback.strategy;

import com.bank.cashback.model.ClientTransaction;
import com.bank.cashback.model.TotalCashback;

import java.util.List;

public interface CashbackStrategy {
    TotalCashback applyCashback(List<ClientTransaction> pack);

    default CashBackStrategyType getStrategyType(){
        return null;
    }
}
