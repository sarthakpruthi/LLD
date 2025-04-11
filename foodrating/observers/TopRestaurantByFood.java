package org.example.observers;

import org.example.entity.Food;
import org.example.entity.Order;
import org.example.entity.Rating;
import org.example.entity.Restaurant;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class TopRestaurantByFood implements TopObserver{
    //food id, restaurant id, rating
    ConcurrentHashMap<Integer, ConcurrentHashMap<Integer, Rating>> topRestaurants = new ConcurrentHashMap<>();
    @Override
    public void update(Order order, int currRating) {
        int restaurantId = order.getRestaurantId();
        for(Food food : order.getFoods()){
            // ConcurrentHashMap<Integer, Rating> restRating = topRestaurants.getOrDefault(food.getId(), new ConcurrentHashMap<>());
            // Rating rating = restRating.getOrDefault(restaurantId, new Rating(0, 0));
            // rating.add(currRating);
            // restRating.putIfAbsent(restaurantId, rating);
            // topRestaurants.putIfAbsent(food.getId(), restRating);

            topRestaurants.compute(food.getId(), (foodId, restRating) -> {
                if (restRating == null) {
                    restRating = new ConcurrentHashMap<>();
                }
                restRating.compute(restaurantId, (restId, rating) -> {
                    if (rating == null) {
                        rating = new Rating(0, 0);
                    }
                    return rating.add(currRating);
                });
                return restRating;
            });

        }
    }


    public List<Integer> getTopRestaurantByFoodId(int foodId, int n){
        Map<Integer,Rating> restRating = topRestaurants.getOrDefault(foodId, null);
        if(restRating == null) return List.of();
        PriorityQueue<Integer> pq = new PriorityQueue<>((a,b)->{
            Rating one = restRating.get(a);
            Rating two = restRating.get(b);
            return one.getAvgRating() > two.getAvgRating() ? 1 : -1;
        });
        for(Map.Entry<Integer,Rating> val:restRating.entrySet()){
            pq.add(val.getKey());
            if(pq.size() > n) pq.remove();
        }
        return new ArrayList<>(pq);
    }
}
