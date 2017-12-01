

import java.io.File;

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


        String text = "SNEMAKESMYCRY";
        String key = "KEYKEYKEYKEYKE";

        //generate QRcode from String message

        String msgFilePath = "/home/tk/Documents/QR/string_msg.png";
        int size = 124;
        String fileType = "png";
        File msgFile = new File(msgFilePath);
        qrcode.createQRImage(msgFile, text, size, fileType);
        System.out.println("QR code from message is generated");

        //generate QRcode from String key

        String keyFilePath = "/home/tk/Documents/QR/string_key.png";
        File keyFile = new File(keyFilePath);
        qrcode.createQRImage(keyFile, key, size, fileType);
        System.out.println("QR code from key is generated");


        //Overlay_codes 2 QRcodes String

        String overFilePath = "/home/tk/Documents/QR/string_overlay.png";
        File overFile = overlay.Overlay_codes(msgFile, keyFile, overFilePath);

        //Overlaying to decode

        String decodeFilePath = "/home/tk/Documents/QR/string_decode.png";
        File decodeFile = overlay.Overlay_codes(overFile, keyFile, decodeFilePath);

        toHex.doHex(text, key);


    }

}