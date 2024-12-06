package net.nickreuter.Day5;

import net.nickreuter.Day4.Day4;

import java.util.*;

public class Day5 {
    public static void main(String[] args) {
        var update = parseInput();
        System.out.println(part1(update));
    }

    private static int part1(BookUpdate bookUpdate) {
        var total = 0;
        for(var update : bookUpdate.updates()) {
            var rules = bookUpdate.getRulesForPages(update.pages());
            if(isInOrder(update.pages(), rules)) {
                total += update.getMiddlePage();
            }
        }
        return total;
    }

    private static boolean isInOrder(List<Integer> pages, List<OrderingRule> rules) {
        var placedPages = new ArrayList<>();
        for(var page : pages) {
            var pageRules = rules.stream().filter(orderingRule -> orderingRule.isForTarget(page)).toList();
            for(var rule : pageRules) {
                if(placedPages.contains(rule.beforePage())) {
                    return false;
                }
            }
            placedPages.add(page);
        }
        return true;
    }

    private static BookUpdate parseInput() {
        var input = new Scanner(Objects.requireNonNull(Day4.class.getResourceAsStream("/Day5/input.txt")));
        var rules = new ArrayList<OrderingRule>();
        var updates = new ArrayList<Update>();
        while (input.hasNextLine()) {
            var line = input.nextLine();
            if (line.contains("|")) {
                var ruleParts = line.split("\\|");
                rules.add(new OrderingRule(Integer.parseInt(ruleParts[0]), Integer.parseInt(ruleParts[1])));
            }
            if (line.contains(",")) {
                updates.add(new Update(Arrays.stream(line.split(",")).map(Integer::parseInt).toList()));
            }
        }
        return new BookUpdate(rules, updates);
    }
}
