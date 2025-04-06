package org.example.observers;

import org.example.entity.Order;
import org.example.entity.Rating;
import org.example.entity.Restaurant;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;

public class TopRestaurant implements TopObserver{
    //restaurant id, rating -> [count, rating]
    ConcurrentHashMap<Integer, Rating> topRestaurants = new ConcurrentHashMap<>();

    @Override
    public void update(Order order, int currRating) {
        topRestaurants.compute(order.getRestaurantId(), (key, rating) -> {
            if (rating == null) {
                rating = new Rating(0, 0);
            }
            return rating.add(currRating);
        });
    }

    public List<Integer> getTopRestaurant(int n){
//        PriorityQueue<Integer> pq = new PriorityQueue<>((a,b)->{
//            Rating one = topRestaurants.get(a);
//            Rating two = topRestaurants.get(b);
//            return one.getAvgRating() > two.getAvgRating() ? 1 : -1;
//        });
//
//        for(Map.Entry<Integer,Rating> val:topRestaurants.entrySet()){
//            pq.add(val.getKey());
//            if(pq.size() > n) pq.remove();
//        }
//        return new ArrayList<>(pq);


        return topRestaurants.entrySet()
                .stream()
                .sorted(Comparator.comparingDouble(a -> a.getValue().getAvgRating()))
                .limit(n)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }
}
