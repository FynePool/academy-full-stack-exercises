package com.riccardorocco.IO;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class CopyFile {

    public static void main(String[] args) throws IOException, FileNotFoundException {

        File file = new File("C:/Users/mdro/Downloads/prova.txt");
        System.out.println("Reading file: " + file.getAbsolutePath());
        File dest = new File("C:/Users/mdro/Downloads/prova_copy.txt");
        System.out.println("Writing file: " + dest.getAbsolutePath());

        try (InputStream srcStream = new BufferedInputStream(new FileInputStream(file));
                OutputStream destStream = new BufferedOutputStream(new FileOutputStream(dest));) {
            int value = srcStream.read();
            while (value != -1) {
                destStream.write(value);
                value = srcStream.read();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}