package com.tradecraft.core;

import com.tradecraft.Service.TradeExecutionService;
import com.tradecraft.account.Account;
import com.tradecraft.account.AccountPermissionChanger;
import com.tradecraft.model.Order;
import com.tradecraft.model.MarketOrder;
import com.tradecraft.model.LimitOrder;

public class Engine implements TradeExecutionService {

    private final AccountPermissionChanger permissionChanger;
    private final double currentMarketPrice;


    public Engine() {
        this.permissionChanger = new AccountPermissionChanger();
        this.currentMarketPrice = 150.00;
    }

    @Override
    public void executeOrder(Order order) throws Exception {


        Account account = order.getAccount();


        if (!permissionChanger.hasPermission(account, AccountPermissionChanger.PERMIT_TRADE)) {
            throw new Exception("InvalidPermissionException: Account " + account.getAccountId()
                    + " does not have trading privileges.");
        }

        double cost = order.calculateExecutionCost(currentMarketPrice);

        if (cost == -1) {
            System.out.println("[REJECTED] Order #" + order.getOrderId()
                    + " failed limit price boundary checks at market price $" + currentMarketPrice);
            return;
        }

        if (account.getBalance() < cost) {
            double shortfall = cost - account.getBalance();
            throw new Exception("InsufficientFundsException: Account " + account.getAccountId()
                    + " lacks funds. Cost: $" + cost + ", Balance: $" + account.getBalance()
                    + ". Shortfall: $" + shortfall);
        }

        account.deductFunds(cost);
        System.out.println("[SUCCESS] Order #" + order.getOrderId() + " executed beautifully! "
                + "Account " + account.getAccountId() + " deducted $" + cost
                + ". Remaining balance: $" + account.getBalance());
    }

    public static void main(String[] args) {
        Engine engine = new Engine();

        System.out.println("=== INITIALIZING SIMULATION ACCOUNTS ===");
        Account alpha = new Account("ALPHA-101", 1500000, AccountPermissionChanger.PERMIT_TRADE);

        Account beta = new Account("BETA-202", 10000, AccountPermissionChanger.PERMIT_TRADE);

        Account charlie = new Account("CHARLIE-303", 500000, 0);

        Order[] orderBook = new Order[] {
                new MarketOrder("AAPL", 10, alpha),                       // Should pass completely
                new MarketOrder("TSLA", 5, beta),                         // Should trigger InsufficientFundsException
                new LimitOrder("NVDA", 2, alpha, 160.00),                 // Limit ($160) >= Market ($150) -> Should pass
                new LimitOrder("AMD", 5, alpha, 140.00),                  // Limit ($140) < Market ($150) -> Should reject gracefully (-1)
                new MarketOrder("MSFT", 1, charlie)                       // Should trigger InvalidPermissionException
        };

        System.out.println("\n=== STARTING TRADING ENGINE EXECUTION LOOP ===");

        for (Order order : orderBook) {
            try {
                engine.executeOrder(order);
            } catch (Exception e) {
                System.err.println("[EXCEPTION CAUGHT] " + e.getMessage());
            }
            System.out.println("----------------------------------------------------------------");
        }

        System.out.println("\n=== SIMULATION COMPLETE ===");
    }
}