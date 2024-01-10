package org.example.Day2;

import java.util.ArrayList;
import java.util.List;

public class Game {
    Integer id;
    Integer numberRed;
    Integer numberBlue;
    Integer numberGreen;
    List<Round> rounds;

    public Game(String str, Integer numberRed, Integer numberBlue, Integer numberGreen) {

        rounds = new ArrayList<>();
        this.numberRed = numberRed;
        this.numberBlue = numberBlue;
        this.numberGreen = numberGreen;

        String[] splitted = str.split(": ");
        this.id = Integer.valueOf(splitted[0].split(" ")[1]);
        for (String roundString:
             splitted[1].split("; ")) {
            rounds.add(new Round(roundString));
        }
    }

    private int[] getMaxRGB() {
        int maxRed = 0;
        int maxBlue = 0;
        int maxGreen = 0;
        for (Round r:
                this.rounds) {
            int green = r.getGreen();
            int blue = r.getBlue();
            int red = r.getRed();
            if(green > maxGreen) {
                maxGreen = green;
            }
            if(red > maxRed) {
                maxRed = red;
            }
            if(blue > maxBlue) {
                maxBlue = blue;
            }
        }
        return new int[]{maxRed, maxGreen, maxBlue};
    }

    public int getProduct() {
        int[] rgb = getMaxRGB();
        return rgb[0] * rgb[1] * rgb[2];
    }
    public boolean isValid() {
        for (Round round:
             rounds) {
            if(round.getBlue()>this.numberBlue
                    || round.getGreen() > this.numberGreen
                    || round.getRed()>this.numberRed) {
                return false;
            }
        }
        return true;
    }

    public Integer getId() {
        return id;
    }

    public Integer getNumberRed() {
        return numberRed;
    }

    public Integer getNumberBlue() {
        return numberBlue;
    }

    public Integer getNumberGreen() {
        return numberGreen;
    }

    public List<Round> getRounds() {
        return rounds;
    }
}
