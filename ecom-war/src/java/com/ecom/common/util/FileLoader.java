package com.ecom.common.util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FileLoader {

    public static void loadFile(String filename) throws IOException {
        FileInputStream fs = new FileInputStream(filename);
        BufferedReader br = new BufferedReader(new InputStreamReader(fs));
        String line = "";
        while ((line = br.readLine()) != null) {
            System.out.println("" + line);
        }
    }
    
    public static void main(String[] args) {
        try {
            loadFile("C:\\db2admin.txt");
        } catch (IOException ex) {
            Logger.getLogger(FileLoader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}