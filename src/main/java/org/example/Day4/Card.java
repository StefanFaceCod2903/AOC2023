package org.example.Day4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Card {
    private List<Integer> winning;
    private List<Integer> numbers;
    private Integer id;

    private Long matching;
    public Card(String cardString) {
        this.winning = new ArrayList<>();
        this.numbers = new ArrayList<>();

        String[] splitted = cardString.split(":\\s+");
        this.id = Integer.valueOf(splitted[0].split("\\s+")[1]);
        String winningString = splitted[1].split("\\s+\\|\\s+")[0];
        String numbersString = splitted[1].split("\\s+\\|\\s+")[1];
        List<Integer> winning = Arrays.stream(winningString.split("\\s+")).map(Integer::valueOf).toList();
        this.matching = Arrays.stream(numbersString.split("\\s+")).map(Integer::valueOf).filter(winning::contains).count();
    }

    public Long getMatching() {
        return matching;
    }

    public Long getScore() {
        return 1L << (this.matching - 1);
    }

    public Integer getId() {
        return id;
    }
}
