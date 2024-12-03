package net.nickreuter.Day3;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.regex.Pattern;

public class Day3 {
    public static void main(String[] args) throws IOException {
        var multiplyOperations = parseInput();
        System.out.println(part1(multiplyOperations));
        System.out.println(part2(multiplyOperations));
    }

    private static int part1(String input) {
        var pattern = Pattern.compile("mul\\(([0-9]{1,3},[0-9]{1,3})\\)");
        var matcher = pattern.matcher(input);
        var operations = new ArrayList<MultOperation>();
        while(matcher.find()) {
            var params = matcher.group(1).split(",");
            operations.add(new MultOperation(Integer.parseInt(params[0]), Integer.parseInt(params[1])));
        }
        return operations.stream().reduce(0, (subtotal, element) -> subtotal + element.multiply(), Integer::sum);
    }

    private static int part2(String input) {
        var pattern = Pattern.compile("mul\\(([0-9]{1,3},[0-9]{1,3})\\)|don't\\(\\)|do\\(\\)");
        var matcher = pattern.matcher(input);
        var operations = new ArrayList<MultOperation>();
        var operationsEnabled = true;
        while(matcher.find()) {
            switch(matcher.group()) {
                case "don't()" -> operationsEnabled = false;
                case "do()" -> operationsEnabled = true;
                default -> {
                    if(operationsEnabled) {
                        var params = matcher.group(1).split(",");
                        operations.add(new MultOperation(Integer.parseInt(params[0]), Integer.parseInt(params[1])));
                    }
                }
            }
        }

        return operations.stream().reduce(0, (subtotal, element) -> subtotal + element.multiply(), Integer::sum);
    }

    private static String parseInput() throws IOException {
        try(var file = Day3.class.getResourceAsStream("/Day3/input.txt")) {
            return new String(Objects.requireNonNull(file).readAllBytes());
        }
    }
}
