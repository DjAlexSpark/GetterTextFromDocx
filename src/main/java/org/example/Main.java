package org.example;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

import javax.swing.text.Document;
import javax.xml.parsers.DocumentBuilder;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        File finalFile = new File("1.docx");
        FileInputStream finalFis;
        XWPFDocument finalDocument;
        String finalDocumentLine ="";
        XWPFWordExtractor finalExtractor;
        StringBuffer s = new StringBuffer();
        //получить все файлы
        // из каждого файла импортировать в последний
        File file;
        FileInputStream fis;
        XWPFDocument document;
        String documentLine ="";
        XWPFWordExtractor extractor;
        List<Path> list = null;
        try {
            finalFis = new FileInputStream(finalFile.getAbsolutePath());
            finalDocument = new XWPFDocument(finalFis); // Вот и объект описанного нами класса


            finalExtractor = new XWPFWordExtractor(finalDocument);
            finalDocumentLine = finalDocument.getDocument().toString();
            //System.out.println(finalExtractor.getText());
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            Path path = Path.of("C:\\Users\\33\\IdeaProjects\\GetterTextFromDocx\\src\\main\\resources");
            list = Files.walk(path).filter(p -> p != path && p.toFile().isFile()).collect(Collectors.toList());
        } catch (IOException e) {
            // TODO implement better exception handling
            e.printStackTrace();
        }
        System.out.println(list);

        for (Path l:list) {
            if (l.toString().toLowerCase().endsWith("docx")){
            file = l.toFile();
            try {
                fis = new FileInputStream(file.getAbsolutePath());
                document = new XWPFDocument(fis); // Вот и объект описанного нами класса
                extractor = new XWPFWordExtractor(document);
                documentLine = document.getDocument().toString();
                //System.out.println(extractor.getText());
                s.append(extractor.getText());
                //XWPFDocument documentNew = new XWPFDocument(fis);
                //documentNew.getDocument().toString();

                //пишет в txt


            }catch(IOException e){
                System.out.println("something goes wrong");
            }

        }}


        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("Im tough.txt"));
            writer.write(s.toString());
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println(s);
    }
}