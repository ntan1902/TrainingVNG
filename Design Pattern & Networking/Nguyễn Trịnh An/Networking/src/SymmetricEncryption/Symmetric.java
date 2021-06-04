package SymmetricEncryption;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Base64;

public class Symmetric {

    private static final String AES = "AES";

    // We are using a Block cipher(CBC mode).
    private static final String AES_CIPHER_ALGORITHM = "AES/CBC/PKCS5PADDING";

    // Function to create a secret key.
    public SecretKey generateAESKey() throws Exception {
        SecureRandom securerandom = new SecureRandom();
        KeyGenerator keygenerator = KeyGenerator.getInstance(AES);

        keygenerator.init(256, securerandom);
        return keygenerator.generateKey();
    }

    // Function to initialize a vector with an arbitrary value.
    public byte[] createInitializationVector() {

        // Used with encryption
        byte[] initializationVector = new byte[16];
        SecureRandom secureRandom = new SecureRandom();
        secureRandom.nextBytes(initializationVector);
        return initializationVector;
    }

    // This function takes plaintext, the key with an initialization vector to convert plainText into CipherText.
    public String AESEncrypt(
            String plainText,
            SecretKey secretKey,
            byte[] initializationVector
    ) throws Exception {
        Cipher cipher = Cipher.getInstance(
                AES_CIPHER_ALGORITHM
        );

        IvParameterSpec ivParameterSpec = new IvParameterSpec(
                initializationVector
        );

        cipher.init(
                Cipher.ENCRYPT_MODE,
                secretKey,
                ivParameterSpec
        );

        byte[] cipherText = cipher.doFinal(
                plainText.getBytes(
                        StandardCharsets.UTF_8
                )
        );

        return Base64.getEncoder().encodeToString(cipherText);
    }

    // This function performs the reverse operation of the AESEncryption function.
    // It converts ciphertext to the plaintext using the key.
    public String AESDecrypt(
            String cipherText,
            SecretKey secretKey,
            byte[] initializationVector
    ) throws Exception {
        Cipher cipher = Cipher.getInstance(
                AES_CIPHER_ALGORITHM
        );

        IvParameterSpec ivParameterSpec = new IvParameterSpec(
                initializationVector
        );

        cipher.init(
                Cipher.DECRYPT_MODE,
                secretKey,
                ivParameterSpec
        );

        byte[] result = cipher.doFinal(
                Base64.getDecoder().decode(cipherText)
        );

        return new String(result);
    }

}
