public class RodCutting {
  private int[] prices;
  private Integer[] memo;

  public RodCutting(int[] prices) {
      this.prices = prices;
      this.memo = new Integer[prices.length + 1];
  }

  public int maxRevenue(int length) {
      if (memo[length] != null) {
          return memo[length];
      }
      if (length == 0) {
          return 0;
      }
      int maxRevenue = Integer.MIN_VALUE;
      for (int i = 1; i <= length; i++) {
          maxRevenue = Math.max(maxRevenue, prices[i - 1] + maxRevenue(length - i));
      }
      memo[length] = maxRevenue;
      return maxRevenue;
  }

  public static void main(String[] args) {
      int[] prices = {1, 5, 8, 9};
      RodCutting cutter = new RodCutting(prices);
      System.out.println(cutter.maxRevenue(4)); // Expected output: 10
  }
}
