package extras;

import java.util.Base64;

public class StringXORer {

    public String encode(String s, String key) {
        return base64Encode(xorWithKey(s.getBytes(), key.getBytes()));
    }

    public String decode(String s, String key) {
        return new String(xorWithKey(base64Decode(s), key.getBytes()));
    }

    private static byte[] xorWithKey(byte[] a, byte[] key) {
        byte[] out = new byte[a.length];
        for (int i = 0; i < a.length; i++) {
            out[i] = (byte) (a[i] ^ key[i%key.length]);
        }
        return out;
    }

    private static byte[] base64Decode(String s) {
        return Base64.getDecoder().decode(s);

    }



    private String base64Encode(byte[] bytes) {
        return Base64.getEncoder().encodeToString(bytes);

    }

    public static void main(String[] args){
        String s = "SNEMAKESMECRY";
        String k = "keykeykeykeyk";
        byte[] str = new byte[s.length()];
        str = base64Decode(s);
        System.out.println(str);

        byte[] key = new byte[s.length()];
        key = base64Decode(k);

        //System.out.println(xorWithKey(str, key));
    }
}