package repository;

import entity.User;

import java.util.HashMap;
import java.util.Map;

public class UserRepository {

    private final Map<String, User> users = new HashMap<>();

    public User getUserByName(String name) {
        return users.getOrDefault(name, null);
    }
    public boolean addUser(String name) {
        if(getUserByName(name) != null){
            return false;
        }
        User user = new User(name);
        users.put(name, user);
        return true;
    }
}
