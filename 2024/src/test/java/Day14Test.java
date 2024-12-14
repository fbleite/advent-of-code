import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class Day14Test {

    @Test
    void parseInput() {
        var input = """
                p=0,4 v=3,-3
                p=6,3 v=-1,-3
                p=10,3 v=-1,2
                p=2,0 v=2,-1
                """;
        var robots = Day14.parseInput(input);
        var expected = List.of(
                new Day14.Robot(new Coordinate(0, 4), new Coordinate(3, -3)),
                new Day14.Robot(new Coordinate(6, 3), new Coordinate(-1, -3)),
                new Day14.Robot(new Coordinate(10, 3), new Coordinate(-1, 2)),
                new Day14.Robot(new Coordinate(2, 0), new Coordinate(2, -1))
        );
        assertIterableEquals(expected, robots);
    }


    @Test
    void calculateNewPosition() {
        var input = """
                p=2,4 v=2,-3
                """;
        var robots = Day14.parseInput(input);
        var movedRobot = Day14.calculateNewPosition(robots.get(0), new Coordinate(11, 7), 5);
        assertEquals(new Coordinate(1, 3), movedRobot.position());
    }
    @Test
    void calculateNewPosition_bug() {
        var input = """
                p=7,6 v=-1,-3
                """;
        var robots = Day14.parseInput(input);
        var maxGrid = new Coordinate(11, 7);
        assertEquals(new Coordinate(6, 3),
                Day14.calculateNewPosition(robots.get(0), maxGrid, 1).position());
        assertEquals(new Coordinate(5, 0),
                Day14.calculateNewPosition(robots.get(0), maxGrid, 2).position());
    }

    @Test
    void calculateSafetyFactor() {
        var input = """
                p=0,4 v=3,-3
                p=6,3 v=-1,-3
                p=10,3 v=-1,2
                p=2,0 v=2,-1
                p=0,0 v=1,3
                p=3,0 v=-2,-2
                p=7,6 v=-1,-3
                p=3,0 v=-1,-2
                p=9,3 v=2,3
                p=7,3 v=-1,2
                p=2,4 v=2,-3
                p=9,5 v=-3,-3
                """;
        var robots = Day14.parseInput(input);
        var safetyFactor = Day14.calculateSafetyFactor(robots, new Coordinate(11, 7), 100);
        assertEquals(12, safetyFactor);
    }

    @Test
    void printRobots() {

        var input = """
                p=0,4 v=3,-3
                p=6,3 v=-1,-3
                p=10,3 v=-1,2
                p=2,0 v=2,-1
                p=0,0 v=1,3
                p=3,0 v=-2,-2
                p=7,6 v=-1,-3
                p=3,0 v=-1,-2
                p=9,3 v=2,3
                p=7,3 v=-1,2
                p=2,4 v=2,-3
                p=9,5 v=-3,-3
                """;
        var robots = Day14.parseInput(input);
        Day14.moveAndPrint(robots, new Coordinate(11, 7), 100000);
    }
}