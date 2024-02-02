package com.trading.system;

import java.util.ArrayList;
import java.util.List;

public class StockManager {
    private static StockManager instance;
    private List<Stock> stocks;

    private StockManager() {
        stocks = new ArrayList<>();
    }

    public static StockManager getInstance() {
        if (instance == null) {
            instance = new StockManager();
        }
        return instance;
    }

    public void addStock(String stockSymbol, double initialPrice, int initialQuantity) {
        Stock stock = new Stock(stockSymbol, initialPrice, initialQuantity);
        stocks.add(stock);
    }


    public void updateStockPrice(String stockSymbol, double newPrice) {
        for (Stock stock : stocks) {
            if (stock.getTickerSymbol().equals(stockSymbol)) {
                double oldPrice = stock.getCurrentPrice();
                stock.setCurrentPrice(newPrice);

                
                notifyUsers(stockSymbol, oldPrice, newPrice);
                break;
            }
        }
    }

    private void notifyUsers(String stockSymbol, double oldPrice, double newPrice) {
        
        System.out.println("Notifying users about the price update for " + stockSymbol +
                ": Old Price = $" + oldPrice + ", New Price = $" + newPrice);
    }

    public void updateStockQuantity(String stockSymbol, int quantityChange) {
        for (Stock stock : stocks) {
            if (stock.getTickerSymbol().equals(stockSymbol)) {
                int oldQuantity = stock.getQuantity();
                int newQuantity = oldQuantity + quantityChange;
                stock.setQuantity(newQuantity);

                notifyUsersQuantityChange(stockSymbol, oldQuantity, newQuantity);
                break;
            }
        }
    }

    private void notifyUsersQuantityChange(String stockSymbol, int oldQuantity, int newQuantity) {
        System.out.println("Notifying users about the quantity change for " + stockSymbol +
                ": Old Quantity = " + oldQuantity + ", New Quantity = " + newQuantity);
    }

    public Stock getStock(String stockSymbol) {
        for (Stock stock : stocks) {
            if (stock.getTickerSymbol().equals(stockSymbol)) {
                return stock;
            }
        }
        return null;
    }

    public List<Stock> getAllStocks() {
        return new ArrayList<>(stocks);
    }

}
