package org.example.Day3;

import org.example.Day2.Game;

import java.io.*;
import java.nio.file.Files;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Main {

    public static void partOne() {
        char [][] m = null;
        String filePath = "input3.txt";
        try {
            File myObj = new File("input3.txt");
            Scanner myReader = new Scanner(myObj);
            int i =0;
            while (myReader.hasNextLine()) {
                String line = myReader.nextLine();
                if(m==null) {
                    m = new char[140][line.length()+1];
                }
                m[i] = (line + "\n").toCharArray();
                i++;
            }
            Engine engine = new Engine(m);
            System.out.println(engine.getGearsValue());;
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        partOne();
    }
}
