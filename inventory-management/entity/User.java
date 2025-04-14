package entity;

import java.util.ArrayList;
import java.util.List;

public class User {
    int id;
    String name;
    List<String> orderIds;
    Cart cart;

    public User(int id, String name) {
        this.id = id;
        this.name = name;
        this.orderIds = new ArrayList<>();
        this.cart = new Cart();
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

    public List<String> getOrderIds() {
        return orderIds;
    }

    public void setOrderIds(List<String> orderIds) {
        this.orderIds = orderIds;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }
}
