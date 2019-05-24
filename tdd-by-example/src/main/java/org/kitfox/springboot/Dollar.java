package org.kitfox.springboot;

/**
 * @Author Jean-Francois Larouche (jealar2) on 2019-05-24
 */
public final class Dollar extends Money {

    Dollar(int amount, String currency) {
        super(amount, currency);
    }

    public Money times(int value) {
        return Money.dollar(this.amount * value);
    }
}
