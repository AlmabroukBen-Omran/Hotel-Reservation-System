import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class Encryption {

    private static final String ALGORITHM = "AES";

    public static String encrypt(String plainText) {
        String preview = plainText.length() > 5 ? plainText.substring(0, 5) + "..." : plainText;
        try {
            String secretKey = System.getenv("AES_KEY");
            if (secretKey == null || secretKey.length() != 16) {
                String msg = "AES_KEY must be set in the environment and must be 16 characters long.";
                Logging.log("System", "Encryption failed: " + msg);
                throw new IllegalArgumentException(msg);
            }

            SecretKeySpec keySpec = new SecretKeySpec(secretKey.getBytes(StandardCharsets.UTF_8), ALGORITHM);
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, keySpec);

            byte[] encryptedBytes = cipher.doFinal(plainText.getBytes(StandardCharsets.UTF_8));
            String encryptedString = Base64.getEncoder().encodeToString(encryptedBytes);

            return encryptedString;

        } catch (Exception e) {
            Logging.log("System", "Encryption error: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Error occurred during encryption.");
        }
    }

    public static String decrypt(String encryptedText) {
        String preview = encryptedText.length() > 10 ? encryptedText.substring(0, 10) + "..." : encryptedText;
        try {
            String secretKey = System.getenv("AES_KEY");
            if (secretKey == null || secretKey.length() != 16) {
                String msg = "AES_KEY must be set in the environment and must be 16 characters long.";
                Logging.log("System", "Decryption failed: " + msg);
                throw new IllegalArgumentException(msg);
            }

            SecretKeySpec keySpec = new SecretKeySpec(secretKey.getBytes(StandardCharsets.UTF_8), ALGORITHM);
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, keySpec);

            byte[] decodedBytes = Base64.getDecoder().decode(encryptedText);
            byte[] decryptedBytes = cipher.doFinal(decodedBytes);

            String decrypted = new String(decryptedBytes, StandardCharsets.UTF_8);

            return decrypted;

        } catch (Exception e) {
            Logging.log("System", "Decryption error: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Error occurred during decryption.");
        }
    }
}
