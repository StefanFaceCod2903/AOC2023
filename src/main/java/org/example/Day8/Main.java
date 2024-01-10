package org.example.Day8;

import org.example.Day7Part2.Play;
import org.example.Day7Part2.PlayComparator;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    final static String filePath = "input8.txt";

    public static int steps(Map<String, Node> map, String leftRight, String current) {
        char [] lr = leftRight.toCharArray();
        int i = 0;
        while(!current.endsWith("Z")) {
            if(lr[i%lr.length] == 'L') {
                current = map.get(current).getLeft();
            } else {
                current = map.get(current).getRight();
            }
            i++;
        }
        return i;
    }
    private static long calculateGCD(long a, long b) {
        if (b == 0) {
            return a;
        }
        return calculateGCD(b, a % b);
    }

    // Function to calculate the LCM of two numbers
    private static long calculateLCM(long a, long b) {
        return (a * b) / calculateGCD(a, b);
    }

    // Function to calculate the LCM of a list of integers
    public static long calculateLCM(List<Integer> numbers) {
        if (numbers == null || numbers.isEmpty()) {
            throw new IllegalArgumentException("Input list cannot be null or empty");
        }

        long lcm = numbers.get(0);

        for (int i = 1; i < numbers.size(); i++) {
            lcm = calculateLCM(lcm, numbers.get(i));
        }

        return lcm;
    }
    public static long simultaneousSteps(Map<String, Node> map, String leftRight, List<String> as) {
        Object[] currents =as.toArray();
        List<Integer> results = new ArrayList<>();
        for (String a:
             as) {
            results.add(steps(map, leftRight, a));
        }
        return calculateLCM(results);
    }
    public static void partOne() {
        String pattern = "([0-9A-Z]{3}) = \\(([0-9A-Z]{3}), ([0-9A-Z]{3})\\)";
        Pattern r = Pattern.compile(pattern);
        Map<String, Node> map = new HashMap<>();
        String leftRight = "";
        try {
            File myObj = new File(filePath);
            Scanner myReader = new Scanner(myObj);
            leftRight = myReader.nextLine();
            myReader.nextLine();
            while(myReader.hasNextLine()) {
                String nodeString = myReader.nextLine();
                Matcher m = r.matcher(nodeString);
                if(m.find()) {
                    String id = m.group(1);
                    Node node = new Node(m.group(2), m.group(3));
                    map.put(id, node);
                }
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
//        System.out.println(steps(map,leftRight, "AAA", "ZZZ"));
        List<String>  as = map.keySet().stream().filter(s->s.endsWith("A")).toList();
        System.out.println(simultaneousSteps(map, leftRight, as));
    }

    public static void main(String[] args) {
        partOne();
    }
}
