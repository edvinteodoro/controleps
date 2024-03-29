/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gt.edu.cunoc.controleps.utils;

import fr.opensagres.poi.xwpf.converter.pdf.PdfConverter;
import fr.opensagres.poi.xwpf.converter.pdf.PdfOptions;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author edvin
 */
@Component
public class PoiUtils {

    public MultipartFile generarDocumento(Map<String, Object> data, InputStream template, String type, String nombreDocumento) throws Exception {
        XWPFDocument document = new XWPFDocument(template);
        for (XWPFParagraph paragraph : document.getParagraphs()) {
            for (XWPFRun run : paragraph.getRuns()) {
                String text = run.getText(0);
                if (text != null) {
                    for (String key : data.keySet()) {
                        if (text.contains(key)) {
                            text = text.replace(key, data.get(key).toString());
                        }
                    }
                    run.setText(text, 0);
                }
            }
        }
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        document.write(baos);
        return convertirDocumento(document,nombreDocumento);
    }

    public MultipartFile convertirDocumento(XWPFDocument document,String nombreDocumento) throws Exception {
        PdfOptions options = PdfOptions.create();

        // Convert DOC to PDF
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PdfConverter.getInstance().convert(document, baos, options);

        // Construct a MultipartFile from the generated PDF content
        MultipartFile pdfFile = new MockMultipartFile(nombreDocumento, nombreDocumento, "application/pdf", baos.toByteArray());

        document.close();
        baos.close();

        return pdfFile;
    }
}
