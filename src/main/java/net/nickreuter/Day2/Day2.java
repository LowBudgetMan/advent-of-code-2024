package net.nickreuter.Day2;

import net.nickreuter.Day1.Day1;

import java.util.*;

public class Day2 {
    public static void main(String[] args) {
        var reports = parseInput();
        System.out.println(part1(reports));
    }

    private static int part1(List<Report> reports) {
        var totalSafeReports = 0;
        for (Report report : reports) {
            var isSafe = true;
            var increasing = report.levels().get(1) - report.levels().get(0) > 0;

            for (int i = 0; i < report.levels().size() - 1; i++) {
                var levelDiff = Math.abs(report.levels().get(i + 1) - report.levels().get(i));
                var localIncreasing = report.levels().get(i + 1) - report.levels().get(i) > 0;
                if(levelDiff > 3 || levelDiff < 1 || localIncreasing != increasing) {
                    isSafe = false;
                    break;
                }
            }

            if(isSafe) {
                totalSafeReports++;
            }
        }
        return totalSafeReports;
    }

    private static List<Report> parseInput() {
        var input = new Scanner(Objects.requireNonNull(Day1.class.getResourceAsStream("/Day2/input.txt")));
        var reports = new ArrayList<Report>();
        while (input.hasNextLine()) {
            var levels = Arrays.stream(input.nextLine()
                    .split(" "))
                    .map(Integer::parseInt)
                    .toList();
            reports.add(new Report(levels));
        }
        return reports;
    }
}
