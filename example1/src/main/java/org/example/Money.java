package org.example;

public class Money implements Expression{
    protected int amount;

    protected String currency;

    public Money(int amount,String currency) {
        this.amount=amount;
        this.currency=currency;
    }

    public static Money franc(int amount) {
        return new Money(amount,"CHF");
    }

    static Money dollar(int amount){
        return new Money(amount,"USD");
    }

    public Expression times(int multiplier) {
        return new Money(multiplier*amount,currency);
    }

    String currency(){
        return currency;
    }

    @Override
    public boolean equals(Object o){
        Money money=(Money) o;
        return money.amount==amount && currency().equals(money.currency());
    }

    @Override
    public String toString() {
        return amount+"  "+currency;
    }

    public Expression plus(Expression addend) {
        return new Sum(this,addend);
    }

    @Override
    public Money reduce(Bank bank, String to) {
        int rate=bank.rate(currency,to);
        return new Money(amount/rate,to);
    }
}
