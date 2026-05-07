package com.bank.cashback.service;

import com.bank.cashback.exception.CashbackCalculationException;
import com.bank.cashback.exception.CashbackStrategyNotFoundException;
import com.bank.cashback.model.ClientTransaction;
import com.bank.cashback.model.TotalCashback;
import com.bank.cashback.strategy.CashBackStrategyType;
import com.bank.cashback.strategy.CashbackStrategy;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static com.bank.cashback.model.TransactionCategory.TRAVEL;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CashbackServiceTest {
    @Mock
    private UserCashbackService userCashbackService;
    @Mock
    private CashbackFactory factory;
    @Mock
    private CashbackStrategy strategy;

    @InjectMocks
    private CashbackService cashbackService;

    private final UUID userId = UUID.randomUUID();

    @Test
    void shouldReturnObjectWhenApplyCashbackTest() {
        List<ClientTransaction> inputPack = List.of(new ClientTransaction(UUID.randomUUID(), TRAVEL,1000L, 0));
        List<ClientTransaction> outputPack = List.of(new ClientTransaction(UUID.randomUUID(), TRAVEL,1000L, 20L));
        TotalCashback expected = new TotalCashback(outputPack, 20L);

        when(userCashbackService.getCashbackTypeByUserId(userId)).thenReturn(CashBackStrategyType.FIXED);
        when(factory.getCashbackStrategy(CashBackStrategyType.FIXED)).thenReturn(strategy);
        when(strategy.applyCashback(inputPack)).thenReturn(expected);

        TotalCashback result = cashbackService.applyCashback(userId, inputPack);

        assertEquals(expected, result);
        verify(strategy).applyCashback(inputPack);
    }

    @Test
    void shouldThrowsNotFoundWhenTypeIsNull(){
        when(userCashbackService.getCashbackTypeByUserId(userId)).thenReturn(null);

        assertThrows(CashbackStrategyNotFoundException.class,
                () -> cashbackService.applyCashback(userId, List.of(new ClientTransaction(UUID.randomUUID(), TRAVEL,1000L, 0))));

        verifyNoInteractions(factory);
    }

    @Test
    void shouldReturnEmptyWhenPackIsEmpty() {
        TotalCashback result = cashbackService.applyCashback(UUID.randomUUID(), Collections.emptyList());

        assertEquals(0L, result.getTotalCashback());
        assertTrue(result.getPack().isEmpty());
        verifyNoInteractions(userCashbackService, factory);
    }

    @Test
    void applyCashback_WrapsException_WhenStrategyFails() {
        List<ClientTransaction> pack = List.of(new ClientTransaction(UUID.randomUUID(), TRAVEL,1000L, 0));

        when(userCashbackService.getCashbackTypeByUserId(userId)).thenReturn(CashBackStrategyType.FIXED);
        when(factory.getCashbackStrategy(any())).thenReturn(strategy);

        when(strategy.applyCashback(pack)).thenThrow(new RuntimeException("Math error"));

        assertThrows(CashbackCalculationException.class,
                () -> cashbackService.applyCashback(userId, pack));
    }
}
