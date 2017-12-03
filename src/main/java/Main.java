

import com.google.zxing.EncodeHintType;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import extras.HexStringConverter;

import java.io.File;
import java.io.FileOutputStream;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Formatter;
import java.util.HashMap;
import java.util.Map;


/**
 *
 * @author EtaYuy88
 */
public class Main {

    private static final char[] HEX_CHARS = "0123456789abcdef".toCharArray();

    public static void main(String[] args) throws Exception {

        QRCode qrcode = new QRCode();
        Overlay overlay = new Overlay();
        HexStringConverter toHex = new HexStringConverter();
        QRCodeRW readQR = new QRCodeRW();
        String text = "Tomorrow (Monday December 4, 2017), we will have SSN project presentations. The presentations will start at 10:00 in room # 307.Each group will have 20 minutes for presentation (15+5).Every member of the group should present.";

        RandomStringSecture randomStringSecture = new RandomStringSecture(text.length());
        String key = randomStringSecture.nextString();
        String hexEncode = toHex.encodeAndXorHex(text,key);
        RSAImplementation rsaImplementation = new RSAImplementation();
        KeyPair keyPair = rsaImplementation.buildKeyPair();
        PublicKey pubKey = keyPair.getPublic();
        PrivateKey privateKey = keyPair.getPrivate();
        String outfile = "output";
        FileOutputStream out = new FileOutputStream(outfile + ".key");
        out.write(privateKey.getEncoded());
        out.close();

        byte[] encrypted = rsaImplementation.encrypt(pubKey,key);
        String word = convert(encrypted);

        byte[] decrypted = rsaImplementation.decrypt(privateKey,encrypted);

        word = convert(decrypted);

        System.out.println(toHex.hexToString(word));


       // System.out.println(hexToAscii(word));


        int size = 300;
        String fileType = "png";




//        //1st approach
//        //generate QRcode from String message
//
//        String msgFilePath = "/home/tk/Documents/QR/string_msg.png";
//
//
//        File msgFile = new File(msgFilePath);
//        qrcode.createQRImage(msgFile, text, size, fileType);
//        System.out.println("QR code from message is generated");
//
//        //generate QRcode from String key
//
//        String keyFilePath = "/home/tk/Documents/QR/string_key.png";
//        File keyFile = new File(keyFilePath);
//        qrcode.createQRImage(keyFile, key, size, fileType);
//        System.out.println("QR code from key is generated");
//
//
//        //Overlay 2 QRcodes String
//
//        String overFilePath = "/home/tk/Documents/QR/string_overlay.png";
//        File overFile = overlay.Overlay_codes(msgFile, keyFile, overFilePath);
//
//        //Overlaying to decode
//
//        String decodeFilePath = "/home/tk/Documents/QR/string_decode.png";
//        File decodeFile = overlay.Overlay_codes(overFile, keyFile, decodeFilePath);




        // 2nd approach
        //xor key and msg in hex

        //String hexEncode = toHex.encodeAndXorHex(text, key);
        //System.out.println("Result from main "+ hexEncode);

        //generate QR code with xored hex
        String hexFilePath = "/home/tk/Documents/QR/hex_encoded.png";
        File hexFile = new File(hexFilePath);
        qrcode.createQRImage(hexFile, hexEncode+rsaImplementation.bytesToHex(encrypted), size, fileType);
        System.out.println("QR code from hex msg is generated");


        //read content of hexQR
//        String charset = "UTF-8";
//        Map hintMap = new HashMap();
//        hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
//        String readHexQR = readQR.readQRCode(hexFilePath,charset, hintMap);
//        System.out.println("read result from QR code " + readHexQR);
//
//        //xor hex and key again and convert it to ascii
//        String decode = toHex.xorAndDecodeToString(readHexQR,hexEncode);
//        System.out.println("Xor result with the key and convert to ASCII: " + decode);


    }


    public static String convert(byte[] bytes){
        Formatter formatter = new Formatter();
        for (byte b : bytes){
            formatter.format("%02x", b);
        }
        String hex = formatter.toString();
        return hex;
    }



    public static String toHexString(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        int v;
        for (int j = 0; j < bytes.length; j++) {
            v = bytes[j] & 0xFF;
            hexChars[j * 2] = HEX_CHARS[v >>> 4];
            hexChars[j * 2 + 1] = HEX_CHARS[v & 0x0F];
        }
        return new String(hexChars);
    }

    private static String hexToAscii(String hexStr) {
        StringBuilder output = new StringBuilder("");

        for (int i = 0; i < hexStr.length(); i += 2) {
            String str = hexStr.substring(i, i + 2);
            output.append((char) Integer.parseInt(str, 16));
        }

        return output.toString();
    }

}