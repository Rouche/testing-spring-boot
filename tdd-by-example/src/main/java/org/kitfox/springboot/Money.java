package org.kitfox.springboot;

import lombok.Data;

/**
 * @Author Jean-Francois Larouche (jealar2) on 2019-05-24
 */
@Data
public class Money implements Expression {

    protected final int amount;
    protected final String currency;

    public Money(int amount, String currency) {
        this.amount = amount;
        this.currency = currency;
    }

    public static Money dollar(int amount) {
        return new Money(amount, "USD");
    }

    public static Money euro(int amount) {
        return new Money(amount, "EU");
    }

    public Money times(int value) {
        return new Money(this.amount * value, currency);
    }

    protected boolean canEqual(Object other) {
        return this.currency.equals(((Money) other).currency);
    }

    public String currency() {
        return this.currency;
    }

    @Override
    public Money reduce(String to) {
        return this;
    }

    public Expression plus(Money addend) {
        return new Sum(this, addend);
    }
}