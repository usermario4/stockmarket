package com.trading.system;
import com.trading.system.User;


public interface TradingStrategy {
    void executeStrategy();
    void buyStock(User user, String stockSymbol, int quantity, double stockPrice);
    void sellStock(User user, String stockSymbol, int quantity, double stockPrice);
}
