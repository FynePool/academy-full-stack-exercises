package com.riccardorocco.IO;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;

public class CopyTextFile {

    public static void main(String[] args) {

        File file = new File("C:/Users/mdro/Downloads/prova.txt");
        System.out.println("Reading file: " + file.getAbsolutePath());
        File dest = new File("C:/Users/mdro/Downloads/prova_copy.txt");
        System.out.println("Writing file: " + dest.getAbsolutePath());

        try (BufferedReader srcStream = new BufferedReader(new FileReader(file));
                PrintWriter destStream = new PrintWriter(new BufferedWriter(new FileWriter(dest, Charset.forName("UTF-16"))));) {
            String value;
            while ((value = srcStream.readLine()) != null) {
                destStream.println(value);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        // BufferedReader srcStream = null;
        // BufferedWriter destStream = null;
        // try {
        //     srcStream = new BufferedReader(new FileReader(file));
        //     destStream = new BufferedWriter(new FileWriter(dest, Charset.forName("UTF-16")));            
        //     String value;
        //     while ((value = srcStream.readLine()) != null) {
        //         destStream.append(value).append('\n');
        //     }
        // } catch (IOException e) {
        //     e.printStackTrace();
        // } finally {
        //     try {
        //         if (srcStream != null) srcStream.close();
        //     } catch(IOException e) { /* nothing */ }
        //     try {
        //         if (destStream != null) destStream.close();
        //     } catch(IOException e) { /* nothing */ }
        // }
        
    }
}