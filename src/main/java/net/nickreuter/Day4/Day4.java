package net.nickreuter.Day4;

import java.util.*;

public class Day4 {
    public static void main(String[] args) {
        var searchGrid = parseInput();
        System.out.println(part1(searchGrid));
        System.out.println(part2(searchGrid));
    }

    private static int part1(char[][] grid) {
        int matchCount = 0;
        for (int y = 0; y < grid.length; y++) {
            for (int x = 0; x < grid[0].length; x++) {
                if (grid[y][x] == 'X') {
                    matchCount += xmasCount(y, x, grid);
                }
            }
        }
        return matchCount;
    }

    private static int xmasCount(int y, int x, char[][] grid) {
        var matchCount = 0;
        if(directionContainsXmas(y-1, x, grid, 'X', Direction.NORTH)) matchCount++;
        if(directionContainsXmas(y-1, x+1, grid, 'X', Direction.NORTH_EAST)) matchCount++;
        if(directionContainsXmas(y, x+1, grid, 'X', Direction.EAST)) matchCount++;
        if(directionContainsXmas(y+1, x+1, grid, 'X', Direction.SOUTH_EAST)) matchCount++;
        if(directionContainsXmas(y+1, x, grid, 'X', Direction.SOUTH)) matchCount++;
        if(directionContainsXmas(y+1, x-1, grid, 'X', Direction.SOUTH_WEST)) matchCount++;
        if(directionContainsXmas(y, x-1, grid, 'X', Direction.WEST)) matchCount++;
        if(directionContainsXmas(y-1, x-1, grid, 'X', Direction.NORTH_WEST)) matchCount++;
        return matchCount;
    }

    private static boolean directionContainsXmas(int y, int x, char[][] grid, char xmasPartial, Direction direction) {
        if(xmasPartial == 'S') return true;

        char positionChar;
        try {
            positionChar = grid[y][x];
        } catch (ArrayIndexOutOfBoundsException e) {
            return false;
        }

        if(positionContainsCorrectNextCharacter(xmasPartial, positionChar)) {
            return directionContainsXmas(getNewY(y, direction), getNewX(x, direction), grid, positionChar, direction);
        }

        return false;
    }

    private static int getNewY(int y, Direction direction) {
        return switch (direction) {
            case NORTH, NORTH_EAST, NORTH_WEST -> y - 1;
            case SOUTH, SOUTH_EAST, SOUTH_WEST -> y + 1;
            default -> y;
        };
    }

    private static int getNewX(int x, Direction direction) {
        return switch (direction) {
            case EAST, NORTH_EAST, SOUTH_EAST -> x + 1;
            case WEST, NORTH_WEST, SOUTH_WEST -> x - 1;
            default -> x;
        };
    }

    private static boolean positionContainsCorrectNextCharacter(char xmasPartial, char positionChar) {
        return switch (xmasPartial) {
            case 'X' -> positionChar == 'M';
            case 'M' -> positionChar == 'A';
            case 'A' -> positionChar == 'S';
            default -> false;
        };
    }

    private static int part2(char[][] grid) {
        int matchCount = 0;
        for (int y = 0; y < grid.length; y++) {
            for (int x = 0; x < grid[0].length; x++) {
                if (grid[y][x] == 'A' && masCount(y, x, grid)) {
                    matchCount++;
                }
            }
        }
        return matchCount;
    }

    private static boolean masCount(int y, int x, char[][] grid) {
        char northEast;
        char southEast;
        char northWest;
        char southWest;
        try {
            northEast = grid[y-1][x+1];
            southEast = grid[y+1][x+1];
            northWest = grid[y-1][x-1];
            southWest = grid[y+1][x-1];
        } catch (ArrayIndexOutOfBoundsException e) {
            return false;
        }

        return isCorrectPairing(northEast, southWest) && isCorrectPairing(northWest, southEast);
    }

    private static boolean isCorrectPairing(char piece1, char piece2) {
        return switch(piece1) {
            case 'S' -> piece2 == 'M';
            case 'M' -> piece2 == 'S';
            default -> false;
        };
    }

    private static char[][] parseInput() {
        var input = new Scanner(Objects.requireNonNull(Day4.class.getResourceAsStream("/Day4/input.txt")));
        List<char[]> rows = new ArrayList<>();
        while (input.hasNextLine()) {
            rows.add(input.nextLine().toCharArray());
        }
        var grid = new char[rows.size()][rows.getFirst().length];

        return rows.toArray(grid);
    }
}
