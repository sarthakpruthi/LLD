package entity;

import enums.UserLevel;

public class User {
    String name;
    public double points;
    public int orderCount;
    public double totalSpend;

    public User(String name) {
        this.name = name;
        this.points = 0;
        this.orderCount = 0;
        this.totalSpend = 0;
    }

    public UserLevel getLevel() {
        return UserLevel.getLevel(this.points);
    }
}
