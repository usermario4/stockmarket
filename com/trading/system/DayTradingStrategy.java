package com.trading.system;

import com.trading.system.User;

public class DayTradingStrategy implements TradingStrategy {
    @Override
    public void executeStrategy() {
        
        System.out.println("Executing day trading strategy.");
    }

    @Override
    public void buyStock(User user, String stockSymbol, int quantity, double stockPrice) {
        double totalCost = quantity * stockPrice;
        if (totalCost > user.getFunds()) {
            
            System.out.println("Insufficient funds for day trading " + quantity + " shares of " + stockSymbol);
            return;
        }

        
        user.sellStock(stockSymbol, quantity, stockPrice);
    }

    @Override
    public void sellStock(User user, String stockSymbol, int quantity, double stockPrice) {
        
        if (!user.getPortfolio().containsKey(stockSymbol) || user.getPortfolio().get(stockSymbol) < quantity) {
           
            System.out.println("Insufficient stocks for day trading " + quantity + " shares of " + stockSymbol);
            return;
        }

        
        user.sellStock(stockSymbol, quantity, stockPrice);
    }
}
