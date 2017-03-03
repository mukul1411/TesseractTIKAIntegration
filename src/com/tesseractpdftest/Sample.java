package com.tesseractpdftest;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.parser.ocr.TesseractOCRConfig;
import org.apache.tika.parser.pdf.PDFParserConfig;
import org.apache.tika.sax.BodyContentHandler;
import org.xml.sax.SAXException;

/**
 * @Mukul 04/01/17
 *
 * My pdf contains scanned images and I want to extract text from it.
 */
public class Sample {
    public static void main(String[] args)
            throws IOException, TikaException, SAXException {
        Parser parser = new AutoDetectParser();
        BodyContentHandler handler = new BodyContentHandler(Integer.MAX_VALUE);

        TesseractOCRConfig config = new TesseractOCRConfig();
        String tPath = "C://infogps-agent-bin//Tesseract-OCR"; // tesseract installation directory.
		config.setTesseractPath(tPath);

		config.setPageSegMode("3");
        PDFParserConfig pdfConfig = new PDFParserConfig();
        pdfConfig.setExtractInlineImages(true);

        pdfConfig.setExtractUniqueInlineImagesOnly(false); // set to false if pdf contains multiple images.

        ParseContext parseContext = new ParseContext();
        parseContext.set(TesseractOCRConfig.class, config);
        parseContext.set(PDFParserConfig.class, pdfConfig);
        //need to add this to make sure recursive parsing happens!
        parseContext.set(Parser.class, parser);

        // Parsed file location directory.
        FileInputStream stream = new FileInputStream("C://Users//Atlas08//Desktop//index//tmx.pdf");
        Metadata metadata = new Metadata();
        parser.parse(stream, handler, metadata, parseContext);
        System.out.println(metadata);
        String content = handler.toString();
        System.out.println("===============");
        System.out.println(content);

        System.out.println("Done");


    }
}