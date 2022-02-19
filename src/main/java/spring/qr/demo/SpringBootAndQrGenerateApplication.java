package spring.qr.demo;

import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.QRCodeWriter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Path;

public class SpringBootAndQrGenerateApplication {

	private static String QRCODE_PATH = "C:\\Users\\Nipun\\Desktop\\spring-boot-qr-generator\\QR-Server\\";

	public String writeQRCode(PaytmRequestBody paytmRequestBody) throws Exception {
		String qrcode = QRCODE_PATH+paytmRequestBody.getUserName()+"-QRCODE.png";
		QRCodeWriter writer = new QRCodeWriter();
		BitMatrix bitMatrix = writer.encode(
				paytmRequestBody.getUserName()+ "\n" +paytmRequestBody.getMobileNo()+ "\n" +
				paytmRequestBody.getAccountType()+ "\n" +paytmRequestBody.getAccountNo(), BarcodeFormat.QR_CODE, 350, 350);
		Path path = FileSystems.getDefault().getPath(qrcode);
		MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);
		return "QR Code is generated successfully";
	}

	public String readQRCode(String qrcodeImage) throws Exception {
		BufferedImage bufferedImage = ImageIO.read(new File(qrcodeImage));
		LuminanceSource luminanceSource = new BufferedImageLuminanceSource(bufferedImage);
		BinaryBitmap binaryBitmap = new BinaryBitmap(new HybridBinarizer(luminanceSource));
		Result result = new MultiFormatReader().decode(binaryBitmap);

		return result.getText();
	}

	public static void main(String[] args) throws Exception {
		SpringBootAndQrGenerateApplication application = new SpringBootAndQrGenerateApplication();
		String qrCode = application.writeQRCode(
				new PaytmRequestBody(
						"Nipun",
						"0771234567",
						"Saving",
						"000011112222"));
		System.out.println(qrCode);
		System.out.println("QR Information.......");
		System.out.println(application.readQRCode(QRCODE_PATH+"Nipun-QRCODE.png"));
	}

}
