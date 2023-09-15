public class Test {

    private static final int TEST_SIZE = 1000000;

    public static void main(String[] args) {

        // Testing doubling strategy
        testStrategy(2.0, "Doubling");
        testStrategy(3.0, "Tripling");
        testStrategy(1.5, "1.5x");
        testStrategy(10.0, "10x");
    }

    private static void testStrategy(double growthFactor, String strategyName) {
        System.out.println("Testing " + strategyName + " Strategy...");

        DynamicArray dynArr = new DynamicArray(growthFactor);

        long startTime = System.nanoTime();

        for (int i = 0; i < TEST_SIZE; i++) {
            dynArr.append(i);

            if (i % 1000 == 999) {
                for (int j = 0; j < 10; j++) {
                    dynArr.pop();
                }
            }
        }

        long endTime = System.nanoTime();
        long operationTime = endTime - startTime;

        System.out.println(strategyName + " Strategy Operation Time: " + operationTime + " ns");
        System.out.println("----------------------------------------------------");
    }
}
