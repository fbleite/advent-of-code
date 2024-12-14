import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Day14 {
    record Robot(Coordinate position, Coordinate speed) {
    }

    public static void main(String[] args) throws URISyntaxException, IOException {
        var input = FileUtil.getFileString("Day14.txt");
        var robots = parseInput(input);
        Coordinate maxGrid = new Coordinate(101, 103);
        System.out.println(calculateSafetyFactor(robots, maxGrid, 100));
        moveAndPrint(robots, maxGrid, 100000);
    }

    public static List<Robot> parseInput(String input) {
        return input.lines()
                .filter(l -> !l.isBlank())
                .map(Day14::lineToRobot).toList();
    }

    private static Robot lineToRobot(String line) {
        Pattern pattern = Pattern.compile("p=([0-9]+),([0-9]+) v=(-?[0-9]+),(-?[0-9]+)");
        var matcher = pattern.matcher(line);
        if (matcher.find()) {
            var position = new Coordinate(Integer.valueOf(matcher.group(1)), Integer.valueOf(matcher.group(2)));
            var speed = new Coordinate(Integer.valueOf(matcher.group(3)), Integer.valueOf(matcher.group(4)));
            return new Robot(position, speed);
        } else {
            throw new RuntimeException("Pattern didn't match");
        }
    }


    public static Long calculateSafetyFactor(List<Robot> robots, Coordinate maxGrid, int numberOfMoves) {
        return robots.stream()
                .map(r -> calculateNewPosition(r, maxGrid, numberOfMoves))
                .map(r -> quadrantIndex(r, maxGrid))
                .filter(q -> q >= 0)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .values().stream().reduce(1L, (a, b) -> a * b);
    }

    public static Robot calculateNewPosition(Robot robot, Coordinate maxGrid, int numberOfMoves) {
        return new Robot(new Coordinate(
                calculateOneAxis(robot.position.i(), robot.speed.i(), maxGrid.i(), numberOfMoves),
                calculateOneAxis(robot.position.j(), robot.speed.j(), maxGrid.j(), numberOfMoves)
        ), robot.speed);
    }

    private static int calculateOneAxis(int position, int speed, int max, int numberOfMoves) {
        if (speed >= 0) {
            return (position + speed * numberOfMoves) % max;
        } else {
            var pos = max - ((max - position + Math.abs(speed) * numberOfMoves) % max);
            if (pos == max) {
                return 0;
            }
            return pos;
        }
    }

    private static int quadrantIndex(Robot robot, Coordinate maxGrid) {
        int middleI = maxGrid.i() / 2;
        int middleJ = maxGrid.j() / 2;

        if (robot.position.i() < middleI && robot.position.j() < middleJ) {
            return 0;
        } else if (robot.position.i() > middleI && robot.position.j() < middleJ) {
            return 1;
        } else if (robot.position.i() < middleI && robot.position.j() > middleJ) {
            return 2;
        } else if (robot.position.i() > middleI && robot.position.j() > middleJ) {
            return 3;
        } else {
            return -1;
        }
    }

    public static void moveAndPrint(List<Robot> robots, Coordinate maxGrid, int maxIterations) {
        for (int i = 0; i < maxIterations; i++) {
            robots = robots.stream()
                    .map(r -> calculateNewPosition(r, maxGrid, 1))
                    .collect(Collectors.toList());
            printRobots(robots, maxGrid, i);
        }
    }

    public static void printRobots(List<Robot> robots, Coordinate maxGrid, int iteration) {
        char[][] grid = new char[maxGrid.i()][maxGrid.j()];
        for (var robot : robots) {
            grid[robot.position.i()][robot.position.j()] = '*';
        }
        var sb = new StringBuilder();
        var maxRobotRowCount = 0;
        for (int i = 0; i < grid.length; i++) {
            int currRobotCount = 0;
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '*') {
                    currRobotCount++;
                    sb.append('O');
                } else {
                    currRobotCount = 0;
                    sb.append('.');
                }
                maxRobotRowCount = Math.max(maxRobotRowCount, currRobotCount);
            }
            sb.append("\n");
        }
        if (maxRobotRowCount > 10) {
            System.out.println("Iteration: " + iteration);
            System.out.println("--------------------------------");
            System.out.println(sb);
            System.out.println("--------------------------------");
        }
    }
}
