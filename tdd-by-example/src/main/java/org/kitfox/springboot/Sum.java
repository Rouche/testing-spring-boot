package org.kitfox.springboot;

/**
 * @Author Jean-Francois Larouche (jealar2) on 2019-06-03
 */
public class Sum implements Expression {

    public Money augmend;
    public Money addmend;

    public Sum(Money augmend, Money addmend) {
        this.augmend = augmend;
        this.addmend = addmend;
    }

    public Money reduce(String toCurrency) {
        int amount = augmend.amount + addmend.amount;
        return new Money(amount, toCurrency);
    }

}
