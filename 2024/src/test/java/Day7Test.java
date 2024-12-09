import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

class Day7Test {

    @Test
    void parseInput() {
        var input = """
                190: 10 19
                3267: 81 40 27
                83: 17 5
                156: 15 6
                7290: 6 8 6 15
                161011: 16 10 13
                192: 17 8 14
                21037: 9 7 18 13
                292: 11 6 16 20
                """;
        var parsedInput = Day7.parseInput(input);
        var expected = List.of(
                new Day7.Calibration(190L, List.of(10L, 19L)),
                new Day7.Calibration(3267L, List.of(81L, 40L, 27L)),
                new Day7.Calibration(83L, List.of(17L, 5L)),
                new Day7.Calibration(156L, List.of(15L, 6L)),
                new Day7.Calibration(7290L, List.of(6L, 8L, 6L, 15L)),
                new Day7.Calibration(161011L, List.of(16L, 10L, 13L)),
                new Day7.Calibration(192L, List.of(17L, 8L, 14L)),
                new Day7.Calibration(21037L, List.of(9L, 7L, 18L, 13L)),
                new Day7.Calibration(292L, List.of(11L, 6L, 16L, 20L))
        );
        assertEquals(expected, parsedInput);

    }

    @Test
    void sumOfValidEquations() {
        var input = """
                190: 10 19
                3267: 81 40 27
                83: 17 5
                156: 15 6
                7290: 6 8 6 15
                161011: 16 10 13
                192: 17 8 14
                21037: 9 7 18 13
                292: 11 6 16 20
                """;
        var parsedInput = Day7.parseInput(input);
        assertEquals(3749, Day7.sumOfValidEquations(parsedInput));
    }

    @Test
    void sumOfValidEquationsWithConcat() {
        var input = """
                190: 10 19
                3267: 81 40 27
                83: 17 5
                156: 15 6
                7290: 6 8 6 15
                161011: 16 10 13
                192: 17 8 14
                21037: 9 7 18 13
                292: 11 6 16 20
                """;
        var parsedInput = Day7.parseInput(input);
        assertEquals(11387, Day7.sumOfValidEquationsWithConcat(parsedInput));
    }

    @Test
    void isValidEquationWithConcat() {
        assertTrue(Day7.isValidEquationWithConcat(new Day7.Calibration(123L, List.of(1L, 2L, 3L))));
        assertTrue(Day7.isValidEquationWithConcat(new Day7.Calibration(6L, List.of(1L, 2L, 3L))));
        assertTrue(Day7.isValidEquationWithConcat(new Day7.Calibration(15L, List.of(1L, 2L, 3L))));
        assertTrue(Day7.isValidEquationWithConcat(new Day7.Calibration(9L, List.of(1L, 2L, 3L))));
        assertTrue(Day7.isValidEquationWithConcat(new Day7.Calibration(36L, List.of(1L, 2L, 3L))));
        assertTrue(Day7.isValidEquationWithConcat(new Day7.Calibration(5L, List.of(1L, 2L, 3L))));
        assertTrue(Day7.isValidEquationWithConcat(new Day7.Calibration(23L, List.of(1L, 2L, 3L))));
        assertTrue(Day7.isValidEquationWithConcat(new Day7.Calibration(15L, List.of(1L, 2L, 3L))));
        assertTrue(Day7.isValidEquationWithConcat(new Day7.Calibration(36L, List.of(1L, 2L, 3L))));

//        assertFalse(Day7.isValidEquationWithConcat(new Day7.Calibration(100L, List.of(50L, 50L, 2L))));
        assertTrue(Day7.isValidEquationWithConcat(new Day7.Calibration(5050L, List.of(50L, 50L))));
    }
}