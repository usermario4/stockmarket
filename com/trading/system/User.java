package com.trading.system;

import com.trading.system.TradingStrategy;

import java.util.HashMap;
import java.util.Map;

public class User {
    private String username;
    private Map<String, Integer> portfolio;
    private double funds;
    private TradingStrategy tradingStrategy;
    private StringBuilder transactionHistory;

    public User(String username, double initialFunds, TradingStrategy tradingStrategy) {
        this.username = username;
        this.funds = initialFunds;
        this.portfolio = new HashMap<>();
        this.tradingStrategy = tradingStrategy;
        this.transactionHistory = new StringBuilder();
    }
    public void deductFunds(double amount) {
        if (amount > funds) {
            
            System.out.println("Insufficient funds for deduction.");
        } else {
            funds -= amount;
        }
    }

    public void setTradingStrategy(TradingStrategy tradingStrategy) {
        this.tradingStrategy = tradingStrategy;
    }

    public void executeTradingStrategy() {
        tradingStrategy.executeStrategy();
    }

    public void buyStock(String stockSymbol, int quantity, double stockPrice) {
        double totalCost = quantity * stockPrice;
        if (totalCost > funds) {
            // Handle insufficient funds error
            System.out.println("Insufficient funds for buying " + quantity + " shares of " + stockSymbol);
            return;
        }

        funds -= totalCost;
        portfolio.put(stockSymbol, portfolio.getOrDefault(stockSymbol, 0) + quantity);

        System.out.println("DEBUG: Buy Stock - " + username + " bought " + quantity + " shares of " + stockSymbol + " at $" + stockPrice);
        transactionHistory.append("Bought ")
                .append(quantity)
                .append(" shares of ")
                .append(stockSymbol)
                .append(" at $")
                .append(stockPrice)
                .append("\n");
    }

    public void sellStock(String stockSymbol, int quantity, double stockPrice) {
        if (!portfolio.containsKey(stockSymbol) || portfolio.get(stockSymbol) < quantity) {
            // Handle insufficient stocks error
            System.out.println("Insufficient stocks for selling " + quantity + " shares of " + stockSymbol);
            return;
        }

        funds += quantity * stockPrice;
        portfolio.put(stockSymbol, portfolio.get(stockSymbol) - quantity);

        System.out.println("DEBUG: Sell Stock - " + username + " sold " + quantity + " shares of " + stockSymbol + " at $" + stockPrice);
        transactionHistory.append("Sold ")
                .append(quantity)
                .append(" shares of ")
                .append(stockSymbol)
                .append(" at $")
                .append(stockPrice)
                .append("\n");
    }

    public String getTransactionHistoryAsString() {
        return transactionHistory.toString();
    }
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Map<String, Integer> getPortfolio() {
		return portfolio;
	}

	public void setPortfolio(Map<String, Integer> portfolio) {
		this.portfolio = portfolio;
	}

	public double getFunds() {
		return funds;
	}

	public void setFunds(double funds) {
		this.funds = funds;
	}

	public TradingStrategy getTradingStrategy() {
		return tradingStrategy;
	}

   
}