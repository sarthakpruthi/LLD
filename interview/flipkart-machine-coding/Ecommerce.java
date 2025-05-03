import repository.UserRepository;
import service.EcommerceService;
import service.UserService;

import java.util.Scanner;

public class Ecommerce {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        UserRepository userRepository = new UserRepository();
        EcommerceService ecommerceService = new EcommerceService(userRepository);
        UserService userService = new UserService(userRepository);

        while (sc.hasNext()) {
            String cmd = sc.next();
            if (cmd.equalsIgnoreCase("onboard")) {
                userService.addUser(sc.next());
            } else if (cmd.equalsIgnoreCase("purchase")) {
                String user = sc.next();
                double amt = sc.nextDouble();
                int redeem = sc.nextInt();
                ecommerceService.purchase(user, amt, redeem);
            } else if (cmd.equalsIgnoreCase("getUserStats")) {
                userService.getUserStats(sc.next());
            } else {
                System.out.println("Invalid command.");
            }
        }
    }
}