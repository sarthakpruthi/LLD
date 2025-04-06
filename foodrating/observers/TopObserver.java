package org.example.observers;

import org.example.entity.Order;

public interface TopObserver {
    void update(Order order, int rating);
}
