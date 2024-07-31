import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.io.*;

public class DESEncryption {

    private static final String ENCRYPTION_ALGORITHM = "DES";

    // Encrypts a file using a provided key
    public static void encryptFile(String key, String inputFile, String outputFile) throws Exception 
    {
        // Generate a SecretKey from the provided key string
        SecretKey secretKey = generateSecretKey(key);

        // Initialize a Cipher object for encryption
        Cipher cipher = Cipher.getInstance(ENCRYPTION_ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);

        // Read the input file and write the encrypted data to the output file
        try (InputStream inputStream = new FileInputStream(inputFile);
             OutputStream outputStream = new FileOutputStream(outputFile)) 
        {
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) 
            {
                // Encrypt the current buffer and write the result to the output stream
                byte[] encryptedBytes = cipher.update(buffer, 0, bytesRead);
                outputStream.write(encryptedBytes);
            }
            // Encrypt the final block of data and write it to the output stream
            byte[] encryptedBytes = cipher.doFinal();
            outputStream.write(encryptedBytes);
        }
    }

    // Decrypts a file using a provided key
    public static void decryptFile(String key, String inputFile, String outputFile) throws Exception 
    {
        // Generate a SecretKey from the provided key string
        SecretKey secretKey = generateSecretKey(key);

        // Initialize a Cipher object for decryption
        Cipher cipher = Cipher.getInstance(ENCRYPTION_ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, secretKey);

        // Read the encrypted input file and write the decrypted data to the output file
        try (InputStream inputStream = new FileInputStream(inputFile);
             OutputStream outputStream = new FileOutputStream(outputFile)) 
        {
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) 
            {
                // Decrypt the current buffer and write the result to the output stream
                byte[] decryptedBytes = cipher.update(buffer, 0, bytesRead);
                outputStream.write(decryptedBytes);
            }
            // Decrypt the final block of data and write it to the output stream
            byte[] decryptedBytes = cipher.doFinal();
            outputStream.write(decryptedBytes);
        }
    }

    // Generates a SecretKey from a provided key string
    private static SecretKey generateSecretKey(String key) throws Exception 
    {
        // Convert the key string to a byte array
        byte[] keyBytes = key.getBytes();

        // Initialize a DESKeySpec object with the key byte array
        DESKeySpec desKeySpec = new DESKeySpec(keyBytes);

        // Initialize a SecretKeyFactory object for DES encryption
        SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance(ENCRYPTION_ALGORITHM);

        // Generate a SecretKey object from the DESKeySpec object
        SecretKey secretKey = secretKeyFactory.generateSecret(desKeySpec);

        return secretKey;
    }
}
