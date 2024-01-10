package org.example.Day7Part1;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Play {
    private char[] cards;
    private int bid;
    private Hand hand;

    public char[] getCards() {
        return cards;
    }

    public int getBid() {
        return bid;
    }

    public Hand getHand() {
        return hand;
    }

    @Override
    public String toString() {
        return "Play{" +
                "cards=" + Arrays.toString(cards) +
                ", bid=" + bid +
                ", hand=" + hand +
                '}';
    }

    public Play(String playString) {
        this.cards = playString.split("\\s+")[0].toCharArray();
        this.bid = Integer.parseInt(playString.split("\\s+")[1]);
        this.hand = computeHand();
    }

    private Hand computeHand() {
        HashMap<Character, Integer> map = new HashMap<>();
        for (char c:
                this.cards) {
            if(map.containsKey(c)) {
                map.put(c, map.get(c)+1);
            } else {
                map.put(c, 1);
            }
        }
        List<Integer> values = map.values().stream().toList();
        if(values.contains(2) && values.contains(3)) {
            return Hand.FullHouse;
        } if(values.contains(5)) {
            return Hand.FiveOfAKind;
        } else if (values.contains(4)) {
            return Hand.FourOfAKind;
        } else if (values.contains(3)) {
            return Hand.ThreeOfAKind;
        } else if (values.contains(2)) {
            long numberOfPairs = values.stream().filter(e-> e==2).count();
            if(numberOfPairs==1) {
                return Hand.OnePair;
            } else {
                return Hand.TwoPair;
            }
        } else {
            return Hand.HighCard;
        }
    }
}
