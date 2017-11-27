public class toBin {

    public static String AsciiToBinary(String asciiString){

        byte[] bytes = asciiString.getBytes();
        StringBuilder binary = new StringBuilder();
        for (byte b : bytes)
        {
            int val = b;
            for (int i = 0; i < 8; i++)
            {
                binary.append((val & 128) == 0 ? 0 : 1);
                val <<= 1;
            }
            // binary.append(' ');
        }
        return binary.toString();
    }

    public static String xorBin (byte[] s, byte[] k){
        byte[] out = new byte[s.length];
        for (int i=0; i<s.length; i++){
            out[i]= (byte) (s[i] ^ k[i]);
        }

        return out.toString();
    }

     public static String genString (String s, String k){

        String lseq = AsciiToBinary(s);
        String tseq = AsciiToBinary(k);

        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < lseq.length(); i++)
            sb.append((lseq.charAt(i) ^ tseq.charAt(i)));

        String result = sb.toString();

        String s2 = "";
        char nextChar;

        for(int i = 0; i <= result.length()-8; i += 8) //this is a little tricky.  we want [0, 7], [9, 16], etc (increment index by 9 if bytes are space-delimited)
        {
            nextChar = (char)Integer.parseInt(result.substring(i, i+7), 2);
            s2 += nextChar;
        }

        return (s2);


    }


}

