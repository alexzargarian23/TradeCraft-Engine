package com.tradecraft.model;

import com.tradecraft.account.Account;

public abstract  class Order {
    private final String ticker;
    private int quantity;
    private Account account;
    private static long idCounter=0;
    private final long orderId;


    public Order(String ticker, int quantity, Account account){

    this.ticker=ticker;
    this.account =account;
        this.quantity=quantity;
        this.orderId = ++idCounter;

    }
    public long getOrderId(){
        return  orderId;
    }

    public abstract double calculateExecutionCost(double marketPrice);


    public int  getQuantity(){
        return quantity;
    }
    public void setQuantity(int quantity){
        this.quantity = quantity;
    }
    public Account getAccount(){
        return account;
    }
    public void setAccount(Account account){
        this.account = account;
    }
    public String getTicker(){
        return ticker;
    }


}
