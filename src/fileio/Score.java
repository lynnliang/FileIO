/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fileio;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author lingliang
 */
public class Score {
    private String fileName;
    private Map<String, Integer> score;
    private Scanner scanner;
    private BufferedReader reader;
    private BufferedWriter writer;
    private PrintWriter out;
    
    public Score(String fn){
        fileName = fn;
        score = new HashMap<> ();
        scanner = null;
        writer = null;
        out = null;
    }
    
    public void readFile() throws FileNotFoundException{
        String line = null;
        String[] str2 = new String[2];
        reader = new BufferedReader(new FileReader(fileName));
        try {
            while((line = reader.readLine())!=null){
                str2 = line.split(" ");
                score.put(str2[0], Integer.valueOf(str2[1]));
            }
            reader.close();
        } catch (IOException ex) {
            Logger.getLogger(Score.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            if (reader != null)
            {
                try {
                    reader.close();
                } catch (IOException ex) {
                    Logger.getLogger(Score.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
    public void interact(){
        String str = null;
        String[] str2 = null;
       
        try{
            scanner = new Scanner(System.in);
            Boolean terminate=false;
            Boolean validScore = false;
            while(!terminate)
            {

                do{
                    str = scanner.nextLine();
                    if(str.equals("x")){
                        terminate = true;
                        break;
                    }
                    str2 = str.split(" ");
                    if (str2.length ==2 && isIntegerMethod(str2[1]))
                    {
                        validScore = true;
                    }
                    else{
                        validScore = false;
                        System.out.println("Please input the student name and score again(input 'x' to exit):");
                    }
                }while(!validScore);
                if(terminate == true){
                    break;
                }
                //check if the student exist
                if(score.get(str2[0]) == null){
                    score.put(str2[0],Integer.valueOf(str2[1]));
                    System.out.println("The new student name:" + str2[0]+ ", score:" + str2[1]);
                }
                else{
                    System.out.println("The existing student has name:" + str2[0] 
                            + " and score" + score.get(str2[0]));
                    System.out.println("\n Do you want to overwrite it?") ;
                    str = scanner.nextLine().trim();
                    if(str.equals("x"))
                    {
                        terminate = true;
                        break;
                    }else if(str.equals("yes")){
                        score.put(str2[0], Integer.valueOf(str2[1]));
                        System.out.println("Student record Updated!");
                    }else
                    {
                        System.out.println("The score is not saved.");
                    }

                }
            }        
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (scanner != null) {
                scanner.close();
                scanner = null;
            }
        }

    }

    public void save() {
        try {
            out = new PrintWriter("scores.txt");
            for (Map.Entry<String, Integer> entry : score.entrySet()) {
                out.println(entry.getKey() + " " + entry.getValue());
            }
            out.flush();
            System.out.println("Data is written to scores.txt.");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                out.close();
                out = null;
            }
        }
    }
    
    public Boolean isIntegerMethod(String str){
        if(str == null || str.length()== 0){
            return false;
        }
        if(!str.matches("[0-9]+")){
            return false;
        }
        return true;
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException {
        Score s = new Score("scores.txt");
        s.readFile();
        s.interact();
        s.save();
    }
    
}
