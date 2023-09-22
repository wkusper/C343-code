import java.util.HashMap;

public class TestCustomHash {

    public static String[] getTestCases() {
        // TODO: Add or modify test cases
        return new String[]{
                "Hello", "World"
        };
    }

    public static void testHashFunction() {
        HashMap<Integer, String> map = new HashMap<>(); // Maps hash values to strings

        for (String testCase : getTestCases()) {
            int hashValue = CustomHash.customHashFunction(testCase);

            // Check if this hash value is already in the map
            if (map.containsKey(hashValue)) {
                System.out.println("Collision detected for: " + testCase +
                        " and " + map.get(hashValue));
            } else {
                map.put(hashValue, testCase);
            }
        }
    }

    public static void main(String[] args) {
        testHashFunction();
    }
}
