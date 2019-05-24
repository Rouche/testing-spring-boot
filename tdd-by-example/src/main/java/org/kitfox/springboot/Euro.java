package org.kitfox.springboot;

/**
 * @Author Jean-Francois Larouche (jealar2) on 2019-05-24
 */
public class Euro extends Money {

    Euro(int amount, String currency) {
        super(amount, currency);
    }

    public Money times(int value) {
        return Money.euro(this.amount * value);
    }
}
