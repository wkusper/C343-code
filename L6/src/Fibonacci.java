public class Fibonacci {
    public static int nthFibonacci(int n) {

        int[] fib = new int[n+1]; // Initialize an array to store Fibonacci numbers
        fib[0] = 0;
        fib[1] = 1;

        // Begin iterating from i = 2
        // (since we already know the values for Fib(0) and Fib(1)).
        for (int i = 2; i <= n; i++) {
            fib[i] = fib[i - 1] + fib[i - 2];
        }

        return fib[n];
    }

    public static void main(String[] args) {
        int n = 4;
        // n = 4, "The 4th Fibonacci number is 3"
        // Sequence : 0, 1, 1, 2, 3, 5, 8, 13, 21, 34.....
        int result = nthFibonacci(n);
        System.out.println("The " + n + "th Fibonacci number is " + result);
    }
}