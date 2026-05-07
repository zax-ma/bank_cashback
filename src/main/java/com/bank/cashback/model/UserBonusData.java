package com.bank.cashback.model;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter
@Setter
public class UserBonusData {
    UUID userId;
    TransactionCategory cashbackType;
}
