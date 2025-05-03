package service;

import entity.User;
import repository.UserRepository;

public class UserService {

    UserRepository userRepository;
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public void addUser(String name) {
        boolean isUserAdded = userRepository.addUser(name);
        if (!isUserAdded) {
            System.out.println("User already exists.");
            return;
        }
        System.out.println("User " + name + " added successfully.");
    }

    public void getUserStats(String name) {
        User user = userRepository.getUserByName(name);
        if (user == null) {
            System.out.println("User not found.");
            return;
        }
        System.out.printf("%s has %.1f points. Current level: %s\n", name, user.points, user.getLevel());
    }

}
