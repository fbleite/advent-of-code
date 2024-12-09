import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Day4Test {

    @Test
    void parseInput() {
        var input = """
                MMMSXXMASM
                MSAMXMSMSA
                AMXSXMAAMM
                MSAMASMSMX
                XMASAMXAMM
                XXAMMXXAMA
                SMSMSASXSS
                SAXAMASAAA
                MAMMMXMMMM
                MXMXAXMASX
                """;
        var expected = new char[][]{
                {'M', 'M', 'M', 'S', 'X', 'X', 'M', 'A', 'S', 'M', },
                {'M', 'S', 'A', 'M', 'X', 'M', 'S', 'M', 'S', 'A', },
                {'A', 'M', 'X', 'S', 'X', 'M', 'A', 'A', 'M', 'M', },
                {'M', 'S', 'A', 'M', 'A', 'S', 'M', 'S', 'M', 'X', },
                {'X', 'M', 'A', 'S', 'A', 'M', 'X', 'A', 'M', 'M', },
                {'X', 'X', 'A', 'M', 'M', 'X', 'X', 'A', 'M', 'A', },
                {'S', 'M', 'S', 'M', 'S', 'A', 'S', 'X', 'S', 'S', },
                {'S', 'A', 'X', 'A', 'M', 'A', 'S', 'A', 'A', 'A', },
                {'M', 'A', 'M', 'M', 'M', 'X', 'M', 'M', 'M', 'M', },
                {'M', 'X', 'M', 'X', 'A', 'X', 'M', 'A', 'S', 'X', },
        };
        assertArrayEquals(expected, Day4.parseInput(input));
    }

    @Test
    void countXmas() {
        var input = """
                MMMSXXMASM
                MSAMXMSMSA
                AMXSXMAAMM
                MSAMASMSMX
                XMASAMXAMM
                XXAMMXXAMA
                SMSMSASXSS
                SAXAMASAAA
                MAMMMXMMMM
                MXMXAXMASX
                """;
        var parsedInput = Day4.parseInput(input);
        assertEquals(18, Day4.countXmas(parsedInput));
    }

    @Test
    void isXmas() {
        var input = """
                MMMSXXMASM
                MSAMXMSMSA
                AMXSXMAAMM
                MSAMASMSMX
                XMASAMXAMM
                XXAMMXXAMA
                SMSMSASXSS
                SAXAMASAAA
                MAMMMXMMMM
                MXMXAXMASX
                """;
        var parsedInput = Day4.parseInput(input);
        var spots = new int[][]{{0, 5},{0, 6},{0, 7},{0, 8}};
        assertTrue(Day4.isXmas(spots, parsedInput));


        spots = new int[][]{{0, 4},{0, 5},{0, 6},{0, 7}};
        assertFalse(Day4.isXmas(spots, parsedInput));
    }


    @Test
    void isX_mas() {
        var input = """
                MMMSXXMASM
                MSAMXMSMSA
                AMXSXMAAMM
                MSAMASMSMX
                XMASAMXAMM
                XXAMMXXAMA
                SMSMSASXSS
                SAXAMASAAA
                MAMMMXMMMM
                MXMXAXMASX
                """;
        var parsedInput = Day4.parseInput(input);
        assertTrue(Day4.isX_mas(new int[]{1, 2}, parsedInput));
        assertFalse(Day4.isX_mas(new int[]{1, 3}, parsedInput));
    }

    @Test
    void countX_mas() {
        var input = """
                MMMSXXMASM
                MSAMXMSMSA
                AMXSXMAAMM
                MSAMASMSMX
                XMASAMXAMM
                XXAMMXXAMA
                SMSMSASXSS
                SAXAMASAAA
                MAMMMXMMMM
                MXMXAXMASX
                """;
        var parsedInput = Day4.parseInput(input);
        assertEquals(9, Day4.countX_mas(parsedInput));
    }
}