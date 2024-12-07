package net.nickreuter.Day6;

import java.util.*;

public class Day6 {

    public static void main(String[] args) {
        var grid = parseInput();
        var guard = getGuard(grid);
        System.out.println(part1(guard, grid));
    }

    private static long part1(Guard guard, Tile[][] grid) {
        var map = Arrays.stream(grid).map(Tile[]::clone).toArray(Tile[][]::new);
        moveThroughMaze(guard, grid, map);
        return countVisitedSpacesInGrid(map);
    }

    private static void moveThroughMaze(Guard guard, Tile[][] grid, Tile[][] map) {
        var inMaze = true;
        while (inMaze) {
            try {
                map[guard.getY()][guard.getX()] = Tile.VISITED;
                if (isGuardInFrontOfObject(guard, grid)) {
                    guard.rotate();
                } else {
                    guard.moveForward();
                }
            } catch (IndexOutOfBoundsException e) {
                inMaze = false;
            }
        }
    }

    private static long countVisitedSpacesInGrid(Tile[][] map) {
        var total = 0L;
        total += Arrays.stream(map).flatMap(Arrays::stream).filter(tile -> tile.equals(Tile.VISITED)).count();
        return total;
    }

    private static Guard getGuard(Tile[][] grid) {
        Guard guard = null;
        for (int y = 0; y < grid.length; y++) {
            for (int x = 0; x < grid[0].length; x++) {
                if (grid[y][x] == Tile.GUARD) {
                    guard = new Guard(x, y, Direction.NORTH);
                }
            }
        }
        return guard;
    }

    private static boolean isGuardInFrontOfObject(Guard guard, Tile[][] grid) {
        return switch (guard.getDirection()) {
            case NORTH -> grid[guard.getY() - 1][guard.getX()] == Tile.OBSTACLE;
            case EAST -> grid[guard.getY()][guard.getX() + 1] == Tile.OBSTACLE;
            case SOUTH -> grid[guard.getY() + 1][guard.getX()] == Tile.OBSTACLE;
            case WEST -> grid[guard.getY()][guard.getX() - 1] == Tile.OBSTACLE;
        };
    }

    private static Tile[][] parseInput() {
        var input = new Scanner(Objects.requireNonNull(Day6.class.getResourceAsStream("/Day6/input.txt")));
        List<Tile[]> rows = new ArrayList<>();
        while (input.hasNextLine()) {
            var tileList = input.nextLine().chars().mapToObj(Day6::intToTile).toList();
            rows.add(tileList.toArray(new Tile[0]));
        }
        var grid = new Tile[rows.size()][rows.getFirst().length];

        return rows.toArray(grid);
    }

    private static Tile intToTile(int character) {
        return switch (Character.toChars(character)[0]) {
            case '.' -> Tile.EMPTY;
            case '#' -> Tile.OBSTACLE;
            default -> Tile.GUARD;
        };
    }

    private static void printGrid(Tile[][] grid) {
        for (Tile[] tiles : grid) {
            for (int x = 0; x < grid[0].length; x++) {
                switch (tiles[x]) {
                    case VISITED -> System.out.print("X");
                    case OBSTACLE -> System.out.print("#");
                    case GUARD -> System.out.print("G");
                    case EMPTY -> System.out.print(".");
                }
            }
            System.out.println();
        }
    }
}
