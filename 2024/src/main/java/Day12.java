import java.io.IOException;
import java.net.URISyntaxException;
import java.util.*;


public class Day12 {

    public static void main(String[] args) throws URISyntaxException, IOException {
        var input = FileUtil.getFileString("Day12.txt");
        var map = parseInput(input);
        System.out.println(calculateTotalPrice(map, false));
        System.out.println(calculateTotalPrice(map, true));

    }

    public static char[][] parseInput(String input) {
        var lines = input.split("\n|\r\n");
        char[][] result = new char[lines.length][lines[0].length()];
        for (int i = 0; i < lines.length; i++) {
            result[i] = lines[i].toCharArray();
        }
        return result;
    }

    public record Coordinate(int i, int j) {
    }


    public static long calculateTotalPrice(char[][] map, boolean cheaper) {
        var visited = new boolean[map.length][map[0].length];
        long totalPrice = 0;
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map.length; j++) {
                if (!visited[i][j]) {
                    var currPrice = priceForRegion(map, i, j, visited, cheaper);
                    totalPrice += currPrice;
                }
            }
        }
        return totalPrice;
    }

    enum Side {
        UP(new Coordinate(-1, 0)),
        DOWN(new Coordinate(1, 0)),
        LEFT(new Coordinate(0, -1)),
        RIGHT(new Coordinate(0, 1));
        Coordinate coordinate;

        Side(Coordinate coordinate) {
            this.coordinate = coordinate;
        }


    }

    public record Fence(Coordinate coordinate, Side side) {
    }

    public static long priceForRegion(char[][] map, int i, int j, boolean[][] visited) {
        return priceForRegion(map, i, j, visited, false);
    }

    public static long priceForRegion(char[][] map, int i, int j, boolean[][] visited, boolean cheaper) {
        var currChar = map[i][j];
        Set<Fence> fences = new HashSet<>();
        Queue<Coordinate> queue = new LinkedList<>();
        long area = 0;
        visited[i][j] = true;
        queue.add(new Coordinate(i, j));
        while (!queue.isEmpty()) {
            var currPlot = queue.poll();
            area++;
            for (var direction : Side.values()) {
                int newI = currPlot.i + direction.coordinate.i;
                int newJ = currPlot.j + direction.coordinate.j;
                if (isValid(newI, newJ, map, currChar)) {
                    if (!visited[newI][newJ]) {
                        queue.add(new Coordinate(newI, newJ));
                        visited[newI][newJ] = true;
                    }
                } else {
                    fences.add(new Fence(currPlot, direction));
                }
            }
        }
        long sides = cheaper ? calculateSideFromFences(fences) : fences.size();
        return area * sides;
    }

    private static long calculateSideFromFences(Set<Fence> fences) {
        long sides = 0;
        Set<Fence> visited = new HashSet<>();
        for (var fence : fences) {
            if (!visited.contains(fence)) {
                sides++;
                if (fence.side.equals(Side.UP) || fence.side.equals(Side.DOWN)) {
                    visitAllFencesFromSide(fences, visited, Side.LEFT, fence);
                    visitAllFencesFromSide(fences, visited, Side.RIGHT, fence);
                } else {
                    visitAllFencesFromSide(fences, visited, Side.UP, fence);
                    visitAllFencesFromSide(fences, visited, Side.DOWN, fence);
                }
            }
        }
        return sides;
    }

    private static void visitAllFencesFromSide(Set<Fence> fences, Set<Fence> visited, Side side, Fence current) {
        visited.add(current);
        int newI = current.coordinate.i + side.coordinate.i;
        int newJ = current.coordinate.j + side.coordinate.j;
        var nextFence = new Fence(new Coordinate(newI, newJ), current.side());
        if (fences.contains(nextFence)) {
            visitAllFencesFromSide(fences, visited, side, nextFence);
        }
    }

    private static boolean isValid(int i, int j, char[][] map, char currChar) {
        return i >= 0 && j >= 0 && i < map.length && j < map[0].length && map[i][j] == currChar;
    }
}
