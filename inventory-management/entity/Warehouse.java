package entity;

import java.util.ArrayList;
import java.util.List;

public class Warehouse {
    int id;
    List<Product> products;
    String address;


    public Warehouse(int id, String address) {
        this.id = id;
        this.products = new ArrayList<>();
        this.address = address;
    }

    public Product getProductById(int id){
        return products.stream().filter(product -> product.getId() == id).findFirst().orElse(null);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
