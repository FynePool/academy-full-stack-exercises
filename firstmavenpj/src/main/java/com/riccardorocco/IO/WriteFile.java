package com.riccardorocco.IO;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;

public class WriteFile {
    public static void main(String[] args) throws IOException {
        Writer fw = new FileWriter("C:/Users/mdro/Downloads/fileWrite.txt");
        write(fw);
        fw.write("\nCIAO");
        fw.close();
    }

    static void write(Writer writer) throws IOException {
        PrintWriter pw = new PrintWriter(new BufferedWriter(writer));
        pw.println("Questo è un test di scrittura su file:");
        pw.println("Prima Riga");
        pw.println("Seconda Riga");
        pw.println("Terza Riga");
        pw.print("Quarta Riga");
        pw.print(" - Ancora Quarta Riga");
        pw.flush();
    }
}
