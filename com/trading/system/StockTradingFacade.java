package com.trading.system;

import com.trading.system.Stock;
import com.trading.system.User;
import com.trading.system.StockManager;

import java.util.List;

public class StockTradingFacade {
    private StockManager stockManager;
    private UserFactory userFactory;

    public StockTradingFacade(StockManager stockManager, UserFactory userFactory) {
        this.stockManager = stockManager;
        this.userFactory = userFactory;
    }

    public void buyStock(User user, String stockSymbol, int quantity) {
        Stock stock = stockManager.getStock(stockSymbol);
        if (stock == null) {
            System.out.println("Stock " + stockSymbol + " not found.");
            return;
        }

        double stockPrice = stock.getCurrentPrice();
        double totalCost = quantity * stockPrice;
        if (totalCost > user.getFunds()) {
            System.out.println(user.getUsername() + ": Insufficient funds to buy " + quantity + " shares of " + stockSymbol);
            return;
        }

        if (stock.getQuantity() < quantity) {
            System.out.println("Stock " + stockSymbol + " is not available in sufficient quantity.");
            return;
        }

        user.buyStock(stockSymbol, quantity, stockPrice);
        stockManager.updateStockQuantity(stockSymbol, -quantity);

        System.out.println(user.getUsername() + " bought " + quantity + " shares of " + stockSymbol + " at $" + stockPrice);
    }

    public void sellStock(User user, String stockSymbol, int quantity) {
        Stock stock = stockManager.getStock(stockSymbol);
        if (stock == null) {
            System.out.println("Stock " + stockSymbol + " not found.");
            return;
        }

        if (!user.getPortfolio().containsKey(stockSymbol) || user.getPortfolio().get(stockSymbol) < quantity) {
            System.out.println(user.getUsername() + ": Insufficient stocks to sell " + quantity + " shares of " + stockSymbol);
            return;
        }

        double stockPrice = stock.getCurrentPrice();
        user.sellStock(stockSymbol, quantity, stockPrice);
        stockManager.updateStockQuantity(stockSymbol, quantity);

        System.out.println(user.getUsername() + " sold " + quantity + " shares of " + stockSymbol + " at $" + stockPrice);
    }

    public String getUserPortfolio(User user) {
        StringBuilder portfolioInfo = new StringBuilder();
        portfolioInfo.append("Portfolio information for ").append(user.getUsername()).append(":\n");

        for (String stockSymbol : user.getPortfolio().keySet()) {
            int quantity = user.getPortfolio().get(stockSymbol);
            double currentPrice = stockManager.getStock(stockSymbol).getCurrentPrice();
            double totalValue = quantity * currentPrice;

            portfolioInfo.append(stockSymbol).append(": ")
                    .append("Quantity = ").append(quantity).append(", ")
                    .append("Current Price = $").append(currentPrice).append(", ")
                    .append("Total Value = $").append(totalValue).append("\n");
        }

        return portfolioInfo.toString();
    }

    public String getTransactionHistory(User user) {
        return "Transaction history for " + user.getUsername() + ":\n" + user.getTransactionHistoryAsString();
    }

    public void addStock(String stockSymbol, double initialPrice, int initialQuantity) {
        stockManager.addStock(stockSymbol, initialPrice, initialQuantity);
    }

    public List<Stock> getAllStocks() {
        return stockManager.getAllStocks();
    }
}
