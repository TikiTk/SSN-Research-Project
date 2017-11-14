import java.io.*;


import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import net.glxn.qrgen.javase.QRCode;
import net.glxn.qrgen.core.image.ImageType;


public class GenerateQRCode {
    public static void main(String[] args) {
        String path = "path to where file should be saved";
        generateQRimage("QR code test", path);

    }

    static void generateQRimage(String message, String path) {
        ByteArrayOutputStream byteStreamOfText = QRCode.from(message).withErrorCorrection(ErrorCorrectionLevel.H)
                .to(ImageType.JPG).withSize(250, 250).stream();

        try {
            FileOutputStream imageFile = new FileOutputStream(new File(
                    path));

            imageFile.write(byteStreamOfText.toByteArray());

            imageFile.flush();
            imageFile.close();


        } catch (IOException e) {
            // Do Logging
        }

    }


}
