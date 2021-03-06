package extras;

import java.io.UnsupportedEncodingException;
import java.util.Formatter;
import java.util.logging.Logger;

/**
 *
 * @author EtaYuy88
 */
public class HexStringConverter
{
    private static final char[] HEX_CHARS = "0123456789abcdef".toCharArray();
    private static HexStringConverter hexStringConverter = null;

    public HexStringConverter()
    {}

    public static HexStringConverter getHexStringConverterInstance()
    {
        if (hexStringConverter==null) hexStringConverter = new HexStringConverter();
        return hexStringConverter;
    }

    public static String stringToHex(String input) throws UnsupportedEncodingException
    {
        if (input == null) throw new NullPointerException();
        return asHex(input.getBytes());
    }

    public static String hexToString(String txtInHex)
    {

        byte [] txtInByte = new byte [txtInHex.length() / 2];
        int j = 0;
        for (int i = 0; i < txtInHex.length(); i += 2)
        {
            txtInByte[j++] = Byte.parseByte(txtInHex.substring(i, i + 2), 16);
        }
        return new String(txtInByte);
    }


    public String ConvertToStringByHalves(String txtInHex){
        String firstHalf  = txtInHex.substring(0, txtInHex.length()/2);
        String secondHalf = txtInHex.substring(txtInHex.length()/2,txtInHex.length());

        String totalString = hexToString(firstHalf);
        totalString+= hexToString(secondHalf);

        return totalString;
    }
    private static String asHex(byte[] buf)
    {
        char[] chars = new char[2 * buf.length];
        for (int i = 0; i < buf.length; ++i)
        {
            chars[2 * i] = HEX_CHARS[(buf[i] & 0xF0) >>> 4];
            chars[2 * i + 1] = HEX_CHARS[buf[i] & 0x0F];
        }
        return new String(chars);
    }


    public static String xorHex(String a, String b) {
        // TODO: Validation
        char[] chars = new char[a.length()];
        for (int i = 0; i < chars.length; i++) {
            chars[i] = toHex(fromHex(a.charAt(i)) ^ fromHex(b.charAt(i)));
        }
        return new String(chars);
    }

    private static int fromHex(char c) {
        if (c >= '0' && c <= '9') {
            return c - '0';
        }
        if (c >= 'A' && c <= 'F') {
            return c - 'A' + 10;
        }
        if (c >= 'a' && c <= 'f') {
            return c - 'a' + 10;
        }
        throw new IllegalArgumentException();
    }

    private static char toHex(int nybble) {
        if (nybble < 0 || nybble > 15) {
            throw new IllegalArgumentException();
        }
        return "0123456789ABCDEF".charAt(nybble);
    }

    public static void doHex(String text, String key){
        try
        {
            String textInHex = HexStringConverter.getHexStringConverterInstance().stringToHex(text);
            System.out.println("Your msg in HEX : " + text);

            String keyInHex = HexStringConverter.getHexStringConverterInstance().stringToHex(key);

            String output = xorHex(textInHex, keyInHex);
            System.out.println("Result of xoring: " + output);


            System.out.println("Reconvert to String : " + HexStringConverter.getHexStringConverterInstance().hexToString(textInHex));

            System.out.println("Output in String : " + HexStringConverter.getHexStringConverterInstance().hexToString(output));

            String decode = xorHex(output, keyInHex);
            System.out.println("Decoded result: " + output);
            System.out.println("Decoded in String : " + HexStringConverter.getHexStringConverterInstance().hexToString(decode));


        }
        catch (UnsupportedEncodingException ex)
        {
            Logger.getLogger(ex.getMessage());
        }
    }

    public static String encodeAndXorHex(String text, String key){



        try
        {

            return xorHex(stringToHex(text), stringToHex(key));


        }
        catch (UnsupportedEncodingException ex)
        {
            return ex.getMessage();

        }


    }
    public static String xorAndDecodeToString (String hex, String key) throws UnsupportedEncodingException {

        String decode="";

        try {
            String keyInHex = stringToHex(key);

            decode  = xorHex(hex, keyInHex);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }


        return hexToString(decode);
    }
    public static String convertBytesToHex(byte[] bytes){
        Formatter formatter = new Formatter();
        for (byte b : bytes){
            formatter.format("%02x", b);
        }
        String hex = formatter.toString();
        return hex;
    }

}