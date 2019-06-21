package org.kitfox.springboot;

public interface Expression {

    Money reduce(Bank bank, String to);

    Expression plus(Expression addend);
}
