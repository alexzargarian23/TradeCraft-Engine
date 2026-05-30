package com.tradecraft.account;

public class AccountPermissionChanger {

    public static final int PERMIT_TRADE = 1;
    public static final int PERMIT_SHORT = 2;
    public static final int IS_PREMIUM   = 4;


    public boolean hasPermission(Account account, int targetPermission) {
        // 2. Added parentheses around the bitwise math to ensure correct ordering
        if ((account.getPermissionFlags() & targetPermission) != 0) {
            return true;
        } else {
            return false;
        }
    }


    public void addPermission(Account account, int targetPermission) {
        account.setPermissionFlags(account.getPermissionFlags() | targetPermission);
    }
    public void removePermission(Account account, int targetPermission) {
        account.setPermissionFlags(account.getPermissionFlags() & ~targetPermission);
    }
}