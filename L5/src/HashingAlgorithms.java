import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashingAlgorithms {

    public static String sha1HashFunction(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            byte[] result = md.digest(input.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : result) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public static int basicHashFunction(String input) {
        int sum = 0;
        for (char c : input.toCharArray()) {
            sum += c;
        }
        return sum % 10;
    }
}
