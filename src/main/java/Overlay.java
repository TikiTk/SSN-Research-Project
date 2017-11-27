import java.io.File;

import static javax.imageio.ImageIO.read;
import static javax.imageio.ImageIO.write;


public class Overlay {



    public static void main(String[]y) throws Exception {

        long startTime = System.currentTimeMillis();

        //change the path to make it work

        java.awt.image.BufferedImage a = read(new File("/home/sveta_buri/tmsg.png"));
        java.awt.image.BufferedImage b = read(new File("/home/sveta_buri/tkey.png"));
        File c = new File ("/home/sveta_buri/overlay.png");

        System.out.println(a.getWidth());
        System.out.println(a.getHeight());


        //zone1

        for (int i=20; i<44; i++){
            for (int j=52; j<72; j++) {
                a.setRGB(j, i, a.getRGB(j, i) ^ b.getRGB(j, i));
                write(a, "png", c);
            }
        }


        //zone2

        for (int i=52; i<72; i++){
            for (int j=20; j<44; j++) {
                a.setRGB(j, i, a.getRGB(j, i) ^ b.getRGB(j, i));
                write(a, "png", c);
            }
        }

        //zone3

        for (int i=48; i<52; i++){
            for (int j=52; j<72; j++) {
                a.setRGB(j, i, a.getRGB(j, i) ^ b.getRGB(j, i));
                write(a, "png", c);
            }
        }

        //zone4

        for (int i=52; i<72; i++){
            for (int j=48; j<104; j++) {
                a.setRGB(j, i, a.getRGB(j, i) ^ b.getRGB(j, i));
                write(a, "png", c);
            }
        }

        //zone5

        for (int i=72; i<76; i++){
            for (int j=56; j<104; j++) {
                a.setRGB(j, i, a.getRGB(j, i) ^ b.getRGB(j, i));
                write(a, "png", c);
            }
        }

        //zone6

        for (int i=76; i<104; i++){
            for (int j=52; j<104; j++) {
                a.setRGB(j, i, a.getRGB(j, i) ^ b.getRGB(j, i));
                write(a, "png", c);
            }
        }





       // for (int i = 0, j; i < a.getHeight(); i++)
         //   for (j = 0; j < a.getWidth(); j++) {
            //   a.setRGB(j, i, a.getRGB(j, i) ^ read(new File("/home/sveta_buri/text2.png")).getRGB(j, i));
             //   write(a, "png", new File("/home/sveta_buri/overlay3.png"));
          // }

        //     for (int i = 0; i < a.getHeight(); i+=7){
        //        for (int j = 0; j < a.getWidth(); j+=7) {
        //          for (int k = i; k < 7; k++)
        //            for (int n = j; n < 7; n++) {
        //               a.setRGB(n, k, a.getRGB(n, k) ^ read(new File("/home/sveta_buri/text2.png")).getRGB(n++, k));
        //              write(a, "png", new File("/home/sveta_buri/output.png"));
        //         }
        //  }

        // }

        long timeSpent = System.currentTimeMillis() - startTime;
        System.out.println("программа выполнялась " + timeSpent + " миллисекунд");




    }
}
