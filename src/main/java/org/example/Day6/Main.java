package org.example.Day6;

import java.io.File;
import java.io.FileNotFoundException;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
//        partOne();
        partTwo();
    }

    private static void partOne() {
        String filePath = "input6.txt";
        int[] times;
        int[] distance;
        try {
            File myObj = new File(filePath);
            Scanner myReader = new Scanner(myObj);

            String timesString = myReader.nextLine().split(":\\s+")[1];
            String distanceString = myReader.nextLine().split(":\\s+")[1];
            times = Arrays.stream(timesString.split("\\s+")).mapToInt(Integer::parseInt).toArray();
            distance = Arrays.stream(distanceString.split("\\s+")).mapToInt(Integer::valueOf).toArray();
            long result = computeResult(distance, times);
            System.out.println(result);
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    private static void partTwo() {
        String filePath = "input6.txt";
        long time;
        long distance;
        try {
            File myObj = new File(filePath);
            Scanner myReader = new Scanner(myObj);

            String timesString = myReader.nextLine().split(":\\s+")[1];
            String distanceString = myReader.nextLine().split(":\\s+")[1];
            time = Long.parseLong(Arrays.stream(timesString.split("\\s+")).reduce("", (acc,elem) -> acc+elem));
            distance = Long.parseLong(Arrays.stream(distanceString.split("\\s+")).reduce("", (acc,elem) -> acc+elem));
            long result = computeNumberPossibilities(distance, time);
            System.out.println(result);
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }



    private static long computeResult(int[] distance, int[] times) {
        long p = 1L;
        for(int i=0;i<distance.length;i++) {
            p*= computeNumberPossibilities(distance[i], times[i]);
        }
        return p;
    }

    private static long computeNumberPossibilities(long record, long time) {
        long possibilities = 0L;
        for(int i=0;i<=time;i++) {
            long distanceTraveled = (time-i)*i;
            if(distanceTraveled>record) possibilities++;
        }
        return possibilities;
    }
}
