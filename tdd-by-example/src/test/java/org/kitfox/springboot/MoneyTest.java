package org.kitfox.springboot;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

/**
 * @Author Jean-Francois Larouche (jealar2) on 2019-05-24
 */
public class MoneyTest {

    @Test
    void testMultiplication() {
        Money five = Money.dollar(5);
        assertEquals(Money.dollar(10), five.times(2));
        assertEquals(Money.dollar(15), five.times(3));

        Money fiveE = Money.euro(5);
        assertEquals(Money.euro(10), fiveE.times(2));
    }

    @Test
    void testEquality() {
        assertEquals(Money.dollar(5), Money.dollar(5));
        assertNotEquals(Money.dollar(5), Money.dollar(8));
        assertEquals(Money.euro(5), Money.euro(5));
        assertNotEquals(Money.euro(5), Money.dollar(5));
    }

    @Test
    void testCurrency() {
        assertEquals("USD", Money.dollar(1).currency());
    }

    @Test
    void testSimpleAddition() {
        Money five = Money.dollar(5);
        Expression sum = five.plus(five);
        Bank bank = new Bank();
        Money reduced = bank.reduce(sum, "USD");
        assertEquals(Money.dollar(10), reduced);
    }

    @Test
    void testPlusReturnsSum() {
        Money five = Money.dollar(5);
        Expression result = five.plus(five);
        Sum sum = (Sum) result;
        assertEquals(five, sum.augmend);
        assertEquals(five, sum.addmend);
    }

    @Test
    void testReduceSum() {
        Sum sum = new Sum(Money.dollar(3), Money.dollar(4));
        Bank bank = new Bank();
        Money reduced = bank.reduce(sum, "USD");
        assertEquals(Money.dollar(7), reduced);
    }

    @Test
    void testReduceMoney() {
        Bank bank = new Bank();
        Money reduced = bank.reduce(Money.dollar(1), "USD");
        assertEquals(Money.dollar(1), reduced);
    }
}
