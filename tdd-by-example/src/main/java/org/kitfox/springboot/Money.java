package org.kitfox.springboot;

import lombok.EqualsAndHashCode;

/**
 * @Author Jean-Francois Larouche (jealar2) on 2019-05-24
 */
@EqualsAndHashCode
public class Money {

    protected final int amount;
    protected final String currency;

    public static Money dollar(int amount) {
        return new Dollar(amount, "USD");
    }

    public static Money euro(int amount) {
        return new Euro(amount, "EU");
    }

    public Money times(int value) {
        return new Money(this.amount * value, currency);
    }

    public Money(int amount, String currency) {
        this.amount = amount;
        this.currency = currency;
    }

    protected boolean canEqual(Object other) {
        return this.currency.equals(((Money) other).currency);
    }

    public String currency() {
        return this.currency;
    }
}