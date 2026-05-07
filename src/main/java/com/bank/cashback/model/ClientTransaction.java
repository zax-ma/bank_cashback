package com.bank.cashback.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@Builder(toBuilder = true)
public class ClientTransaction {
    UUID id;
    @Enumerated(EnumType.STRING)
    TransactionCategory category;
    long amount;
    long cashback;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ClientTransaction that = (ClientTransaction) o;
        return amount == that.amount && Objects.equals(id, that.id) && category == that.category;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, category, amount);
    }

    @Override
    public String toString() {
        return "ClientTransaction{" +
                "id=" + id +
                ", category=" + category +
                ", amount=" + amount +
                ", cashback=" + cashback +
                '}';
    }
}
