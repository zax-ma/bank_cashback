package com.bank.cashback.strategy;

import com.bank.cashback.model.ClientTransaction;
import com.bank.cashback.model.TotalCashback;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class FixedCashbackStrategy implements CashbackStrategy {
    @Value("${cashback.fixed}")
    private long fixed;
    @Value("${cashback.cap}")
    private long cap;

    @Override
    public TotalCashback applyCashback(List<ClientTransaction> pack) {
        List<ClientTransaction> resultPack = new ArrayList<>();
        long totalAmount = pack.stream().mapToLong(ClientTransaction::getAmount).sum();
        long potentialTotalCashback = Math.round(totalAmount * (fixed / 100.0));
        long totalCashbackToDistribute;
        boolean isCapReached = potentialTotalCashback > cap;

        if (isCapReached) {
            totalCashbackToDistribute = cap;
        } else {
            totalCashbackToDistribute = potentialTotalCashback;
        }

        long currentDistributedSum = 0;
        for (int i = 0; i < pack.size(); i++) {
            ClientTransaction clientTransaction = pack.get(i);
            long cashback;
            if (i == pack.size() - 1) {
                cashback = totalCashbackToDistribute - currentDistributedSum;
            } else {
                if (isCapReached) {
                    double proportion = (double) clientTransaction.getAmount() / totalAmount;
                    cashback = Math.round(proportion * totalCashbackToDistribute);
                } else {
                    cashback = Math.round(clientTransaction.getAmount() * (fixed / 100.0));
                }
            }
            ClientTransaction copy = clientTransaction.toBuilder()
                            .cashback(cashback)
                            .build();
            resultPack.add(copy);
            currentDistributedSum += cashback;
        }
        return new TotalCashback(resultPack, currentDistributedSum);
    }

    @Override
    public CashBackStrategyType getStrategyType() {
        return CashBackStrategyType.FIXED;
    }
}
