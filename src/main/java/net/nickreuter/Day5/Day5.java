package net.nickreuter.Day5;

import net.nickreuter.Day4.Day4;

import java.util.*;

public class Day5 {
    public static void main(String[] args) {
        var update = parseInput();
        System.out.println(part1(update));
        System.out.println(part2(update));
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

    protected static int part2(BookUpdate bookUpdate) {
        var total = 0;
        for(var update : bookUpdate.updates()) {
            if(!isInOrder(update.pages(), bookUpdate.getRulesForPages(update.pages()))) {
                var reorderedUpdate = new Update(reorderPages(update.pages(), bookUpdate.getRulesForPages(update.pages())));
                total += reorderedUpdate.getMiddlePage();
            }
        }
        return total;
    }

    private static List<Integer> reorderPages(List<Integer> pages, List<OrderingRule> rules) {
        var inOrder = true;
        var placedPages = new ArrayList<Integer>();
        for(var page : pages) {
            var placed = false;
            var pageRules = rules.stream().filter(orderingRule -> orderingRule.isForTarget(page)).toList();
            for(var rule : pageRules) {
                if(placedPages.contains(rule.beforePage())) {
                    placedPages.add(placedPages.indexOf(rule.beforePage()), rule.target());
                    placed = true;
                    break;
                }
            }
            if(!placed) {
                placedPages.add(page);
            } else {
                inOrder = false;
            }
        }

        if(inOrder) {
            return placedPages;
        } else {
            return reorderPages(placedPages, rules);
        }
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
