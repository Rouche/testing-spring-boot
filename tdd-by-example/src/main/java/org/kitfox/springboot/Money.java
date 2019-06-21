package org.kitfox.springboot;

import lombok.Data;

/**
 * @Author Jean-Francois Larouche (jealar2) on 2019-05-24
 */
@Data
public class Money implements Expression {

    protected final int amount;
    protected final String currency;

    public static Money dollar(int amount) {
        return new Money(amount, "USD");
    }

    public static Money euro(int amount) {
        return new Money(amount, "EU");
    }

    public Expression times(int value) {
        return new Money(this.amount * value, currency);
    }

    @Override
    public Money reduce(Bank bank, String to) {
        int rate = bank.rate(this.currency, to);
        return new Money(this.amount / rate, to);
    }

    public Expression plus(Expression addend) {
        return new Sum(this, addend);
    }

    /**
     * Lombok
     *
     * @param other Money
     * @return true if it can
     */
    protected boolean canEqual(Object other) {
        return this.currency.equals(((Money) other).currency);
    }
}