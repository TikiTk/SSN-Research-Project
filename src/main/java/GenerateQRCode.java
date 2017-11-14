import java.io.*;


import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import net.glxn.qrgen.javase.QRCode;
import net.glxn.qrgen.core.image.ImageType;


public class GenerateQRCode {
    public static void main(String[] args) {
        generateQRimage("QR code test");

    }

    static void generateQRimage(String message) {
        ByteArrayOutputStream byteStreamOfText = QRCode.from(message).withErrorCorrection(ErrorCorrectionLevel.H)
                .to(ImageType.JPG).withSize(250, 250).stream();

        try {
            FileOutputStream imageFile = new FileOutputStream(new File(
                    "C:\\Users\\Tk\\Documents\\GitHub\\QR.JPG"));

            imageFile.write(byteStreamOfText.toByteArray());

            imageFile.flush();
            imageFile.close();


        } catch (IOException e) {
            // Do Logging
        }

    }


}
