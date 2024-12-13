import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class Day10Test {

    @Test
    void parseInput() {
        var input = """
                0123
                1234
                8765
                9876
                """;
        var actual = Day10.parseInput(input);
        var expected = new int [][] {
                {0,1,2,3},
                {1,2,3,4},
                {8,7,6,5},
                {9,8,7,6}
        };
        assertArrayEquals(expected, actual);
    }


    @Test
    void getTrailheadTops() {
        var input = """
                0123
                1234
                8765
                9876
                """;
        var map = Day10.parseInput(input);
        Map<Coordinate, Integer> tops = new HashMap<>();
        Day10.getTrailheadTops(0, 0, map, tops);
        assertEquals(1, tops.size());
    }

    @Test
    void getTrailheadTops_multiple() {
        var input = """
1110111
1111111
1112111
6543456
7111117
8111118
9111119
                """;
        var map = Day10.parseInput(input);
        Map<Coordinate, Integer> tops = new HashMap<>();
        Day10.getTrailheadTops(0, 3, map, tops);
        assertEquals(2, tops.size());
    }

    @Test
    void calculateMapScore() {
        var input = """
89010123
78121874
87430965
96549874
45678903
32019012
01329801
10456732
                """;
        var map = Day10.parseInput(input);
        assertEquals(36, Day10.calculateMapScore(map, false));
    }

    @Test
    void calculateMapScore_multiplePath() {
        var input = """
89010123
78121874
87430965
96549874
45678903
32019012
01329801
10456732
                """;
        var map = Day10.parseInput(input);
        assertEquals(81, Day10.calculateMapScore(map, true));
    }
}