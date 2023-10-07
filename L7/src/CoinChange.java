import java.util.HashMap;

public class CoinChange {
    public static HashMap<Integer, Integer> hm = new HashMap<Integer, Integer>();
    public static int[] denominations = {1, 5, 10, 25};

    public static int minChangeMemo(int amount) {
        if (hm.containsKey(amount)) {
            return hm.get(amount);
        }
        if (amount == 0) {
            return 0;
        }
        int minCoins = Integer.MAX_VALUE;
        for (int coin : denominations) {
            if (coin <= amount)
                minCoins = Math.min(minCoins, add(minChangeMemo(amount - coin), 1));
        }
        hm.put(amount, minCoins);
        return minCoins;
    }
    public static int minChangeReg(int amount) {
        if (amount == 0) {
            return 0;
        }
        int minCoins = Integer.MAX_VALUE;
        for (int coin : denominations) {
            if (coin <= amount)
                minCoins = Math.min(minCoins, add(minChangeReg(amount - coin), 1));
        }
        return minCoins;
    }
    public static int add(int a, int b) {
        return a == Integer.MAX_VALUE || b == Integer.MAX_VALUE ? Integer.MAX_VALUE : a + b;
    }

    public static void main(String[] args) {
        for (int i = 23; i < 70; i += 23) {
            long start = System.currentTimeMillis();
            System.out.println("The minimum number of coins needed to make " + i + " cents is: " + minChangeMemo(i));
            long end = System.currentTimeMillis();
            System.out.println("Time taken WITH memoization: " + (end - start) + "ms");
        }
        System.out.println("--------------------------------------------------");
        for (int i = 23; i < 70; i += 23) {
            long start = System.currentTimeMillis();
            System.out.println("The minimum number of coins needed to make " + i + " cents is: " + minChangeReg(i));
            long end = System.currentTimeMillis();
            System.out.println("Time taken WITHOUT memoization: " + (end - start) + "ms");
        }

        System.out.println("--------------------------------------------------");

        long start = System.currentTimeMillis();
        System.out.println("The minimum number of coins needed to make 5000 cents is: " + minChangeMemo(5000));
        long end = System.currentTimeMillis();
        System.out.println("Time taken WITH memoization: " + (end - start) + "ms");
    }
}
