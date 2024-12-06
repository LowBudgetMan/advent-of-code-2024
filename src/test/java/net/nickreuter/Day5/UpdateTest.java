package net.nickreuter.Day5;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UpdateTest {
    @Test
    void getMiddlePage() {
        var input = new Update(List.of(1,2,3,4,5));
        assertEquals(3, input.getMiddlePage());
    }

}