package org.example.Day7Part2;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    private final static String filePath = "input7.txt";

    public static long totalWinnings(List<Play> playList) {
        long sum = 0L;
        for (Play p:
                playList) {
            int rank = (playList.size() - playList.indexOf(p));
            int winning =  p.getBid() * rank;
//            System.out.printf("%d * %d = %d%n", p.getBid(), rank, winning);
            sum = sum + winning;
        }
        return sum;
    }
    public static void partTwo() {
        List<Play> playList = new ArrayList<>();
        try {
            File myObj = new File(filePath);
            Scanner myReader = new Scanner(myObj);
            while(myReader.hasNextLine()) {
                String playString = myReader.nextLine();
                playList.add(new Play(playString));
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        List<Play> sortedList = playList.stream().sorted(new PlayComparator()).toList();
        System.out.println(sortedList);
        System.out.println(totalWinnings(sortedList));
    }
    public static void main(String[] args) {
        partTwo();
    }
}
