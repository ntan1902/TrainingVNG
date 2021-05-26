package AsymmetricEncryption;

import java.security.KeyPair;

public class Main {
    public static void main(String[] args) throws Exception {
        Asymmetric asymmetric = new Asymmetric();

        String plainText = "This is a message";

        // Generate public & private keys.
        KeyPair keypair = asymmetric.generateRSAKeyPair();

        // Encrypt message
        String cipherText = asymmetric.RSAEncrypt(
                plainText,
                keypair.getPrivate()
        );

        // Decrypt message
        String decryptText = asymmetric.RSADecrypt(
                cipherText,
                keypair.getPublic()
        );

        System.out.println(decryptText);
    }

}
