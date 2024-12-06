package net.nickreuter.Day5;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class Day5Test {
    @Test
    void part2() {
        var rules = List.of(
                new OrderingRule(47,53),
                new OrderingRule(97,13),
                new OrderingRule(97,61),
                new OrderingRule(97,47),
                new OrderingRule(75,29),
                new OrderingRule(61,13),
                new OrderingRule(75,53),
                new OrderingRule(29,13),
                new OrderingRule(97,29),
                new OrderingRule(53,29),
                new OrderingRule(61,53),
                new OrderingRule(97,53),
                new OrderingRule(61,29),
                new OrderingRule(47,13),
                new OrderingRule(75,47),
                new OrderingRule(97,75),
                new OrderingRule(47,61),
                new OrderingRule(75,61),
                new OrderingRule(47,29),
                new OrderingRule(75,13),
                new OrderingRule(53,13)
        );

        var updates = List.of(
                new Update(List.of(75,47,61,53,29)),
                new Update(List.of(97,61,53,29,13)),
                new Update(List.of(75,29,13)),
                new Update(List.of(75,97,47,61,53)),
                new Update(List.of(61,13,29)),
                new Update(List.of(97,13,75,29,47))
        );

        var bookUpdate = new BookUpdate(rules, updates);
        Day5.part2(bookUpdate);
    }
}