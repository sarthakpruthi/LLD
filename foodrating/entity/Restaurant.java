package org.example.entity;

import java.util.List;

public class Restaurant {

    int id;
    String name;
    List<Food> foods;
    double rating;

    public Restaurant(int id, String name, List<Food> foods, double rating) {
        this.id = id;
        this.name = name;
        this.foods = foods;
        this.rating = rating;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Food> getFoods() {
        return foods;
    }

    public void setFoods(List<Food> foods) {
        this.foods = foods;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }
}
