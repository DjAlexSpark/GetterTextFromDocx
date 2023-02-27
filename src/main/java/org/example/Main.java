package org.example;

import org.apache.poi.xwpf.usermodel.XWPFDocument;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String spath = "src/main/resources/Files";//путь где лежат файлы
        String starget = "target.docx";//куда сохранить и как назвать файл

        XWPFDocument targetFile = null;
        Path path = Path.of(spath);
        Path target = Path.of(starget);

        List<Path> listOfFilesPathsDocx = getListOfFiles(path);//готов

        targetFile = getFileFromPaths(listOfFilesPathsDocx);

        saveTargetFile(targetFile,target);
    }

    private static void saveTargetFile(XWPFDocument targetFile, Path pathToSave) {
        try {
            targetFile.write(new FileOutputStream(new File(pathToSave.toAbsolutePath().toUri())) );
            targetFile.close();
            System.out.println("Saved");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    private static XWPFDocument getFileFromPaths(List<Path> listOfFilesPathsDocx) {
        XWPFDocument resultFile = new XWPFDocument();
        XWPFDocument docx = null;
        for (Path p:listOfFilesPathsDocx) {
            try {
                docx=new XWPFDocument(new FileInputStream(p.toFile()));
                resultFile.getDocument().addNewBody().set(docx.getDocument().getBody());
            } catch (IOException  e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println("file is ready with "+listOfFilesPathsDocx.size());
        return resultFile;
        }


    private static List getListOfFiles(Path pathOfFiles) {
        //лист с файлами в этом пути
        List list =null;
        try {
             list = Files.walk(pathOfFiles)
                    .filter(path ->path.toFile().isFile()
                            &&path.toFile().toString().endsWith(".docx"))
                    .toList();
//                    .sort // разобраться как отсортировать файлы по алфавиту, по дате изменения
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        if (list!=null){
            System.out.println("GetListOFFiles() ready");
            return list;
        }else {
            System.out.println("GetListOFFiles() empty list");
            return new ArrayList<>();
        }

    }

}
