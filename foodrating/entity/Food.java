package org.example.entity;

public class Food {
    int id;
    String name;
    boolean isVeg;

    public Food(int id, String name, boolean isVeg) {
        this.id = id;
        this.name = name;
        this.isVeg = isVeg;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isVeg() {
        return isVeg;
    }

    public void setVeg(boolean veg) {
        isVeg = veg;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
