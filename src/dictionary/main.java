/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dictionary;

import java.io.Console;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;
import model.Word;

/**
 *
 * @author lamqu
 */
public class main {

    private static ArrayList<Word> data = new ArrayList<>();

    static void init() {
        data = IOStream.ReadFile();
    }

    static void Output(ArrayList<Word> data) {
        System.out.println("---------Word - Meaning---------");
        for (Word item : data) {
            System.out.printf("%-18s%-5s%-22s\n", item.getWord(), " - ", item.getMeaning());
        }
    }

    
    static void Case1() throws IOException {
        String inputWord = "";
        ArrayList<Word> temp = new ArrayList<>();
        while (true) {
            temp = new ArrayList<>();
            System.out.print("---------Case 1--------- \nNhap -e de thoat\n Nhap tu can tim: ");
            inputWord = Input.input_String();
            if (inputWord.equals("-e")) {
                return;
            }
            for (Word item : data) {
                if (item.getWord().contains(inputWord)) {
                    temp.add(item);
                }
            }
            Output(temp);
        }
    }

    static void Case2() throws IOException {
        String data1 = "", data3 = "", data2 = "";
        System.out.print("---------Case 2--------- \n Nhap tu moi: ");
        data1 = Input.input_String();
        System.out.print("Nhap loai tu: ");
        data2 = Input.input_String();
        System.out.print("Nhap nghia cua tu: ");
        data3 = Input.input_String();

        Word word = new Word(data1, "(" + data2 + ") " + data3);

        data.add(word);
        Collections.sort(data, new Comparator<Word>() {
            @Override
            public int compare(Word o1, Word o2) {
                return o1.getWord().compareTo(o2.getWord());
            }
        });
        if (IOStream.WriteFile(data)) {
            System.out.println("Them tu thanh cong!");
        } else {
            System.out.println("Them tu that bai");
        }
    }

    static Word EditWord(Word word) throws IOException{
        System.out.print("Từ cần chỉnh sửa:\nTừ tiếng Anh("+word.getWord()+"): ");
        word.setWord(Input.input_String());
        System.out.print("Từ tiếng Việt("+word.getMeaning()+"): ");
        word.setMeaning(Input.input_String());
        return word;
    }
    static void Case3() throws IOException, InterruptedException {
        String word = "";
        System.out.print("---------Case 3--------- \nNhập từ cần chỉnh sửa: ");
        word = Input.input_String();
        Word wordTemp = new Word();
        for (Word item : data) {
            if (item.getWord().equals(word)) {
                item = EditWord(item);
                IOStream.WriteFile(data);
                System.out.println("Đã chỉnh sửa!");
                Thread.sleep(2000);
                return;
            }
        }
        System.out.println("Không có từ cần tìm");
        Thread.sleep(2000);
    }
    
    static void Case4() throws IOException, InterruptedException {
        String word = "";
        System.out.print("---------Case 4--------- \nNhập từ cần xóa: ");
        word = Input.input_String();
        Word wordTemp = new Word();
        for (Word item : data) {
            if (item.getWord().equals(word)) {
                data.remove(item);
                IOStream.WriteFile(data);
                System.out.println("Đã xóa");
                Thread.sleep(2000);
                return;
            }
        }
        
        System.out.println("Không có từ cần tìm");
        Thread.sleep(2000);
    }

    static void menu() throws IOException, InterruptedException {
        int select;
        while (true) {
            System.out.print("---------TU DIEN ANH - VIET--------- \n 1. Tim tu\n 2. Them tu \n 3. Sua tu \n 4. Xoa tu \n 0. Thoat \n => Nhap lua chon: ");
            select = Input.input_int();
            switch (select) {
                case 1:
                    Case1();
                    break;
                case 2:
                    Case2();
                    break;
                case 3:
                    Case3();
                    break;
                case 4:
                    Case4();
                    break;
                case 0:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Nhập sai! Vui lòng nhập lại");
                    break;
            }
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        init();
        menu();

//        for(Word item:data){
//            System.out.println(item.getWord() + " - "+ item.getMeaning());
//        }
    }
}
