package org.example;

import org.example.entity.Food;
import org.example.entity.Order;
import org.example.entity.Restaurant;
import org.example.observers.TopObserver;
import org.example.observers.TopRestaurant;
import org.example.observers.TopRestaurantByFood;

import java.util.ArrayList;
import java.util.List;
import java.util.Observer;

public class OrderManager {
    List<TopObserver> observers;
    TopRestaurant topRestaurants;
    TopRestaurantByFood topRestaurantsByFood;

    OrderManager(){
        observers = new ArrayList<>();
        topRestaurants = new TopRestaurant();
        topRestaurantsByFood = new TopRestaurantByFood();
        observers.add(topRestaurants);
        observers.add(topRestaurantsByFood);
    }

    String orderFood(List<Food> foods, Restaurant restaurant){
        return "order placed";
    }

    void rateOrder(Order order, int rating){
        for(TopObserver observer : observers){
            observer.update(order,rating);
        }
    }

    List<Integer> mostRatedRestaurants(){
        return topRestaurants.getTopRestaurant(6);
    }

    List<Integer> mostRatedRestaurants(Integer foodId){
        return topRestaurantsByFood.getTopRestaurantByFoodId(foodId, 6);
    }
}
