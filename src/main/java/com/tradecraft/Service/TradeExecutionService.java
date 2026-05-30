package com.tradecraft.Service;

import com.tradecraft.exception.TradeException;
import com.tradecraft.model.Order;

public interface TradeExecutionService {
    void executeOrder(Order order) throws Exception;
}
