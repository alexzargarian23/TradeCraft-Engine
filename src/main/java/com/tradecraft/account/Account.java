package com.tradecraft.account;

public class Account {
    private  String accountId;
    private long balanceInCents;
    private int permissionFlags;

    public Account(String accountId, long balanceInCents, int permissionFlags) {
        this.accountId = accountId;
        this.balanceInCents = balanceInCents;
        this.permissionFlags = permissionFlags;
    }
    public int getPermissionFlags() {
        return permissionFlags;
    }
    public void setPermissionFlags(int permissionFlags) {
        this.permissionFlags = permissionFlags;
    }
    public String getAccountId() {
        return accountId;
    }
    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }
    public long getBalanceInCents() {
        return  balanceInCents;
    }
    public void setBalanceInCents(long balanceInCents) {
        this.balanceInCents = balanceInCents;
    }
    public void deductFunds(double amountInDollars) {
        long centsToDeduct = Math.round(amountInDollars * 100.0);
        this.balanceInCents -= centsToDeduct;
    }

    public double getBalance() {
        return balanceInCents;
    }
}
