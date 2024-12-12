import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Day12Test {

    @Test
    void priceForRegion() {
        var input = """
                AAAA
                BBCD
                BBCC
                EEEC
                """;
        var map = Day12.parseInput(input);
        var visited = new boolean[map.length][map[0].length];
        assertEquals(40, Day12.priceForRegion(map, 0, 0, visited));
        assertEquals(32, Day12.priceForRegion(map, 1, 0, visited));
        assertEquals(40, Day12.priceForRegion(map, 1, 2, visited));
        assertEquals(4, Day12.priceForRegion(map, 1, 3, visited));
        assertEquals(24, Day12.priceForRegion(map, 3, 0, visited));
    }

    @Test
    void priceForRegionCheaper() {
        var input = """
                AAAA
                BBCD
                BBCC
                EEEC
                """;
        var map = Day12.parseInput(input);
        var visited = new boolean[map.length][map[0].length];
        assertEquals(16, Day12.priceForRegion(map, 0, 0, visited, true));
        assertEquals(16, Day12.priceForRegion(map, 1, 0, visited, true));
        assertEquals(32, Day12.priceForRegion(map, 1, 2, visited, true));
        assertEquals(4, Day12.priceForRegion(map, 1, 3, visited, true));
        assertEquals(12, Day12.priceForRegion(map, 3, 0, visited, true));
    }

    @Test
    void calculateTotalPrice() {
        var input = """
                AAAA
                BBCD
                BBCC
                EEEC
                """;
        var map = Day12.parseInput(input);
        assertEquals(140, Day12.calculateTotalPrice(map, false));
    }

    @Test
    void calculateTotalPriceCheaper() {
        var input = """
                AAAA
                BBCD
                BBCC
                EEEC
                """;
        var map = Day12.parseInput(input);
        assertEquals(80, Day12.calculateTotalPrice(map, true));
    }

    @Test
    void calculateTotalPriceCheaper_X() {
        var input = """
EEEEE
EXXXX
EEEEE
EXXXX
EEEEE
                """;
        var map = Day12.parseInput(input);
        assertEquals(236, Day12.calculateTotalPrice(map, true));
    }

    @Test
    void calculateTotalPriceCheaper_ABBA() {
        var input = """
AAAAAA
AAABBA
AAABBA
ABBAAA
ABBAAA
AAAAAA
                """;
        var map = Day12.parseInput(input);
        assertEquals(368, Day12.calculateTotalPrice(map, true));
    }


    @Test
    void calculateTotalPriceCheaper_large() {
        var input = """
RRRRIICCFF
RRRRIICCCF
VVRRRCCFFF
VVRCCCJFFF
VVVVCJJCFE
VVIVCCJJEE
VVIIICJJEE
MIIIIIJJEE
MIIISIJEEE
MMMISSJEEE
                """;
        var map = Day12.parseInput(input);
        assertEquals(1206, Day12.calculateTotalPrice(map, true));
    }

    @Test
    void calculateTotalPrice_X() {
        var input = """
OOOOO
OXOXO
OOOOO
OXOXO
OOOOO
                """;
        var map = Day12.parseInput(input);
        assertEquals(772, Day12.calculateTotalPrice(map, false));
    }

    @Test
    void calculateTotalPrice_large() {
        var input = """
RRRRIICCFF
RRRRIICCCF
VVRRRCCFFF
VVRCCCJFFF
VVVVCJJCFE
VVIVCCJJEE
VVIIICJJEE
MIIIIIJJEE
MIIISIJEEE
MMMISSJEEE
                """;
        var map = Day12.parseInput(input);
        assertEquals(1930, Day12.calculateTotalPrice(map, false));
    }
}