package net.nickreuter.Day3;

public record MultOperation(int value1, int value2) {
    public int multiply() {
        return value1 * value2;
    }
}
