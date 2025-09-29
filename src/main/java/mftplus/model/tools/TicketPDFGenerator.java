package mftplus.model.tools;
import mftplus.model.entity.Ticket;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.common.BitMatrix;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Arrays;


public class TicketPDFGenerator {

    public static void generateTicketPDF(Ticket ticket, String filePath) throws IOException, WriterException {
        PDDocument document = new PDDocument();
        PDPage page = new PDPage();
        document.addPage(page);


        String qrText = "TicketID:" + ticket.getTicketId() +
                "|Event:" + ticket.getEvent().getEventId() +
                "|Seat:" + ticket.getSeat().getSeatNumber();
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(qrText, BarcodeFormat.QR_CODE, 150, 150);

        BufferedImage qrImage = new BufferedImage(150, 150, BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < 150; x++) {
            for (int y = 0; y < 150; y++) {
                qrImage.setRGB(x, y, bitMatrix.get(x, y) ? 0xFF000000 : 0xFFFFFFFF);
            }
        }

        PDImageXObject pdImage = PDImageXObject.createFromByteArray(document,
                BufferedImageToByteArray(qrImage), "qr");

        PDPageContentStream contentStream = new PDPageContentStream(document, page);


        contentStream.beginText();
        contentStream.setFont(PDType1Font.HELVETICA_BOLD, 16);
        contentStream.newLineAtOffset(50, 700);
        for (String s : Arrays.asList("Ticket ID: " + ticket.getTicketId()
                , "Event: " + ticket.getEvent().getEventId()
                , "Seat: " + ticket.getSeat().getSeatNumber()
                ,"Customer ID: " + ticket.getCustomer().getCustomerId()
                , "Payment Type: " + ticket.getPayment().getPaymentType().name()))
        {
            contentStream.showText(s);
            contentStream.newLineAtOffset(0, -20);
        }
        contentStream.showText("Amount: $" + ticket.getPayment().getAmount());
        contentStream.endText();


        contentStream.drawImage(pdImage, 400, 600, 150, 150);

        contentStream.close();

        document.save(filePath);
        document.close();

        System.out.println("PDF Generated: " + filePath);
    }

    private static byte[] BufferedImageToByteArray(BufferedImage image) throws IOException {
        java.io.ByteArrayOutputStream baos = new java.io.ByteArrayOutputStream();
        ImageIO.write(image, "png", baos);
        return baos.toByteArray();
    }
}