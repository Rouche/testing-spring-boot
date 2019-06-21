package org.kitfox.springboot;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

/**
 * @Author Jean-Francois Larouche (jealar2) on 2019-06-03
 */
public class Bank {

    private Map<Pair, Integer> rateMap = new HashMap<>();

    public Money reduce(Expression exp, String toCurrency) {
//        if(exp instanceof Money) {
//            return (Money)exp;
//        }
//        Sum sum = (Sum)exp;
//        return sum.reduce(toCurrency);

        return exp.reduce(this, toCurrency);
    }

    public void addRate(String from, String to, int rate) {
        rateMap.put(new Pair(from, to), rate);
    }

    public int rate(String from, String to) {
        if (StringUtils.equals(from, to)) {
            return 1;
        }

        return rateMap.get(new Pair(from, to));
    }
}
