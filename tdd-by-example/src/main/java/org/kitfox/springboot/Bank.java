package org.kitfox.springboot;

/**
 * @Author Jean-Francois Larouche (jealar2) on 2019-06-03
 */
public class Bank {

    public Money reduce(Expression exp, String toCurrency) {
//        if(exp instanceof Money) {
//            return (Money)exp;
//        }
//        Sum sum = (Sum)exp;
//        return sum.reduce(toCurrency);

        return exp.reduce(toCurrency);
    }
}
