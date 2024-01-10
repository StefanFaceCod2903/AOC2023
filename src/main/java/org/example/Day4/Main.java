package org.example.Day4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {

    static long calculateNumberOfCards(List<Card> cardList) {
        HashMap<Card, Long> hashMap = new HashMap<>();
        for (Card c:
             cardList) {
            hashMap.put(c, 1L);
        }
        for (Card c:
             cardList) {
            for(int i=1;i<=c.getMatching();i++) {
                if(c.getId() + i <=cardList.size()) {
                    Card increasingCard = cardList.get(c.getId()+i - 1);
                    hashMap.put(increasingCard, hashMap.get(increasingCard) + hashMap.get(c));
                }
            }
        }
        return hashMap.values().stream().reduce(0L, Long::sum);
    }

    public static void main(String[] args) {
        String filePath = "input4.txt";
        List<Card> cards = new ArrayList<>();
        try {
            File myObj = new File(filePath);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String line = myReader.nextLine();
                Card card = new Card(line);
                cards.add(card);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        System.out.println(calculateNumberOfCards(cards));
    }
}
