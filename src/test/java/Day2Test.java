import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
class Day2Test {

    @Test
    void parseInput() {
        var input = """
                7 6 4 2 1
                1 2 7 8 9
                9 7 6 2 1
                1 3 2 4 5
                8 6 4 4 1
                1 3 6 7 9
                """;

        var actual = Day2.parseInput(input);
        var expected = List.of(
                List.of(7, 6, 4, 2, 1),
                List.of(1, 2, 7, 8, 9),
                List.of(9, 7, 6, 2, 1),
                List.of(1, 3, 2, 4, 5),
                List.of(8, 6, 4, 4, 1),
                List.of(1, 3, 6, 7, 9));
        assertEquals(expected, actual);
    }

    @Test
    void isSafe() {
        assertEquals(-1, Day2.isSafe(List.of(7, 6, 4, 2, 1)));
        assertEquals(2, Day2.isSafe(List.of(1, 2, 7, 8, 9)));
        assertEquals(3, Day2.isSafe(List.of(9, 7, 6, 2, 1)));
        assertEquals(2, Day2.isSafe(List.of(1, 3, 2, 4, 5)));
        assertEquals(3, Day2.isSafe(List.of(8, 6, 4, 4, 1)));
        assertEquals(-1, Day2.isSafe(List.of(1, 3, 6, 7, 9)));
    }

    @Test
    void countSafeReports() {
        var input = """
                7 6 4 2 1
                1 2 7 8 9
                9 7 6 2 1
                1 3 2 4 5
                8 6 4 4 1
                1 3 6 7 9
                """;
        assertEquals(2L, Day2.countSafeReports(input));
    }


    @Test
    void isDampenerSafe() {
//        assertTrue(Day2.isDampenerSafe(List.of(7, 6, 4, 2, 1)));
//        assertFalse(Day2.isDampenerSafe(List.of(1, 2, 7, 8, 9)));
//        assertFalse(Day2.isDampenerSafe(List.of(9, 7, 6, 2, 1)));
//        assertTrue(Day2.isDampenerSafe(List.of(1, 3, 2, 4, 5)));
//        assertTrue(Day2.isDampenerSafe(List.of(8, 6, 4, 4, 1)));
//        assertTrue(Day2.isDampenerSafe(List.of(1, 3, 6, 7, 9)));
        assertTrue(Day2.isDampenerSafe(List.of(0, 5, 6, 7, 9)));

    }
}