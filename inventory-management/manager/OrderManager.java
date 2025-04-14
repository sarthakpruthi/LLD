package manager;

import entity.*;
import enums.Status;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class OrderManager {
    List<Order> orders;

    UserManager userManager;

    public OrderManager(UserManager userManager){
        this.userManager = userManager;
        this.orders = new ArrayList<>();
    }

    public void placeOrder(int id, Warehouse warehouse){
        User user = userManager.getUserById(id);
        Cart cart = user.getCart();
        int totalPrice = printGetTotPriceProducts(cart.getProductIdCount(), warehouse);
        System.out.println("total price: " + totalPrice + " products above are ordered successfully from warehouse " + warehouse.getAddress());

        Order order = new Order(UUID.randomUUID().variant(), user, cart.getProductIdCount(),warehouse, totalPrice, Status.CONFIRMED );
        // order dispatch logic
        orders.add(order);

        cart.resetCart();
    }

    private int printGetTotPriceProducts(Map<Integer, Integer> productIdCount, Warehouse warehouse) {
        int tot = 0;
        for(var val: productIdCount.entrySet()) {
            Product productById = warehouse.getProductById(val.getKey());
            System.out.println(productById.toString() + " " + "quantity : " + val.getValue());
            tot += productById.getPrice() * val.getValue();
        }
        return tot;
    }
}
