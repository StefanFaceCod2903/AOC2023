package org.example.Day10;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String filePath = "input9.txt";
        char [][] input = new char[5][5];
        int startX = 0;
        int startY = 0;
        try {
            File myObj = new File(filePath);
            Scanner myReader = new Scanner(myObj);
            int i = 0;
            int j = 0;
            while(myReader.hasNext()) {
                char c = myReader.next().charAt(0);
                if(c=='\n') {
                    j++;
                    i=0;
                } else {
                    if(c=='S') {
                        startY = i;
                        startX = j;
                    }
                    input[i][j] = c;
                }
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
