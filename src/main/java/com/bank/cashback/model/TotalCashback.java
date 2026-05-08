package com.bank.cashback.model;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@AllArgsConstructor
public class TotalCashback {
    List<ClientTransaction> pack;
    long totalCashback;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        TotalCashback that = (TotalCashback) o;
        return totalCashback == that.totalCashback && Objects.equals(pack, that.pack);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pack, totalCashback);
    }

    @Override
    public String toString() {
        return "TotalCashback{" +
                "pack=" + pack +
                ", totalCashback=" + totalCashback +
                '}';
    }
}
