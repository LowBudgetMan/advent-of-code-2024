package net.nickreuter.Day5;

import java.util.List;

public record Update(List<Integer> pages) {
    public int getMiddlePage() {
        return pages.get(pages.size() / 2);
    }
}
