package com.riccardorocco.IO;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

public class ReadFile {
    public static void main(String[] args) throws IOException, FileNotFoundException {
        try (Reader reader = new BufferedReader(new FileReader("C:/Users/mdro/Downloads/fileRead.txt"))){
            read(reader);
            reader.close();
        }
        
        try (Reader reader = new BufferedReader(new InputStreamReader(getSourceStream()))){
            read(reader);
            reader.close();
        }

        String test = "1,ciao;ciaone,,5.99";
        String[] tmp = test.split(",");
        for (String str : tmp){
            System.out.println(str);
        }
    }

    static InputStream getSourceStream() throws IOException{
        return new FileInputStream("C:/Users/mdro/Downloads/fileRead.txt");
    }

    static void read(Reader reader) throws IOException{
        int c;
        while( (c=reader.read())>=0 ){
            System.out.print((char) c);
        }
        System.out.println();
    }
}
