package com.bank.cashback.exception;

public class CashbackStrategyNotFoundException extends RuntimeException{
    public CashbackStrategyNotFoundException(String message) {
        super(message);
    }
}
