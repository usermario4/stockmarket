package com.trading.system;

import com.trading.system.StockTradingFacade;

public class Main {
    public static void main(String[] args) {
    
        StockManager stockManager = StockManager.getInstance();

        stockManager.addStock("AAPL", 150.0, 100);
        stockManager.addStock("GOOGL", 2000.0, 50);
        
        User dayTrader = UserFactory.createUser("DayTrader");
        User longTermInvestor = UserFactory.createUser("LongTermInvestor");

       
        dayTrader.setTradingStrategy(new DayTradingStrategy());
        longTermInvestor.setTradingStrategy(new LongTermInvestingStrategy());

        
        StockTradingFacade tradingFacade = new StockTradingFacade(stockManager, new UserFactory());

       
        tradingFacade.buyStock(dayTrader, "AAPL", 10);
        tradingFacade.sellStock(dayTrader, "AAPL", 5);

  
        tradingFacade.buyStock(longTermInvestor, "GOOGL", 8);
        tradingFacade.sellStock(longTermInvestor, "GOOGL", 3);

        
        dayTrader.setTradingStrategy(new LongTermInvestingStrategy());
        tradingFacade.buyStock(dayTrader, "MSFT", 15);

        
        System.out.println("\nDay Trader's Portfolio:");
        System.out.println(tradingFacade.getUserPortfolio(dayTrader));

        System.out.println("\nLong-Term Investor's Portfolio:");
        System.out.println(tradingFacade.getUserPortfolio(longTermInvestor));

        System.out.println("\nDay Trader's Transaction History:");
        System.out.println(tradingFacade.getTransactionHistory(dayTrader));

        System.out.println("\nLong-Term Investor's Transaction History:");
        System.out.println(tradingFacade.getTransactionHistory(longTermInvestor));

    }
}
