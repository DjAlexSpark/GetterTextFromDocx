package org.example;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR;

import java.io.*;

public class NewMain {
    public static void main(String[] args) {
        File file = new File("1.docx");
        XWPFDocument docFile;
        try {
            if(file.isFile()||file.exists()||file.canRead()) {
                System.out.println("starting reading");


                System.out.println("qweasdzxc");
                if (true) {
                    docFile = new XWPFDocument(new FileInputStream(file));
                    XWPFWordExtractor extractor = new XWPFWordExtractor(docFile);
                    System.out.println(extractor.getText());
                    docFile.getLastParagraph().createRun().addCarriageReturn();
                    docFile.getLastParagraph().createRun().setText("Этот текст должен быть с новй строки");
                    System.out.println(docFile.getDocument().toString());
                    extractor=new XWPFWordExtractor(docFile);
                    System.out.println(extractor.getText());
                }
                //write file

        }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
