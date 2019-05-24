package org.kitfox.springboot;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

/**
 * @Author Jean-Francois Larouche (jealar2) on 2019-05-24
 */
public class MoneyTest {

    @Test
    void testMultiplicationDollar() {
        Money five = Money.dollar(5);
        assertEquals(Money.dollar(10), five.times(2));
        assertEquals(Money.dollar(15), five.times(3));
    }

    @Test
    void testEqualityDollar() {
        assertEquals(Money.dollar(5), Money.dollar(5));
        assertNotEquals(Money.dollar(5), Money.dollar(8));
        assertNotEquals(Money.euro(5), Money.dollar(5));
    }

    @Test
    void testMultiplicationEuro() {
        Money five = Money.euro(5);
        assertEquals(Money.euro(10), five.times(2));
        assertEquals(Money.euro(15), five.times(3));
    }

    @Test
    void testEqualityEuro() {
        assertEquals(Money.euro(5), Money.euro(5));
        assertNotEquals(Money.euro(5), Money.euro(8));
    }

    @Test
    void testCurrency() {
        assertEquals("USD", Money.dollar(1).currency());
    }
}
