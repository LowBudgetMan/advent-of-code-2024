package net.nickreuter.Day2;

import net.nickreuter.Day1.Day1;

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

    private static int part2(List<Report> reports) {
        var totalSafeReports = 0;
        System.out.println(reports.size());
        for (Report report : reports) {
            if(isReportSafe(report.levels(), false)) {
                totalSafeReports++;
            }
        }
        return totalSafeReports;
    }

    private static boolean isReportSafe(List<Integer> levels, boolean isLevelRemoved) {
        var isSafe = true;
        var increasing = levels.get(1) - levels.get(0) > 0;
        for (int i = 0; i < levels.size() - 1; i++) {
            var levelDiff = Math.abs(levels.get(i + 1) - levels.get(i));
            var localIncreasing = levels.get(i + 1) - levels.get(i) > 0;
            if(levelDiff > 3 || levelDiff < 1 || localIncreasing != increasing) {
                if (isLevelRemoved) {
                    isSafe = false;
                    break;
                } else {
                    var levelsWithoutNext = new ArrayList<>(levels);
                    levelsWithoutNext.remove(i+1);

                    var levelsWithoutCurrent = new ArrayList<>(levels);
                    levelsWithoutCurrent.remove(i);

                    isSafe = isReportSafe(levelsWithoutNext, true) || isReportSafe(levelsWithoutCurrent, true);
                    break;
                }
            }
        }
        return isSafe;
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
