package com.tradecraft.exception;

public class InvalidPermissionException extends TradeException {
    private final String permission;
    public InvalidPermissionException(String message, String permission) {
        super(message);
        this.permission = permission;
    }
    public String getPermission() {
        return permission;
    }
}
