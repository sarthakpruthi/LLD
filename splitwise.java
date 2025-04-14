package MachineCoding;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

    static Map<String, User> userIdUserMap = new HashMap<>();


    public static void createExpense(String userPaid, List<Split> splits, String expenseName, int totAmount){
        User paidUser = userIdUserMap.get(userPaid);
        UserBalance paidUserBalance = paidUser.userBalance;

        paidUserBalance.totPay += totAmount;
        for(Split split : splits){
            String payeeUserId = split.userId;
            User payeeUser = userIdUserMap.get(payeeUserId);
            int amountToPay = split.amountOwe;

            if(payeeUserId.equals(userPaid)){
                paidUserBalance.totExpense += amountToPay;
            }
            else{
                //apne user balance map ko update
                paidUserBalance.totGet += amountToPay;
                Map<String, Balance> userIdBalance = paidUserBalance.userIdBalance;
                Balance balance = userIdBalance.getOrDefault(payeeUserId, new Balance());
                balance.amountGet += split.amountOwe;
                userIdBalance.put(payeeUserId, balance);

                //dusre k user balance ko update
                UserBalance payeeUserBalance = payeeUser.userBalance;
                payeeUserBalance.totOwe += split.amountOwe;
                payeeUserBalance.totExpense += amountToPay;
                Map<String, Balance> payeeUserBalanceMap = payeeUserBalance.userIdBalance;
                Balance oweBalance = payeeUserBalanceMap.getOrDefault(userPaid, new Balance());
                oweBalance.amountOwe += amountToPay;
                payeeUserBalanceMap.put(userPaid, oweBalance);
            }
        }
    }

    public static void settleExpense(String oweUserId, int amount, String payerUserId){
        User oweUser = userIdUserMap.get(oweUserId);
        User payerUser = userIdUserMap.get(payerUserId);
        UserBalance oweBalance = oweUser.userBalance;
        oweBalance.totOwe -= amount;
        Map<String, Balance> oweBalanceMap = oweBalance.userIdBalance;
        oweBalanceMap.get(payerUserId).amountOwe -= amount;
        UserBalance payerBalance = payerUser.userBalance;
        payerBalance.totGet -= amount;
        Map<String, Balance> payerBalanceMap = payerBalance.userIdBalance;
        payerBalanceMap.get(oweUserId).amountGet -= amount;
    }

    public static void getUserBalance(String userPaid){
        UserBalance userBalance = userIdUserMap.get(userPaid).userBalance;
        System.out.println("userId: " + userPaid + " totalExpense: " + userBalance.totExpense + " totalOwe: " + userBalance.totOwe + " totalGet: " + userBalance.totGet + " total paid: " + userBalance.totPay);
        for(var it:userBalance.userIdBalance.entrySet()){
            System.out.println("user id " + it.getKey() + " owes: " + it.getValue().amountOwe + " will get: " + it.getValue().amountGet);
        }
    }

    public static void main(String[] args) {
        addUser("u1", "Sarthak");
        addUser("u2", "Ravi");
        addUser("u3", "Aman");

        // u1 paid 300 for dinner, equally split between u1, u2, u3
        List<Split> splits = new ArrayList<>();
        splits.add(new Split("u1", 100));
        splits.add(new Split("u2", 100));
        splits.add(new Split("u3", 100));

        createExpense("u1", splits, "Dinner", 300);

        System.out.println("\n--- Balances after expense ---");
        getUserBalance("u1");
        getUserBalance("u2");
        getUserBalance("u3");

        // u2 settles 100 with u1
        settleExpense("u2", 100, "u1");

        System.out.println("\n--- Balances after settlement ---");
        getUserBalance("u1");
        getUserBalance("u2");
    }

    public static void addUser(String id, String name){
        User user = new User();
        user.id = id;
        user.name = name;
        user.userBalance = new UserBalance();
        user.userBalance.userIdBalance = new HashMap<>();
        userIdUserMap.put(id, user);
    }
}

class Split{
    String userId;
    int amountOwe;
    int amountGet;

    public Split(String u1, int i) {
        this.userId = u1;
        this.amountOwe = i;
    }
}

class User{
    String id;
    String name;
    UserBalance userBalance;
}

class UserBalance{
    int totExpense;
    int totPay;
    int totOwe;
    int totGet;
    Map<String, Balance> userIdBalance;
}

class Balance{
    int amountOwe;
    int amountGet;
}

