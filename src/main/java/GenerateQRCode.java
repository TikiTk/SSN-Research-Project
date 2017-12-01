import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;
import java.io.*;


import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import net.glxn.qrgen.javase.QRCode;
import net.glxn.qrgen.core.image.ImageType;

import javax.imageio.ImageIO;


public class GenerateQRCode {
    public static void main(String[] args) throws Exception {
        String path = "/home/tk/Documents/QR/";
        generateQRimage("This is a test", path+"QR.png");
        generateQRimage("This our key",path+"QR_Key.png");

        File msg = new File(path+"QR.png");
        File key = new File(path+"QR_Key.png");
        Overlay overlay = new Overlay();
        //File overFile = Overlay_codes.Overlay_codes(msg,key,path+"test.png");
        File overFile = overlay.Overlay_codes(msg, key, "/home/tk/Documents/QR/result_QR.png");
        System.out.println("Complete");

    }

    static void generateQRimage(String message, String path) {
        ByteArrayOutputStream byteStreamOfText = QRCode.from(message).withErrorCorrection(ErrorCorrectionLevel.H)
                .to(ImageType.PNG).withSize(200, 200).stream();

        try {
            FileOutputStream imageFile = new FileOutputStream(new File(path));

            imageFile.write(byteStreamOfText.toByteArray());

            imageFile.flush();
            imageFile.close();


        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    static void overlayImages(String MessageImage, String KeyImage,String path) throws IOException {
        BufferedImage image = ImageIO.read(new File(path,MessageImage));
        BufferedImage overlay = ImageIO.read(new File(path,KeyImage));

        WritableRaster imageRaster = image.getRaster();
        DataBufferByte dataBufferByte = (DataBufferByte) imageRaster.getDataBuffer();

        WritableRaster overlWritableRaster = overlay.getRaster();
        DataBufferByte overDataBufferByte = (DataBufferByte) overlWritableRaster.getDataBuffer();

        System.out.println(overDataBufferByte.getData()[0]);

        System.out.println(dataBufferByte.getData().length);
        int width = Math.max(image.getWidth(), overlay.getWidth());
        int height = Math.max(image.getHeight(), overlay.getHeight());

        BufferedImage combined = new BufferedImage(width,height, BufferedImage.TYPE_INT_RGB);

        Graphics2D graphics = combined.createGraphics();
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        graphics.drawImage(image,0,0,null);
        graphics.drawImage(overlay,0,0,null);
        graphics.dispose();


        ImageIO.write(combined, "PNG", new File(path,"combined.png"));


    }



}
