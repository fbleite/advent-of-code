import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Day15 {

    public static void main(String[] args) throws URISyntaxException, IOException {
        var input = FileUtil.getFileString("Day15.txt");
        var md = parseInput(input);
        md = moveAll(md);
        System.out.println(gpsAllBoxes(md.map));

        md = parseInput(input);
        var resizedMap = resizeMap(md.map);
        md = new Day15.MapAndDirections(resizedMap, md.directions(), Day15.findRobot(resizedMap));
        md = moveAll(md);
        System.out.println(gpsAllBoxes(md.map));

    }

    public record MapAndDirections(char[][] map, String directions, Coordinate position) {
    }

    public static MapAndDirections parseInput(String input) {
        var components = input.replaceAll(" ", "").split("\n\n|\r\n\r\n");
        var lines = components[0].split("\n|\r\n");
        var map = new char[lines.length][lines[0].length()];
        Coordinate position = null;
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                map[i][j] = lines[i].charAt(j);
                if (map[i][j] == '@') {
                    position = new Coordinate(i, j);
                }
            }
            map[i] = lines[i].toCharArray();
        }
        return new MapAndDirections(map, components[1].replaceAll("\n|\r\n", ""), position);
    }

    public static Coordinate findRobot(char[][] map) {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if (map[i][j] == '@') {
                    return new Coordinate(i, j);
                }
            }
        }
        throw new RuntimeException("No robot");
    }

    public static char[][] resizeMap(char[][] map) {
        var newMap = new char[map.length][map[0].length * 2];
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if (map[i][j] == '.' || map[i][j] == '#') {
                    newMap[i][j * 2] = map[i][j];
                    newMap[i][j * 2 + 1] = map[i][j];
                } else if (map[i][j] == '@') {
                    newMap[i][j * 2] = map[i][j];
                    newMap[i][j * 2 + 1] = '.';
                } else if (map[i][j] == 'O') {
                    newMap[i][j * 2] = '[';
                    newMap[i][j * 2 + 1] = ']';
                }
            }
        }
        return newMap;
    }

    enum Direction {
        UP('^', new Coordinate(-1, 0)),
        DOWN('v', new Coordinate(1, 0)),
        LEFT('<', new Coordinate(0, -1)),
        RIGHT('>', new Coordinate(0, 1));
        final char symbol;
        final Coordinate coordinate;

        Direction(char symbol, Coordinate coordinate) {
            this.symbol = symbol;
            this.coordinate = coordinate;
        }
    }


    public static long gpsAllBoxes(char[][] map) {
        long sum = 0;
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if (map[i][j] == 'O' || map[i][j] == '[') {
                    sum += ((i * 100) + j);
                }
            }
        }
        return sum;
    }

    public static MapAndDirections moveAll(MapAndDirections md) {
        int countRocks = countRock(md.map);
        while (!md.directions.isBlank()) {
            md = moveDirection(md);
            if (countRocks != countRock(md.map())) {
                System.out.println("Wrong!");
                System.out.println(md.directions.length());
                throw new RuntimeException();
            }
        }
        return md;
    }

    public static int countRock(char[][] map) {
        int count = 0;
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if (map[i][j] == 'O' || map[i][j] == '[') {
                    count++;
                }
            }
        }
        return count;
    }

    public static MapAndDirections moveDirection(MapAndDirections md) {
        var move = md.directions.charAt(0);
        for (var direction : Direction.values()) {
            if (direction.symbol == move) {
                int newRobotI = md.position.i() + direction.coordinate.i();
                int newI = newRobotI;
                int newRobotJ = md.position.j() + direction.coordinate.j();
                int newJ = newRobotJ;
                if (isValid(newI, newJ, md.map)) {
                    if (md.map[newI][newJ] == '.') {
                        md.map[newI][newJ] = '@';
                        md.map[md.position.i()][md.position.j()] = '.';
                        return new MapAndDirections(md.map, md.directions.substring(1), new Coordinate(newI, newJ));
                    }
                    if (md.map[newI][newJ] == '#') {
                        return new MapAndDirections(md.map, md.directions.substring(1), md.position);
                    }
                    if (md.map[newI][newJ] == 'O') {
                        while (isValid(newI, newJ, md.map) && md.map[newI][newJ] != '.' && md.map[newI][newJ] != '#') {
                            newI = newI + direction.coordinate.i();
                            newJ = newJ + direction.coordinate.j();
                        }
                        if (isValid(newI, newJ, md.map)) {
                            if (md.map[newI][newJ] == '.') {
                                md.map[newI][newJ] = 'O';
                                md.map[md.position.i()][md.position.j()] = '.';
                                md.map[newRobotI][newRobotJ] = '@';

                                return new MapAndDirections(md.map, md.directions.substring(1), new Coordinate(newRobotI, newRobotJ));
                            }
                            if (md.map[newI][newJ] == '#') {
                                return new MapAndDirections(md.map, md.directions.substring(1), md.position);
                            }
                        }
                    }

                    if (md.map[newI][newJ] == '[' || md.map[newI][newJ] == ']') {
                        if (Direction.LEFT.equals(direction) || Direction.RIGHT.equals(direction)) {
                            while (isValid(newI, newJ, md.map) &&
                                    (md.map[newI][newJ] == '[' || md.map[newI][newJ] == ']')) {
                                newI = newI + direction.coordinate.i();
                                newJ = newJ + direction.coordinate.j();
                            }
                            if (isValid(newI, newJ, md.map)) {
                                if (md.map[newI][newJ] == '.') {
                                    while (newI != newRobotI || newJ != newRobotJ) {
                                        md.map[newI][newJ] = Direction.LEFT.equals(direction) ? '[' : ']';
                                        newI += (direction.coordinate.i() * -1);
                                        newJ += (direction.coordinate.j() * -1);
                                        md.map[newI][newJ] = Direction.LEFT.equals(direction) ? ']' : '[';
                                        newI += (direction.coordinate.i() * -1);
                                        newJ += (direction.coordinate.j() * -1);
                                    }
                                    md.map[md.position.i()][md.position.j()] = '.';
                                    md.map[newRobotI][newRobotJ] = '@';

                                    return new MapAndDirections(md.map, md.directions.substring(1), new Coordinate(newRobotI, newRobotJ));
                                }
                                if (md.map[newI][newJ] == '#') {
                                    return new MapAndDirections(md.map, md.directions.substring(1), md.position);
                                }
                            }
                        } else {
                            List<Coordinate> toMove = new ArrayList<>();
                            var canMove = canMoveVertical(md.map, md.position.i(), md.position.j(), direction, toMove);
                            if (canMove) {
                                var visited = new boolean[md.map.length][md.map[0].length];
                                moveVertical2(md, direction);
//                                moveVertical(md.map, md.position.i(), md.position.j(), direction, visited, md.position.i(), toMove);
                                return new MapAndDirections(md.map, md.directions.substring(1), new Coordinate(newRobotI, newRobotJ));
                            } else {
                                new MapAndDirections(md.map, md.directions.substring(1), md.position);
                            }
                        }
                    }
                }
            }
        }
        return new MapAndDirections(md.map, md.directions.substring(1), md.position);
    }

    private static boolean canMoveVertical(char[][] map, int i, int j, Direction
            direction, List<Coordinate> toMove) {
        toMove.add(new Coordinate(i, j));
        if (map[i][j] == '.') {
            return true;
        }
        if (map[i][j] == '#') {
            return false;
        }

        if (map[i][j] == '[') {
//            toMove.add(new Coordinate(i, j));
            return canMoveVertical(map, i + direction.coordinate.i(), j, direction, toMove) && canMoveVertical(map, i + direction.coordinate.i(), j + 1, direction, toMove);
        } else if (map[i][j] == ']') {
//            toMove.add(new Coordinate(i, j));
            return canMoveVertical(map, i + direction.coordinate.i(), j, direction, toMove) && canMoveVertical(map, i + direction.coordinate.i(), j - 1, direction, toMove);
        }
        if (map[i][j] == '@') {
//            toMove.add(new Coordinate(i, j));
            return canMoveVertical(map, i + direction.coordinate.i(), j, direction, toMove);
        }
        throw new RuntimeException("What's up?");
    }

    record Block(Coordinate coordinate, char value) {
    }

    private static void moveVertical2(MapAndDirections md, Direction direction) {
        Set<Block> allMovingBlocks = new HashSet<>();
        getAllBlocks(md.map, md.position.i(), md.position.j(), direction, allMovingBlocks);
        for (var block : allMovingBlocks) {
            md.map[block.coordinate.i()][block.coordinate.j()] = '.';
        }

        for (var block : allMovingBlocks) {
            md.map[block.coordinate.i() + direction.coordinate.i()][block.coordinate.j()] = block.value;
        }
    }

    private static void getAllBlocks(char[][] map, int i, int j, Direction
            direction, Set<Block> allMovingBlocks) {
        var curr = map[i][j];

        if (allMovingBlocks.contains(new Block(new Coordinate(i, j), curr))) {
            return;
        }
        if (curr == '@') {
            allMovingBlocks.add(new Block(new Coordinate(i, j), curr));
            getAllBlocks(map, i + direction.coordinate.i(), j, direction, allMovingBlocks);
        }
        if (curr == '[') {
            allMovingBlocks.add(new Block(new Coordinate(i, j), curr));
            getAllBlocks(map, i + direction.coordinate.i(), j, direction, allMovingBlocks);
            getAllBlocks(map, i, j + 1, direction, allMovingBlocks);
        }

        if (curr == ']') {
            allMovingBlocks.add(new Block(new Coordinate(i, j), curr));
            getAllBlocks(map, i + direction.coordinate.i(), j, direction, allMovingBlocks);
            getAllBlocks(map, i, j - 1, direction, allMovingBlocks);
        }
    }

    private static void moveVertical(char[][] map, int i, int j, Direction direction, boolean[][] visited,
                                     int robotI, List<Coordinate> allMoved) {
        if (visited[i][j]) {
            return;
        }
        var curr = map[i][j];
        visited[i][j] = true;
        if (curr == '#' || curr == '.') {
            map[i][j] = map[i + direction.coordinate.i() * -1][j + direction.coordinate.j() * -1];
            return;
//            throw new RuntimeException("Can't move");
        }

        if (curr == '[') {
            moveVertical(map, i + direction.coordinate.i(), j, direction, visited, robotI, allMoved);
            moveVertical(map, i, j + 1, direction, visited, robotI, allMoved);
        } else if (curr == ']') {
            moveVertical(map, i + direction.coordinate.i(), j, direction, visited, robotI, allMoved);
            moveVertical(map, i, j - 1, direction, visited, robotI, allMoved);
        }
        if (curr == '@') {
            moveVertical(map, i + direction.coordinate.i(), j, direction, visited, robotI, allMoved);
        }

//        if (allMoved[i + direction.coordinate.i() * -1][j + direction.coordinate.j() * -1]) {
        map[i][j] = map[i + direction.coordinate.i() * -1][j + direction.coordinate.j() * -1];
//        } else {
//            map[i][j] = '.';
//        }

        if (curr == '@') {
            map[i][j] = '.';
        } else if (robotI == i + direction.coordinate.i() * -1 && map[i][j] != '@') {
            map[i][j] = '.';
        }
    }

    private static boolean isValid(int newI, int newJ, char[][] map) {
        return newI >= 0 && newI < map.length &&
                newJ >= 0 && newJ < map[0].length;
    }
}
