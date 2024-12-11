import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class Day11Test {

    @Test
    void parseInput() {
        var input = "125 17";
        var expected = List.of(125L, 17L);
        assertEquals(expected, Day11.parseInput(input));
    }

    @Test
    void blink() {
        var input = "125 17";
        var stones = Day11.parseInput(input);
        var blinked1 = Day11.blink(stones);
        assertIterableEquals(List.of(253000L, 1L, 7L), blinked1);
    }

    @Test
    void blinkNTimes() {
        var input = "125 17";
        var stones = Day11.parseInput(input);
        var blinkedNTimes = Day11.blinkNTimes(stones, 6);
        assertIterableEquals(List.of(2097446912L,  14168L,  4048L,  2L,  0L,  2L,  4L,  40L,  48L,  2024L,  40L,  48L,  80L,  96L,  2L,  8L,  6L,  7L,  6L,  0L,  3L,  2L), blinkedNTimes);
    }

    @Test
    void countStonesAfterNBlinks() {
        var input = "125 17";
        var stones = Day11.parseInput(input);
        assertEquals(22, Day11.countStonesAfterNBlinks(stones, 6));
        assertEquals(55312, Day11.countStonesAfterNBlinks(stones, 25));
    }

    @Test
    void countStonesAfterNBlinks_fast() {
        var input = "125 17";
        var stones = Day11.parseInput(input);
        assertEquals(22, Day11.countStonesAfterNBlinks_fast(stones, 6));
        assertEquals(55312, Day11.countStonesAfterNBlinks_fast(stones, 25));
    }


}