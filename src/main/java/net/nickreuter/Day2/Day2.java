package net.nickreuter.Day2;

import java.util.*;

public class Day2 {
    public static void main(String[] args) {
        var reports = parseInput();
        System.out.println(part1(reports));
        System.out.println(part2(reports));
    }

    private static int part1(List<Report> reports) {
        var totalSafeReports = 0;
        for (Report report : reports) {
            if(isReportSafe(report.levels())) {
                totalSafeReports++;
            }
        }
        return totalSafeReports;
    }

    private static int part2(List<Report> reports) {
        var totalSafeReports = 0;
        for (Report report : reports) {
            if(isReportSafe(report.levels())) {
                totalSafeReports++;
            } else {
                for (int i = 0; i < report.levels().size(); i++) {
                    var dampenedLevels = new ArrayList<>(report.levels());
                    dampenedLevels.remove(i);
                    if(isReportSafe(dampenedLevels)) {
                        totalSafeReports++;
                        break;
                    }
                }
            }
        }
        return totalSafeReports;
    }

    private static boolean isReportSafe(List<Integer> levels) {
        var increasing = levels.get(1) - levels.get(0) > 0;
        for (int i = 0; i < levels.size() - 1; i++) {
            var levelDiff = Math.abs(levels.get(i + 1) - levels.get(i));
            var localIncreasing = levels.get(i + 1) - levels.get(i) > 0;
            if(levelDiff > 3 || levelDiff < 1 || localIncreasing != increasing) {
                return false;
            }
        }
        return true;
    }

    private static List<Report> parseInput() {
        var input = new Scanner(Objects.requireNonNull(Day2.class.getResourceAsStream("/Day2/input.txt")));
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
