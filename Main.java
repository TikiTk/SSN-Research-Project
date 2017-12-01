import com.google.zxing.EncodeHintType;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import java.io.File;
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


        String text = "SNEMAKESMYCRY";
        String key = "KEYKEYKEYKEYKE";

        //1st approach
        //generate QRcode from String message

        String msgFilePath = "/home/sveta_buri/string_msg.png";
        int size = 124;
        String fileType = "png";
        File msgFile = new File(msgFilePath);
        qrcode.createQRImage(msgFile, text, size, fileType);
        System.out.println("QR code from message is generated");

        //generate QRcode from String key

        String keyFilePath = "/home/sveta_buri/string_key.png";
        File keyFile = new File(keyFilePath);
        qrcode.createQRImage(keyFile, key, size, fileType);
        System.out.println("QR code from key is generated");


        //Overlay 2 QRcodes String

        String overFilePath = "/home/sveta_buri/string_overlay.png";
        File overFile = overlay.Overlay(msgFile, keyFile, overFilePath);

        //Overlaying to decode

        String decodeFilePath = "/home/sveta_buri/string_decode.png";
        File decodeFile = overlay.Overlay(overFile, keyFile, decodeFilePath);




        // 2nd approach
        //xor key and msg in hex

        String hexEncode = toHex.encodeAndXorHex(text, key);
        //System.out.println("Result from main "+ hexEncode);

        //generate QR code with xored hex
        String hexFilePath = "/home/sveta_buri/hex_encoded.png";
        File hexFile = new File(hexFilePath);
        qrcode.createQRImage(hexFile, hexEncode, size, fileType);
        System.out.println("QR code from hex msg is generated");

        //read content of hexQR
        String charset = "UTF-8";
        Map hintMap = new HashMap();
        hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
        String readHexQR = readQR.readQRCode(hexFilePath,charset, hintMap);
        System.out.println("read result from QR code " + readHexQR);

        //xor hex and key again and convert it to ascii
        String decode = toHex.xorAndDecodeToString(readHexQR,key);
        System.out.println("Xor result with the key and convert to ASCII: " + decode);


    }

}