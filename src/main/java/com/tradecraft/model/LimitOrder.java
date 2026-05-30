package com.tradecraft.model;

import com.tradecraft.account.Account;

public class LimitOrder extends Order {
    private final double limitPrice;

    public LimitOrder(String ticker, int quantity, Account account, double limitPrice) {
        super(ticker, quantity, account);
        this.limitPrice = limitPrice;
    }

    @Override
    public double calculateExecutionCost(double marketPrice) {
        if(limitPrice < marketPrice) {
            return -1;
        }
        else{
            double value =  getQuantity() * marketPrice;
            return value*1.05;
        }
    }
}
