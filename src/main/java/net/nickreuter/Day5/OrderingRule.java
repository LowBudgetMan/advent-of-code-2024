package net.nickreuter.Day5;

public record OrderingRule(int target, int beforePage) {
    public boolean isForTarget(int target) {
        return this.target == target;
    }
}
