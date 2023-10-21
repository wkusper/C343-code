public class Power {
    public static int power(int base, int powerRaised) {
        if (powerRaised != 0) {
            return (base * power(base, powerRaised - 1));
        }
        else {
            return 1;
        }
    }
    public static void main(String[] args) {
        int base, powerRaised;
        for (int i = 2; i < 5; i++) {
            for (int j = 2; j < 5; j++) {
                base = i;
                powerRaised = j;
                System.out.println(base + "^" + powerRaised + "=" + power(base, powerRaised));
            }
        }
    }
}
