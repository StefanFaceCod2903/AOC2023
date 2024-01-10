package org.example.Day2;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Objects;
import java.util.stream.Stream;

public class Main {
    public static void partTwo() throws IOException{
        File input = new File("input2.txt");
        long sum = Files.lines(input.toPath())
                .map((s) -> new Game(s,12,14,13))
                .mapToLong(Game::getProduct).sum();
        System.out.println(sum);
    }
    public static void partOne() throws IOException{
        File input = new File("input2.txt");
        long sum = Files.lines(input.toPath())
                .map((s) -> new Game(s,12,14,13))
                .filter(Game::isValid)
                .mapToInt(Game::getId)
                .sum();
        System.out.println(sum);
    }
    public static void main(String[] args) throws IOException {
        partTwo();
    }
}
