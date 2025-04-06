package org.example.entity;

public class Rating {

    final int totalRating;
    final int count;


    public Rating(int totalRating, int count) {
        this.totalRating = totalRating;
        this.count = count;
    }

    public double getAvgRating() {
        return totalRating*1.0/count;
    }

    public Rating add(int totalRating){
        return new Rating(this.totalRating + totalRating, this.count+1);
    }

}
