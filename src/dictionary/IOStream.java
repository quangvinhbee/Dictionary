/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dictionary;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import model.Word;

/**
 *
 * @author lamqu
 */
public class IOStream {

    public static ArrayList<Word> ReadFile() {

        ArrayList<Word> dta = new ArrayList<>();
        try {
            File myObj = new File("C:\\Users\\lamqu\\Documents\\NetBeansProjects\\Dictionary\\src\\dictionary\\data.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data1 = myReader.nextLine();
                String data2 = myReader.nextLine();
                Word word = new Word(data1, data2);
                dta.add(word);
            }

            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Lỗi đọc file.");
            e.printStackTrace();
        }

        return dta;
    }

    public static boolean WriteFile(ArrayList<Word> data) {
        try {
            FileWriter myWriter = new FileWriter("C:\\Users\\lamqu\\Documents\\NetBeansProjects\\Dictionary\\src\\dictionary\\data.txt");
            for(Word item:data){
                myWriter.write(item.getWord()+"\n");
                myWriter.write(item.getMeaning()+"\n");
            }
            myWriter.close();
            return true;
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
             return false;
        }
    }

}
