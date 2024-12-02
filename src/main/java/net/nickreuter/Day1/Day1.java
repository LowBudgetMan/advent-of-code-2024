package net.nickreuter.Day1;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Day1 {
    public static void main(String[] args) {
        System.out.println("Hello, world!");
        var input = parseInput();
        var left = input.get(0);
        var right = input.get(1);

        System.out.println(part1(left, right));
        System.out.println(part2(left, right));
    }

    public static int part1(List<Integer> left, List<Integer> right) {
        left.sort(Integer::compareTo);
        right.sort(Integer::compareTo);

        int totalDiff = 0;
        for(int i = 0; i < left.size(); i++) {
            totalDiff += Math.abs(left.get(i) - right.get(i));
        }

        return totalDiff;
    }

    public static int part2(List<Integer> left, List<Integer> right) {
        int totalDiff = 0;
        for (var value : left) {
            totalDiff += (int) right.stream().filter(value::equals).count() * value;
        }

        return totalDiff;
    }

    private static List<List<Integer>> parseInput() {
        var input = new Scanner(Objects.requireNonNull(Day1.class.getResourceAsStream("/Day1/input.txt")));
        var left = new ArrayList<Integer>();
        var right = new ArrayList<Integer>();

        while(input.hasNext()) {
            var pieces = input.nextLine().split(" {3}");
            left.add(Integer.parseInt(pieces[0]));
            right.add(Integer.parseInt(pieces[1]));
        }

        return List.of(left, right);
    }
}
