package AsymmetricEncryption;

import javax.crypto.Cipher;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.util.Base64;

public class Asymmetric {
    private static final String RSA = "RSA";

    // Generating public and private keys using RSA algorithm.
    public KeyPair generateRSAKeyPair() throws Exception {
        SecureRandom secureRandom
                = new SecureRandom();

        KeyPairGenerator keyPairGenerator
                = KeyPairGenerator.getInstance(RSA);

        keyPairGenerator.initialize(
                2048, secureRandom);

        return keyPairGenerator
                .generateKeyPair();
    }

    // Encryption function which converts the plainText into a cipherText using private Key.
    public String RSAEncrypt(
            String plainText,
            PrivateKey privateKey
    ) throws Exception {
        Cipher cipher = Cipher.getInstance(RSA);

        cipher.init(
                Cipher.ENCRYPT_MODE,
                privateKey
        );

        byte[] cipherText =  cipher.doFinal(
                plainText.getBytes(StandardCharsets.UTF_8)
        );

        return Base64.getEncoder().encodeToString(cipherText);
    }

    // Decryption function which converts the ciphertext back to the original plaintext.
    public String RSADecrypt(
            String cipherText,
            PublicKey publicKey
    ) throws Exception {
        Cipher cipher = Cipher.getInstance(RSA);

        cipher.init(
                Cipher.DECRYPT_MODE,
                publicKey
        );

        byte[] result = cipher.doFinal(
                Base64.getDecoder().decode(cipherText)
        );

        return new String(result);
    }
}
