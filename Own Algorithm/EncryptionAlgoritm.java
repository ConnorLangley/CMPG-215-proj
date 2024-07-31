import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.nio.file.Files;
import java.security.*;
import java.util.Base64;

public class EncryptionAlgoritm
{
    public static void main(String[] args) throws Exception 
	{
        // Password to be used for encryption key
        String password = "password";
        
        // File to be encrypted
        File file = new File("C:/Users/Connor/OneDrive/Documents/CMPG215/EncryptionProject (2)/EncryptionProject/EncryptionProject/Own Algorithm/test.zip");

        // Get SHA-256 hash of the password. This hash will be used as an encryption key.
        byte[] key = getSHA(password);

        // Create a new SecretKeySpec for the key. This key specification will be used to create an AES key.
        SecretKeySpec secretKey = new SecretKeySpec(key, "AES");

        // Get an instance of the Cipher for AES encryption
        Cipher cipher = Cipher.getInstance("AES");

        // Initialize the cipher for encryption with the secret key
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);

        // Encrypt the file
        encryptFile(cipher, file);
    }

    public static void encryptFile(Cipher cipher, File file) throws IOException 
	{
		try{
        // Read all bytes from the file to be encrypted
        byte[] input = Files.readAllBytes(file.toPath());

        // Encrypt the bytes
        byte[] output = cipher.doFinal(input);

        // Write the encrypted bytes to a new file
        FileOutputStream outputStream = new FileOutputStream(file.getPath() + ".enc");
        outputStream.write(output);

        // Delete the original, unencrypted file
        if(file.delete()) 
		{
            System.out.println("The original file is deleted successfully!");
        } else {
            System.out.println("Failed to delete the file.");
        }

        // Close the output stream
        outputStream.close();
		}catch(IllegalBlockSizeException e)
		{
			System.out.println(e.getMessage());
		}
		catch(BadPaddingException e)
		{
			System.out.println(e.getMessage());
		}
		
    }

    public static byte[] getSHA(String input) throws NoSuchAlgorithmException 
	{
        // Get a MessageDigest instance for SHA-256
        MessageDigest md = MessageDigest.getInstance("SHA-256");

        // Compute and return the hash of the input
        return md.digest(input.getBytes());
    }
}
