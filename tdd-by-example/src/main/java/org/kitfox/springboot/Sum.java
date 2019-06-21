package org.kitfox.springboot;

/**
 * @Author Jean-Francois Larouche (jealar2) on 2019-06-03
 */
public class Sum implements Expression {

    public Expression augmend;
    public Expression addmend;

    public Sum(Expression augmend, Expression addmend) {
        this.augmend = augmend;
        this.addmend = addmend;
    }

    @Override
    public Money reduce(Bank bank, String toCurrency) {
        int amount = augmend.reduce(bank, toCurrency).amount + addmend.reduce(bank, toCurrency).amount;
        return new Money(amount, toCurrency);
    }

    @Override
    public Expression plus(Expression addend) {
        return null;
    }

}
