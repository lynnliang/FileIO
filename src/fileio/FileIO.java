/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fileio;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author lingliang
 */
public class FileIO {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException {
        File inputFile = new File("input.txt");
        File outputFile = new File("outpt.txt");
        BufferedReader reader = null;
        BufferedWriter writer = null;
        try{
            System.out.println("Read whole line every time");
            reader = new BufferedReader (new FileReader(inputFile));
            writer = new BufferedWriter (new FileWriter(outputFile));
            String tempString = null;
            String reverse = null;
            int line = 1;
            //Read a Line
            while ((tempString = reader.readLine()) != null){
                tempString = tempString.replaceAll("[^a-z^A-Z]", "");
                reverse = new StringBuffer(tempString).reverse().toString().toUpperCase();
                System.out.println(reverse);
                writer.write(reverse + "\n");
                line++;
            }
            reader.close();
            writer.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }finally{
            if(reader != null){
                try{
                    reader.close();
                }
                catch (IOException e1){
                    e1.printStackTrace();
                }
            }
            if(writer != null)
            {
                try{
                    writer.close();
                }
                catch(IOException e2)
                {
                    e2.printStackTrace();
                }
            }
        }
    }
    
}
