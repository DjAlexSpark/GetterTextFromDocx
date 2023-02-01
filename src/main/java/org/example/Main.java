package org.example;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

import javax.swing.text.Document;
import javax.xml.parsers.DocumentBuilder;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        File file = new File("1.docx");
        FileInputStream fis;
        XWPFDocument document;
        String documentLine ="";
        XWPFWordExtractor extractor;
        try {

            fis = new FileInputStream(file.getAbsolutePath());
            document = new XWPFDocument(fis); // Вот и объект описанного нами класса
            extractor = new XWPFWordExtractor(document);
            documentLine = document.getDocument().toString();
            System.out.println(extractor.getText());

            //XWPFDocument documentNew = new XWPFDocument(fis);
            //documentNew.getDocument().toString();

            //пишет в txt
            BufferedWriter writer = new BufferedWriter(new FileWriter("Im tough.txt"));
            writer.write(extractor.getText());
            writer.close();

        }catch(IOException e){
            System.out.println("something goes wrong");
        }



    }
}