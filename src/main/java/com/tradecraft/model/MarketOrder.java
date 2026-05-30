package com.tradecraft.model;

import com.tradecraft.account.Account;

public class MarketOrder extends Order {

    public MarketOrder(String ticker, int quantity, Account account) {
        super(ticker, quantity, account);
    }

    @Override
    public double calculateExecutionCost(double marketPrice) {

        double value =  getQuantity() * marketPrice;
        return value*1.05;
    }


}
