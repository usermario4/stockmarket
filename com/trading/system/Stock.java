package com.trading.system;

public class Stock {
    private String tickerSymbol;
    private double currentPrice;
    private int quantity;

    public Stock(String tickerSymbol, double currentPrice, int quantity) {
        this.tickerSymbol = tickerSymbol;
        this.currentPrice = currentPrice;
        this.quantity = quantity;
    }

    public String getTickerSymbol() {
        return tickerSymbol;
    }

    public double getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(double currentPrice) {
        this.currentPrice = currentPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
