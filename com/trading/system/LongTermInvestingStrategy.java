package com.trading.system;

import com.trading.system.User;

public class LongTermInvestingStrategy implements TradingStrategy {
    @Override
    public void executeStrategy() {
        // Implement long-term investing logic
        System.out.println("Executing long-term investing strategy.");
    }

    @Override
    public void buyStock(User user, String stockSymbol, int quantity, double stockPrice) {
        double totalCost = quantity * stockPrice;
        if (totalCost > user.getFunds()) {
            // Handle insufficient funds error
            System.out.println("Insufficient funds for buying " + quantity + " shares of " + stockSymbol);
            return;
        }

        // Long-term investing: buy and hold
        user.buyStock(stockSymbol, quantity, stockPrice);
    }

    @Override
    public void sellStock(User user, String stockSymbol, int quantity, double stockPrice) {
        // Implement long-term investing sell logic
        if (!user.getPortfolio().containsKey(stockSymbol) || user.getPortfolio().get(stockSymbol) < quantity) {
            // Handle insufficient stocks error
            System.out.println("Insufficient stocks for selling " + quantity + " shares of " + stockSymbol);
            return;
        }

        // Long-term investing: sell anytime
        user.sellStock(stockSymbol, quantity, stockPrice);
    }
}
