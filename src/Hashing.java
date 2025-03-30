import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class Hashing{
    public static String saltAndHashPassword(String data) throws NoSuchAlgorithmException {
        byte[] salt = generateSalt();

        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        digest.reset();
        digest.update(salt);
        byte[] hash = digest.digest(data.getBytes());

        String hashHexString = bytesToHexString(hash);
        String saltHexString = bytesToHexString(salt);

        return saltHexString+":"+hashHexString;
    }

    public static String hashSaltedPassword(String saltHexString, String password) throws NoSuchAlgorithmException{
        byte[] saltBytes = hexStringToBytes(saltHexString); // Convert salt back to bytes
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        digest.reset();
        digest.update(saltBytes); // Apply salt bytes first
        byte[] hash = digest.digest(password.getBytes());

        return bytesToHexString(hash);
    }

    private static byte[] generateSalt() {
        byte[] bytes = new byte[20];
        SecureRandom random = new SecureRandom();
        random.nextBytes(bytes);

        return bytes;
    }

    private static String bytesToHexString(byte[] bytes) {
        StringBuilder hexString = new StringBuilder();
        for (byte b : bytes) {
            String hex = Integer.toHexString(0xff & b); // Convert byte to hex
            if (hex.length() == 1) {
                hexString.append('0'); // Ensure two-digit representation
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }

    private static byte[] hexStringToBytes(String hexString) {
        int length = hexString.length();
        byte[] bytes = new byte[length / 2];
        for (int i = 0; i < length; i += 2) {
            bytes[i / 2] = (byte) ((Character.digit(hexString.charAt(i), 16) << 4)
                    + Character.digit(hexString.charAt(i + 1), 16));
        }
        return bytes;
    }
}
