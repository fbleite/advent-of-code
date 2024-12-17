import java.io.IOException;
import java.net.URISyntaxException;
import java.util.*;

public class Day16 {

    public static void main(String[] args) throws URISyntaxException, IOException {
        var input = FileUtil.getFileString("Day16.txt");
        var maze = parseInput(input);
        System.out.println(optimalPath(maze));
    }

    public static char[][] parseInput(String input) {
        var lines = input.split("\n|\r\n");
        char[][] result = new char[lines.length][lines[0].length()];
        for (int i = 0; i < lines.length; i++) {
            result[i] = lines[i].toCharArray();
        }
        return result;
    }

    enum Direction {
        NORTH(new Coordinate(-1, 0)),
        EAST(new Coordinate(0, 1)),
        SOUTH(new Coordinate(1, 0)),
        WEST(new Coordinate(0, -1));
        final Coordinate coordinate;

        Direction( Coordinate coordinate) {
            this.coordinate = coordinate;
        }
    }
    record Node(Coordinate coordinate, Direction direction, Long cost) {}

    public static long optimalPath(char[][] maze) {
        var reindeerPos = findReindeer(maze);
        Set<String> visited = new HashSet<>();
        Map<Coordinate, List<Coordinate>> prevCoordinates = new HashMap<>();
        PriorityQueue<Node> queue = new PriorityQueue<>(Comparator.comparingLong(n -> n.cost));
        queue.add(new Node(reindeerPos, Direction.EAST, 0l));
        while (!queue.isEmpty()) {
            var n = queue.poll();
            String nString = n.coordinate.toString() + n.direction.toString();
            if (visited.contains(nString)) continue;
            visited.add(nString);
            if (maze[n.coordinate.i()][n.coordinate.j()] == 'E') {
                var path = new Coordinate(n.coordinate.i(), n.coordinate.j());
//                while (path) {
//
//                }
                return n.cost;
            }
            int newI = n.coordinate.i() + n.direction.coordinate.i();
            int newJ = n.coordinate.j() + n.direction.coordinate.j();

            if (isValid(newI, newJ, maze)) {
                prevCoordinates.computeIfAbsent(new Coordinate(newI, newJ), k -> new ArrayList<>()).add(n.coordinate);
                queue.add(new Node(new Coordinate(newI, newJ), n.direction, n.cost + 1));
            }
            var clockWise = Direction.values()[(n.direction.ordinal() + 1) % 4];
            var counterClockWise = Direction.values()[(n.direction.ordinal() == 0 ? 3 : n.direction.ordinal() - 1) % 4];
            queue.add(new Node(n.coordinate, clockWise, n.cost + 1000));
            queue.add(new Node(n.coordinate, counterClockWise, n.cost + 1000));
        }
        return -1;
    }


    private static boolean isValid(int x, int y, char[][] maze) {
        return x >= 0 && x < maze.length && y >= 0 && y < maze[0].length && maze[x][y] != '#';
    }

    public static Coordinate findReindeer(char[][] maze) {
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[0].length; j++) {
                if (maze[i][j] == 'S') {
                    return new Coordinate(i, j);
                }
            }
        }
        throw new RuntimeException("No reindeer");
    }
}
