

import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import extras.HexStringConverter;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;

public class QRCodeReader {

    public static String decodeQRCode(File qrCodeimage) throws Exception {
        BufferedImage bufferedImage = ImageIO.read(qrCodeimage);
        LuminanceSource source = new BufferedImageLuminanceSource(bufferedImage);
        BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
        RSAImplementation rsaImplementation = new RSAImplementation();
        HexStringConverter toHex = new HexStringConverter();
        try {
            Result result = new MultiFormatReader().decode(bitmap);

            String cipherText =  result.getText();
            String encrpytedKey = cipherText.substring(cipherText.length()-512,cipherText.length());
            String encrpyedMsg = cipherText.substring(0,cipherText.length()-512);
            byte[] encrypted = new BigInteger(encrpytedKey,16).toByteArray();

            String keyFile =  "output.key";
            Path path = Paths.get(keyFile);
            byte[] bytes = Files.readAllBytes(path);


            PKCS8EncodedKeySpec ks = new PKCS8EncodedKeySpec(bytes);
            KeyFactory kf = KeyFactory.getInstance("RSA");
            PrivateKey pvt = kf.generatePrivate(ks);

            byte[] decrypted = rsaImplementation.decrypt(pvt,encrypted);

            String initial_key = toHex.convertBytesToHex(decrypted);



            return toHex.hexToString(toHex.xorHex(encrpyedMsg,initial_key));

        } catch (NotFoundException e) {
            System.out.println("There is no QR code in the image");
            return null;
        }
    }

    public static void main(String[] args) throws Exception {
        try {
            File file = new File("/home/tk/Documents/QR/hex_encoded.png");
            String decodedText = decodeQRCode(file);
            if (decodedText == null) {
                System.out.println("No QR Code found in the image");
            } else {
                System.out.println("Decoded text = " + decodedText);
            }
        } catch (IOException e) {
            System.out.println("Could not decode QR Code, IOException :: " + e.getMessage());
        }
    }
}