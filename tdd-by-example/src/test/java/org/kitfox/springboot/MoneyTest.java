package org.kitfox.springboot;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

/**
 * @author Jean-Francois Larouche (jealar2) on 2019-05-24
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
        assertEquals("USD", Money.dollar(1).getCurrency());
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
    void testSumPlusMoney() {
        Expression fiveDol = Money.dollar(5);
        Expression tenEu = Money.euro(10);
        Bank bank = new Bank();
        bank.addRate("EU", "USD", 2);
        Expression sum = new Sum(fiveDol, tenEu).plus(fiveDol);
        Money result = bank.reduce(sum, "USD");
        assertEquals(Money.dollar(15), result);
    }

    @Test
    void testSumTimes() {
        Expression fiveDol = Money.dollar(5);
        Expression tenEu = Money.euro(10);
        Bank bank = new Bank();
        bank.addRate("EU", "USD", 2);
        Expression sum = new Sum(fiveDol, tenEu).times(2);
        Money result = bank.reduce(sum, "USD");
        assertEquals(Money.dollar(20), result);
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

    @Test
    void testReduceMoneyDifferentCurrency() {
        Bank bank = new Bank();
        bank.addRate("EU", "USD", 2);
        Money result = bank.reduce(Money.euro(2), "USD");
        assertEquals(Money.dollar(1), result);
    }

    @Test
    void testIdentityRate() {
        assertEquals(1, new Bank().rate("USD", "USD"));
        assertEquals(1, new Bank().rate("EU", "EU"));
    }

    @Test
    void testMixedAddition() {
        Expression fiveDol = Money.dollar(5);
        Expression tenEu = Money.euro(10);
        Bank bank = new Bank();
        bank.addRate("EU", "USD", 2);
        Money result = bank.reduce(fiveDol.plus(tenEu), "USD");
        assertEquals(Money.dollar(10), result);
    }
}
