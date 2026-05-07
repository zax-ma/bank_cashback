package com.bank.cashback.exception;

public class CashbackCalculationException extends RuntimeException {
    public CashbackCalculationException(String message, Throwable cause) {
        super(message, cause);
    }
}
