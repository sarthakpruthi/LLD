package manager;

import entity.User;

import java.util.ArrayList;
import java.util.List;

public class UserManager {
    List<User> users;

    public UserManager() {
        this.users = new ArrayList<>();
    }

    public void addUser(User user) {
        this.users.add(user);
    }

    public User getUserById(int id){
        return users.stream().filter(user -> user.getId() == id).findFirst().orElse(null);
    }

    public void addToCart(int userId, int productId,int count){
        User user = getUserById(userId);
        user.getCart().updateProductCount(productId, count);
    }
}
