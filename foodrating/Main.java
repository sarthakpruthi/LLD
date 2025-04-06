package org.example;

import org.example.entity.Food;
import org.example.entity.Order;
import org.example.entity.Restaurant;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Food pizza = new Food(1, "Pizza", false);
        Food burger = new Food(2, "Burger", false);
        Food salad = new Food(3, "Salad", true);

        // Sample Restaurants
        Restaurant dominos = new Restaurant(101, "Domino's", Arrays.asList(pizza, burger), 4.5);
        Restaurant subway = new Restaurant(102, "Subway", Arrays.asList(salad, burger), 4.2);

        OrderManager orderManager = new OrderManager();
        System.out.println("Hello world!");
    }
}

/**

    Write code for low level design of a restaurant food ordering and rating system, similar to food delivery apps like Zomato,Swiggy, Door Dash, Uber Eats etc.

        There will be food items like 'Veg Burger', 'Veg Spring Roll', 'Ice Cream' etc.
        And there will be restaurants from where you can order these food items.

        Same food item can be ordered from multiple restaurants. e.g. you can order 'food-1' 'veg burger' from burger king as
        well as from McDonald's.

        Users can order food, rate orders, fetch restaurants with most rating and fetch restaurants with most rating for
        a particular food item e.g. restaurants which have the most rating for 'veg burger'.


        order food
        rate order
        get restaurant with most rating -> numb to be given
        get restaurant with most rating for a food -> num to be given

        Food -> name, desc, isVeg
        Order -> id, List<food>, restaurantId
        Restaurant -> id, name, List<food>, rating
        Rating -> totalRating, countOfReviews

        OrderManagement

        String orderFood(List<food> foods, Restaurant restaurant);
        void rateOrder(Order order, double rating);

        bir - r1 - 8
        bur - r1 - 1
        r1 = 4.5
        bir - r1 - 8
        bur - r1 - 1

        //food id, restaurant id, rating
        Map<String, Map<restaurantId,rating>>
        bir r1 - 9
        bir r2 - 8
        void updateRating(Order order, double rating)
        List<Restaurant> getTopRestaurantByFoodId(int foodId,int n);

        Map<restaurantId, rating>
        void updateRating(Order order, double rating); o(1)
        List<Restaurant> getTopRestaurant(int n); o(nlogn)

**/