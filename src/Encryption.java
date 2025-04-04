import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class Encryption {

    private static final String ALGORITHM = "AES";

    public static String encrypt(String plainText) {
        try {
            String secretKey = System.getenv("AES_KEY");
            if (secretKey == null || secretKey.length() != 16) {
                throw new IllegalArgumentException("AES_KEY must be set in the environment and must be 16 characters long.");
            }

            SecretKeySpec keySpec = new SecretKeySpec(secretKey.getBytes(StandardCharsets.UTF_8), ALGORITHM);
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, keySpec);

            byte[] encryptedBytes = cipher.doFinal(plainText.getBytes(StandardCharsets.UTF_8));
            return Base64.getEncoder().encodeToString(encryptedBytes);

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error occurred during encryption.");
        }
    }

    public static String decrypt(String encryptedText) {
        try {
            String secretKey = System.getenv("AES_KEY");
            if (secretKey == null || secretKey.length() != 16) {
                throw new IllegalArgumentException("AES_KEY must be set in the environment and must be 16 characters long.");
            }

            SecretKeySpec keySpec = new SecretKeySpec(secretKey.getBytes(StandardCharsets.UTF_8), ALGORITHM);
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, keySpec);

            byte[] decodedBytes = Base64.getDecoder().decode(encryptedText);
            byte[] decryptedBytes = cipher.doFinal(decodedBytes);

            return new String(decryptedBytes, StandardCharsets.UTF_8);

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error occurred during decryption.");
        }
    }
}
