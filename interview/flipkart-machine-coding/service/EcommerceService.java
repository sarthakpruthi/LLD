package service;

import entity.User;
import enums.UserLevel;
import repository.UserRepository;

import java.util.HashMap;
import java.util.Map;

public class EcommerceService {

    UserRepository userRepository;

    public EcommerceService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    private final Map<String,Integer> personalizedDiscountMap = new HashMap<>(); // name, count of discount used

    public void purchase(String name, double amount, int pointsToRedeem) {
        User user = userRepository.getUserByName(name);
        if (user == null) {
            System.out.println("User not found.");
            return;
        }

        UserLevel level = user.getLevel();
        double maxRedeemAmount = amount * level.maxRedeemPercent;
        int maxPoints = Math.min(level.maxRedeemPoints, (int) (maxRedeemAmount));

        if (pointsToRedeem > user.points) {
            System.out.println("Purchase Failed. Not enough points to redeem");
            return;
        }

        if (pointsToRedeem > maxPoints) {
            System.out.println("Purchase Failed. Redemption exceeds level cap. Maximum points that you can redeem are : " + maxPoints);
            return;
        }

        double amountAfterRedemption = amount - pointsToRedeem;
        double personalizedDiscount = getPersonalizedDiscount(name, user, amountAfterRedemption);//bonus

        double finalAmount = amountAfterRedemption - personalizedDiscount;
        double pointsEarned = (finalAmount / 100) * level.earnRate;

        user.points = user.points - pointsToRedeem + pointsEarned;
        user.totalSpend += finalAmount;
        user.orderCount++;

        System.out.printf("Purchase successful. Points redeemed: %d. Points added: %.1f. Discount applied: %.2f. Total payable amount: %.2f. Current points: %.1f. Current level: %s\n",
                pointsToRedeem, pointsEarned, personalizedDiscount, finalAmount, user.points, user.getLevel());
    }

    private double getPersonalizedDiscount(String name, User user, double amountAfterRedemption) {
        double discount = 0;

        boolean eligibleForDiscount = false;
        int count = personalizedDiscountMap.getOrDefault(name, 0);
        boolean orderCountCheck = ((int)(user.orderCount/3) - count) > 0;
        boolean totalSpendCheck = ((int)(user.totalSpend/10000) - count) > 0;

        if (orderCountCheck && totalSpendCheck) {
            discount = Math.min(5000, amountAfterRedemption * 0.12);
            eligibleForDiscount = true;
        } else if (totalSpendCheck) {
            discount = Math.min(5000, amountAfterRedemption * 0.10);
            eligibleForDiscount = true;
        } else if (orderCountCheck) {
            discount = Math.min(5000, amountAfterRedemption * 0.05);
            eligibleForDiscount = true;
        }

        if (eligibleForDiscount) {
            personalizedDiscountMap.put(name, count+1);
        }
        return discount;
    }
}
