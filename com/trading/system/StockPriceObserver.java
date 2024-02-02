package com.trading.system;

public interface StockPriceObserver {
    void updatePrice(String stockSymbol, double newPrice);
}
