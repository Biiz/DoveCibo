/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DoveCiboPK;

/**
 *
 * @author IO-PC
 */
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import net.glxn.qrgen.QRCode;
import net.glxn.qrgen.image.ImageType;

public class QR_generator {

    Restaurant rest;

    public QR_generator(Restaurant rest) {
        this.rest = rest;
    }

    public String qr_Gen() {
        String qrText = rest.RestDescriptionToText();

        //stringa che il QR mostrerà
        String dataToQR = qrText;

        ByteArrayOutputStream out = QRCode.from(dataToQR).to(ImageType.PNG).stream();
        Base64.Encoder encoder = Base64.getEncoder();
        String qrCode = encoder.encodeToString(out.toByteArray());

        return qrCode;
    }
}
