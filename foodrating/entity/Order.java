package org.example.entity;

import java.util.List;

public class Order {
    int id;
    List<Food> foods;
    int restaurantId;

    public Order(int id, List<Food> foods, int restaurantId) {
        this.id = id;
        this.foods = foods;
        this.restaurantId = restaurantId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Food> getFoods() {
        return foods;
    }

    public void setFoods(List<Food> foods) {
        this.foods = foods;
    }

    public int getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }
}
