import javax.crypto.Cipher;
import java.security.*;

public class RSAImplementation {

    public static void main(String[] args) throws Exception {
        // generate public and private keys
        KeyPair keyPair = buildKeyPair();
        PublicKey pubKey = keyPair.getPublic();
        PrivateKey privateKey = keyPair.getPrivate();

        // encrypt the message
        String message = "This is the secret message to encrypt";


        byte[] encrypted = encrypt(pubKey, "This is a secret message");

        // <<encrypted message>>

        // decrypt the message
        byte[] secret = decrypt(privateKey, encrypted);
        //System.out.println(new String(secret));     // This is a secret message

        //System.out.println(new String(String.valueOf(pubKey)));

    }

    public RSAImplementation()
    {

    }

    public static KeyPair buildKeyPair() throws NoSuchAlgorithmException {
        final int keySize = 2048;
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(keySize);
        return keyPairGenerator.genKeyPair();
    }

    public static byte[] encrypt(PublicKey publicKey, String message) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);

        return cipher.doFinal(message.getBytes());
    }

    public static byte[] decrypt(PrivateKey privateKey, byte[] encrypted) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);

        return cipher.doFinal(encrypted);
    }
    public static String bytesToHex(byte[] array)
    {
        char[] val = new char[2*array.length];
        String hex = "0123456789ABCDEF";
        for (int i = 0; i < array.length; i++)
        {
            int b = array[i] & 0xff;
            val[2*i] = hex.charAt(b >> 4);
            val[2*i + 1] = hex.charAt(b & 15);
        }
        return String.valueOf(val);
    }
}
