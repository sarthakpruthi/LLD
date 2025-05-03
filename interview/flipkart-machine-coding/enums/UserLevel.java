package enums;

public enum UserLevel {

    BRONZE(0, 499, 10, 0.05, 200),
    SILVER(500, 999, 12.5, 0.10, 500),
    GOLD(1000, Integer.MAX_VALUE, 15, 0.15, 1000);

    int minPoints, maxPoints;
    public double earnRate;
    public double maxRedeemPercent;
    public int maxRedeemPoints;

    UserLevel(int minPoints, int maxPoints, double earnRate, double maxRedeemPercent, int maxRedeemPoints) {
        this.minPoints = minPoints;
        this.maxPoints = maxPoints;
        this.earnRate = earnRate;
        this.maxRedeemPercent = maxRedeemPercent;
        this.maxRedeemPoints = maxRedeemPoints;
    }

    public static UserLevel getLevel(double points) {
        for (UserLevel level : UserLevel.values()) {
            if (points >= level.minPoints && points <= level.maxPoints) return level;
        }
        return BRONZE;
    }
}
