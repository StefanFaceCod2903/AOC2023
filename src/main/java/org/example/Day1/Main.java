package org.example.Day1;

import org.example.Day2.Round;

import javax.print.DocFlavor;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    static String REGEX = "\\d|zero|one|two|three|four|five|six|seven|eight|nine";


    public static Integer getFirstDigit(String s) {

        Pattern pattern = Pattern.compile(REGEX);
        Matcher matcher = pattern.matcher(s);
        if (matcher.find())
            return getIntegerFromGroup(matcher.group());
        else
            return -1;
    }

    public static Integer getLastDigit(String string) {
        String sb = "";
        int i = string.length();
        while(getFirstDigit(sb) == -1 && i>0) {
            i--;
            sb = string.charAt(i) + sb;
        }
        return getFirstDigit(sb);
    }

        private static Integer getIntegerFromGroup(String group) {
        switch (group) {
            case "zero":
                return 0;
            case "one":
                return 1;
            case "two":
                return 2;
            case "three":
                return 3;
            case "four":
                return 4;
            case "five":
                return 5;
            case "six":
                return 6;
            case "seven":
                return 7;
            case "eight":
                return 8;
            case "nine":
                return 9;
            default:
                char c = group.charAt(0);
                if(Character.isDigit(c)) {
                    return Character.getNumericValue(c);
                }
                return 0;
        }
    }

    public static int getIntegerFromString(String s) {
        int x = getFirstDigit(s) * 10 + getLastDigit(s);
        System.out.println(x);
        return x;
    }
    public static void main(String[] args) throws IOException {
        File input = new File("input1.txt");
        Stream<String> linesStream = Files.lines(input.toPath());
        long result = linesStream.collect(Collectors.summarizingInt(Main::getIntegerFromString)).getSum();
        System.out.println(result);
    }
}
