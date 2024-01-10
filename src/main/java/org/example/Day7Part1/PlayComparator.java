package org.example.Day7Part1;

import java.util.*;

public class PlayComparator implements Comparator<Play> {
    final static Character[] FACES = {'A', 'K', 'Q', 'J', 'T', '9', '8', '7', '6', '5', '4', '3','2'};
    private int computeSecondPriority(char c1, char c2) {
        List<Character> faces = new ArrayList<>(Arrays.asList(FACES));
        int diff = faces.indexOf(c1) - faces.indexOf(c2);
        if(diff>0) {
            return 1;
        } else if(diff==0) {
            return 0;
        }
        return -1;
    }
    private int compareCards(char[] l1, char[] l2) {
        int i=0;
        while (i<Math.min(l1.length, l2.length)) {
            int priority = computeSecondPriority(l1[i], l2[i]);
            if (priority != 0) {
                return priority;
            }
            i++;
        }
        if(l1.length==l2.length) return 0;
        if(i==l1.length) return -1;
        return 1;
    }
    @Override
    public int compare(Play o1, Play o2) {
        int nameCompare = o1.getHand().compareTo(o2.getHand());
        if(nameCompare != 0) {
            return nameCompare;
        }
        return compareCards(o1.getCards(), o2.getCards());
    }
}
