package org.example.Day9;

import org.example.Day8.Node;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;

public class Main {
    // Function to calculate factorial
    private static long factorial(int n) {
        if (n == 0 || n == 1) {
            return 1;
        } else {
            return n * factorial(n - 1);
        }
    }

    // Function to calculate Newton's binomial coefficient C(n, k)
    public static long calculateBinomialCoefficient(int n, int k) {
        if (k < 0 || k > n) {
            throw new IllegalArgumentException("Invalid input. n and k must be non-negative, and k must be less than or equal to n.");
        }

        long numerator = factorial(n);
        long denominator = factorial(k) * factorial(n - k);

        return numerator / denominator;
    }
    public static boolean allZero(int[] arr, int end) {
        for(int i=0;i<end;i++) {
            if(arr[i]!=0) return false;
        }
        return true;
    }


    public static int getNext(int []arr) {
        int i = arr.length-1;
        while (!allZero(arr, i)) {
            for (int j = 0; j < i; j++) {
                arr[j] = arr[j+1] - arr[j];
            }
            i--;
        }
        Arrays.stream(arr).forEach(e-> System.out.printf("%d ", e));
        System.out.println();
        int newElem = 0;
        for(int k=i;k<arr.length;k++) {
            newElem += arr[k];
        }
        return newElem;
    }

    public static boolean isValidBinomial(int[] arr, int start, int finish) {
        int len = finish - start;
        int sum = 0;
        for(int i = start; i<finish;i++) {
            if(i%2==1)
                sum += arr[i] * calculateBinomialCoefficient(len-1, i-start);
            else
                sum -= arr[i] * calculateBinomialCoefficient(len-1, i-start);
        }
        return sum == 0;
    }

    public static long nextValueOfBinomial(int []arr, int finish) {
        long sum = 0;
        for(int i = 0; i< finish; i++) {
            long val;
            long coef = calculateBinomialCoefficient(finish, i);
            System.out.println(coef);
            if(i%2==1)
                val = arr[i] * coef;
            else
                val = -arr[i] * coef;
            if(finish %2==1) {
                val = -val;
            }
            sum+=val;
        }
        return sum;
    }

    public static void reverseArray(int[] array) {
        int start = 0;
        int end = array.length - 1;

        while (start < end) {
            // Swap elements at start and end indices
            int temp = array[start];
            array[start] = array[end];
            array[end] = temp;

            // Move indices towards the center
            start++;
            end--;
        }
    }

    public static void main(String[] args) {
        String filePath = "input9.txt";
        int sum = 0;
        try {
            File myObj = new File(filePath);
            Scanner myReader = new Scanner(myObj);
            while(myReader.hasNextLine()) {
                int[] initialArray = Arrays.stream(myReader.nextLine().split(" ")).mapToInt(Integer::valueOf).toArray();
//                System.out.println(Arrays.stream(initialArray).boxed().toList());
                reverseArray(initialArray);
                long next = getNext(initialArray);
//                System.out.printf("\n%d\n",next);
                sum += next;
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        System.out.println(sum);
    }
}
