package entity;

import enums.Status;

import java.util.Map;

public class Order {
    int id;
    User user;
    Map<Integer, Integer> productIdCount;
    Warehouse warehouse;
    int totAmount;
    Status status;

    public Order(int id, User user, Map<Integer, Integer> productIdCount, Warehouse warehouse, int totAmount, Status status) {
        this.id = id;
        this.user = user;
        this.productIdCount = productIdCount;
        this.warehouse = warehouse;
        this.totAmount = totAmount;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Warehouse getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(Warehouse warehouse) {
        this.warehouse = warehouse;
    }

    public int getTotAmount() {
        return totAmount;
    }

    public void setTotAmount(int totAmount) {
        this.totAmount = totAmount;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
