package com.trading.system;

import com.trading.system.User;
import com.trading.system.DayTradingStrategy;
import com.trading.system.LongTermInvestingStrategy;
import com.trading.system.TradingStrategy;

public class UserFactory {
    public static User createUser(String userType) {
        double initialFunds = 10000.0; 

        TradingStrategy tradingStrategy;
        switch (userType) {
            case "DayTrader":
                tradingStrategy = new DayTradingStrategy();
                break;
            case "LongTermInvestor":
                tradingStrategy = new LongTermInvestingStrategy();
                break;
            default:
                throw new IllegalArgumentException("Invalid user type: " + userType);
        }

        return new User(userType, initialFunds, tradingStrategy);
    }
}
