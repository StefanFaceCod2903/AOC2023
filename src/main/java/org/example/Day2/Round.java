package org.example.Day2;

public class Round {
    Integer red = 0;
    Integer blue = 0;
    Integer green = 0;

    public Integer getRed() {
        return red;
    }

    public Integer getBlue() {
        return blue;
    }

    public Integer getGreen() {
        return green;
    }

    public Round(String roundString) {
        String[] splitted = roundString.split(", ");
        for (String s:
             splitted) {
            String color = s.split(" ")[1];
            int amount = Integer.parseInt(s.split(" ")[0]);
            switch (color) {
                case "red":
                    this.red = amount;
                    break;
                case "blue":
                    this.blue = amount;
                    break;
                case "green":
                    this.green = amount;
                    break;
                default:
                    System.out.println("Something went wrong when reading the colors");
            }
        }
    }
}
