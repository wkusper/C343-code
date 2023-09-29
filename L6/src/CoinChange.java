import java.util.Arrays;

public class CoinChange {
    public static int[] denominations = new int[]{1, 5, 10, 25};

    public static int minChange(int amount) {
        int[] minCoins = new int[amount + 1];
        Arrays.fill(minCoins, Integer.MAX_VALUE);
        minCoins[0] = 0;
        for (int i = 1; i <= amount; i++) {
            for (int coin : denominations) {
                if (coin <= i) {
                    int sub_res = minCoins[i - coin];
                    if (sub_res != Integer.MAX_VALUE && sub_res + 1 < minCoins[i])
                        minCoins[i] = sub_res + 1;
                }
            }
        }
        return minCoins[amount];
    }

    /*
    This is a helper function that you may find useful for
    adding numbers while avoiding the MAX_VALUE overflow problem
     */
    public static int add(int x, int y) {
        if (x == Integer.MAX_VALUE || y == Integer.MAX_VALUE)
            return Integer.MAX_VALUE;
        return x + y;
    }


    public static void main(String[] args) {
        for (int i = 0; i <= 25; i++) {
            System.out.println("The minimum number of coins to make change for " + i + " is: " + minChange(i));
        }
    }
}