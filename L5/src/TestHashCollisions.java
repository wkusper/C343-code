import java.util.HashMap;

public class TestHashCollisions {

    public static String[] getTestCases() {
        // TODO: Add or modify test cases.
        return new String[]{
                "Hello", "Hash", "AB", "olleH", "hash", "BA", "oHlel", "hhsa"
        };
    }

    public static void testCollisions() {
        HashMap<String, String> sha1Map = new HashMap<>();
        HashMap<Integer, String> basicMap = new HashMap<>();

        for (String testCase : getTestCases()) {
            String sha1Hash = HashingAlgorithms.sha1HashFunction(testCase);
            int basicHash = HashingAlgorithms.basicHashFunction(testCase);

            if (sha1Map.containsKey(sha1Hash)) {
                System.out.println("SHA-1 collision detected for: " + testCase +
                        " and " + sha1Map.get(sha1Hash));
            } else {
                sha1Map.put(sha1Hash, testCase);
            }

            if (basicMap.containsKey(basicHash)) {
                System.out.println("Basic Hash collision detected for: " + testCase +
                        " and " + basicMap.get(basicHash));
            } else {
                basicMap.put(basicHash, testCase);
            }
        }
    }

    public static void main(String[] args) {
        testCollisions();
    }
}
