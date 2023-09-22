public class CustomHash {

    public static int customHashFunction(String input) {
        int sum = 0;
        int n = 5;
        for (char c : input.toCharArray()) {
            sum += (Math.pow(n, c) % 7);
            sum = (sum * 3 + 44) + (sum % 2);
            n = n + 2;
        }
        return sum;
    }

    public static void main(String[] args) {
        String test = "Hello";
        System.out.println("Hash of " + test + ": " + customHashFunction(test));
    }
}
