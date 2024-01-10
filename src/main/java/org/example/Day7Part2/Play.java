package org.example.Day7Part2;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        return "\nPlay{" +
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

    private  Character getKeyWithMaxValue(Map<Character, Integer> map) {
        if (map == null || map.isEmpty()) {
            return null; // Handle the case when the map is null or empty
        }

        // Initialize variables to store the maximum value and corresponding key
        Integer maxValue = Integer.MIN_VALUE;
        Character maxKey = null;

        // Iterate through the entries of the map
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            Integer value = entry.getValue();

            // If maxValue is null or the current value is greater than maxValue
            if (value.compareTo(maxValue) > 0 && entry.getKey()!= 'J') {
                maxValue = value;
                maxKey = entry.getKey();
            }
        }

        return maxKey;
    }

    private void modifyJokers(HashMap<Character, Integer> map) {
        if(map.containsKey('J')) {
            Character replacement;
            if(map.get('J') == 5) {
                replacement = 'A';
                map.put(replacement, map.get('J'));

            } else {
                replacement = getKeyWithMaxValue(map);
                map.put(replacement, map.get('J') + map.get(replacement));

            }
            map.put('J', 0);
        }
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
        modifyJokers(map);
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
