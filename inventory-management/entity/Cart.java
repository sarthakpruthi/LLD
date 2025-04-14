package entity;

import java.util.HashMap;
import java.util.Map;

public class Cart {
    Map<Integer,Integer> productIdCount;

    public Cart() {
        this.productIdCount = new HashMap<>();
    }

    public void updateProductCount(int productId, int count){
        productIdCount.compute(productId, (key,val) -> {
            if(val == null) return count;
            return val + count;
        });
    }

    public void resetCart(){
        productIdCount = new HashMap<>();
    }

    public Map<Integer, Integer> getProductIdCount() {
        return productIdCount;
    }
}
