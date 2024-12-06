package net.nickreuter.Day5;

import java.util.List;

public record BookUpdate(List<OrderingRule> rules, List<Update> updates) {
    List<OrderingRule> getRulesForPages(List<Integer> pages) {
        return rules.stream().filter(orderingRule -> pages.contains(orderingRule.beforePage())).toList();
    }
}
