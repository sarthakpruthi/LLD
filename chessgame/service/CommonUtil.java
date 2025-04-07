package service;

public class CommonUtil {
    public static boolean isValid(int eRow, int eCol) {
        return eRow >=0 && eRow < 8 && eCol >= 0 && eCol < 8;
    }
}
