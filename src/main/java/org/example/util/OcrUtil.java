package org.example.util;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

import java.io.File;


public class OcrUtil {

    private static final String  tessdataPath = "./Tesseract-OCR/tessdata";


    public static String ocrByImage(File image) throws TesseractException {
        Tesseract tess = new Tesseract();
        tess.setDatapath(tessdataPath);
        tess.setLanguage("chi_sim+eng");
        String result = tess.doOCR(image);
        System.out.println("识别结果：\n" + result);
        return result;
    }

}


