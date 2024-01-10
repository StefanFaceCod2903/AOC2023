package org.example.Day5;

import java.util.List;

public class Seed {
    private Long id;
    private Long location;
    private Long soil;
    private Long fertilizer;
    private Long water;
    private Long light;
    private Long temperature;
    private Long humility;

    private long getNext(long previous, List<long[]> matrix) {
        long next = previous;
        for (long[] arr:
             matrix) {
            if(previous >= arr[1] && previous < arr[2] + arr[1]) {
                next = previous + arr[0] - arr[1];
            }
        }
        return next;
    }

    public Long getId() {
        return id;
    }

    public Long getLocation() {
        return location;
    }

    public Long getSoil() {
        return soil;
    }

    public Long getFertilizer() {
        return fertilizer;
    }

    public Long getWater() {
        return water;
    }

    public Long getLight() {
        return light;
    }

    public Long getTemperature() {
        return temperature;
    }

    public Long getHumility() {
        return humility;
    }

    public Seed(Long id, List<long[]> seedToSoil, List<long[]> soilToFertilizer, List<long[]> fertilizerToWater, List<long[]> waterToLight, List<long[]> lightToTemperature, List<long[]> temperatureToHumidity, List<long[]> humidityToLocation) {
        this.id = id;
        this.soil = getNext(this.id, seedToSoil);
        this.fertilizer = getNext(this.soil, soilToFertilizer);
        this.water = getNext(this.fertilizer, fertilizerToWater);
        this.light = getNext(this.water, waterToLight);
        this.temperature = getNext(this.light, lightToTemperature);
        this.humility = getNext(this.temperature, temperatureToHumidity);
        this.location = getNext(this.humility, humidityToLocation);
    }

    @Override
    public String toString() {
        return "Seed{" +
                "id=" + id +
                ", location=" + location +
                ", soil=" + soil +
                ", fertilizer=" + fertilizer +
                ", water=" + water +
                ", light=" + light +
                ", temperature=" + temperature +
                ", humility=" + humility +
                '}';
    }
}
