package SymmetricEncryption;

import javax.crypto.SecretKey;

public class Main {
    public static void main(String[] args) throws Exception {
        Symmetric symmetric = new Symmetric();

        String plainText = "This is a message";

        // Generate secret key
        SecretKey symmetricKey = symmetric.generateAESKey();

        // Generate the initialization vector that is required to avoid repetition during the encryption process.
        byte[] initializationVector = symmetric.createInitializationVector();

        // Encrypt message
        String cipherText = symmetric.AESEncrypt(
                plainText,
                symmetricKey,
                initializationVector
        );

        // Decrypt message
        String decryptText = symmetric.AESDecrypt(
                cipherText,
                symmetricKey,
                initializationVector
        );

        System.out.println(decryptText);
    }
}
