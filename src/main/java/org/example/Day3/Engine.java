package org.example.Day3;

import java.util.*;

public class Engine {
    int[][] m;

    Map<Integer,Integer> lat;
    private boolean isPart(int i,int j) {
        return (i > 0 && j > 0 && m[i - 1][j - 1] <= -2)
                || (i > 0 && m[i - 1][j] <= -2)
                || (i > 0 && j < m[0].length-1 && m[i - 1][j + 1] <= -2)
                || (j > 0 && m[i][j - 1] <= -2)
                || (j < m[0].length-1 && m[i][j + 1] <= -2)
                || (i < m.length-1 && j > 0 && m[i + 1][j - 1] <= -2)
                || (i < m.length-1 && m[i + 1][j] <= -2)
                || (i < m.length-1 && j < m[0].length-1 && m[i + 1][j + 1] <= -2);
    }

    private Set<Integer> getNumberAroundGears(int i,int j) {
        Set<Integer> numbers = new HashSet<>();
        if (i > 0 && j > 0 && m[i - 1][j - 1] >= 0) {
            numbers.add(m[i-1][j-1]);
        }
        if (i > 0 && m[i - 1][j] >= 0) {
            numbers.add(m[i-1][j]);
        }
        if (i > 0 && j < m[0].length-1 && m[i - 1][j + 1] >= 0) {
            numbers.add(m[i-1][j+1]);
        }
        if(j > 0 && m[i][j - 1] >= 0) {
            numbers.add(m[i][j-1]);
        }
        if (j < m[0].length-1 && m[i][j + 1] >= 0) {
            numbers.add(m[i][j+1]);
        }
        if (i < m.length-1 && j > 0 && m[i + 1][j - 1] >= 0) {
            numbers.add(m[i+1][j-1]);
        }
        if(i < m.length-1 && m[i + 1][j] >= 0) {
            numbers.add(m[i+1][j]);
        }
        if(i < m.length-1 && j < m[0].length-1 && m[i + 1][j + 1] >= 0){
            numbers.add(m[i+1][j+1]);
        }
        return numbers;
    }

    public Integer getGearsValue() {
        int product = 0;
        for(int i=0;i< m.length;i++) {
            for(int j=0;j<m[0].length;j++) {
                if(m[i][j] == -3) {
                    Set<Integer> gears = getNumberAroundGears(i,j);
                    if(gears.size()==2) {
                        product+= gears.stream().reduce((a, b) -> this.lat.get(a)*this.lat.get(b)).orElse(0);
                    }
                }
            }
        }
        return product;
    }
    public Set<Integer> getListOfParts() {
        Set<Integer> integerSet = new HashSet<>();
        for(int i=0;i< m.length;i++) {
            for(int j=0;j<m[0].length;j++) {
                if(m[i][j]>=0 && this.isPart(i,j)) {
                    integerSet.add(m[i][j]);
                }
            }
        }
        return integerSet;
    }

    public Integer getSum() {
        Integer sum = 0;
        Set<Integer> parts = getListOfParts();
        for (Integer part:
             parts) {
            sum += this.lat.get(part);
        }
        return sum;
    }

    public Engine(char[][] m) {
        this.m = new int[m.length][m[0].length-1];
        lat = new HashMap<>();
        int numberCount = 0;
        int currentNumber = 0;

        for (int i=0;i<m.length;i++) {
            for (int j=0;j<m[0].length;j++) {
                char c = m[i][j];
                if(Character.isDigit(c)) {
                    int val = Character.getNumericValue(c);
                    currentNumber = currentNumber * 10 + val;
                    this.m[i][j] = numberCount;
                } else {
                    if(currentNumber!=0) {
                        lat.put(numberCount,currentNumber);
                        numberCount++;
                        currentNumber=0;
                    }
                    if(c=='.') {
                        this.m[i][j] = -1;
                    } else if (c=='*') {
                        this.m[i][j] = -3;
                    }else if(c!='\n'){
                        this.m[i][j] = -2;
                    }
                }
            }
        }
    }
}
