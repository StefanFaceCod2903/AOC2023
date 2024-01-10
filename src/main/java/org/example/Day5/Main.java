package org.example.Day5;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void readMapping(List<long[]> mappings, Scanner sc) {
        sc.nextLine();
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            if(line.equals("")) break;
            mappings.add(Arrays.stream(line.split(" ")).mapToLong(Long::valueOf).toArray());
        }
    }
    public static long[] getSeeds(String seedsString) {
        return Arrays.stream(seedsString.split(": ")[1].split(" ")).mapToLong(Long::valueOf).toArray();
    }

    public static void main(String[] args) {
        String filePath = "input5.txt";
        long[] seeds = null;

        List<long[]> seedToSoil = new ArrayList<>();;
        List<long[]> soilToFertilizer = new ArrayList<>();
        List<long[]> fertilizerToWater = new ArrayList<>();
        List<long[]> waterToLight = new ArrayList<>();
        List<long[]> lightToTemperature = new ArrayList<>();
        List<long[]> temperatureToHumidity = new ArrayList<>();
        List<long[]> humidityToLocation = new ArrayList<>();
        try {
            File myObj = new File(filePath);
            Scanner myReader = new Scanner(myObj);
            String seedsLine = myReader.nextLine();

            seeds = getSeeds(seedsLine);

            myReader.nextLine();

            readMapping(seedToSoil, myReader);
            readMapping(soilToFertilizer, myReader);
            readMapping(fertilizerToWater, myReader);
            readMapping(waterToLight, myReader);
            readMapping(lightToTemperature, myReader);
            readMapping(temperatureToHumidity, myReader);
            readMapping(humidityToLocation, myReader);
            myReader.close();

        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        //part one
        List<Long> seedList = Arrays.stream(seeds).mapToObj(e -> new Seed(e, seedToSoil,soilToFertilizer,fertilizerToWater,waterToLight,lightToTemperature,temperatureToHumidity,humidityToLocation).getLocation()).toList();
        System.out.println(seedList.stream().min(Long::compareTo).get());
        //part two
        long minimum = Long.MAX_VALUE;
        for(int i=0; i<seeds.length;i+=2) {
            for(long j=0; j<seeds[i+1];j++) {
                Seed seed = new Seed(j+seeds[i], seedToSoil, soilToFertilizer, fertilizerToWater, waterToLight, lightToTemperature, temperatureToHumidity, humidityToLocation);
                if(seed.getLocation()<minimum) {
                    minimum = seed.getLocation();
                }
            }
        }
        System.out.println(minimum);
    }
}
