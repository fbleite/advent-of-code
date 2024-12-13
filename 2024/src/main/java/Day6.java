import java.io.IOException;
import java.net.URISyntaxException;
import java.util.*;

public class Day6 {

    public static void main (String [] args) throws URISyntaxException, IOException {
        String input = FileUtil.getFileString("Day6.txt");
        char[][] parsedInput = parseInput(input);
        System.out.println(countPath(parsedInput));
        parsedInput = parseInput(input);
        System.out.println(countTraps(parsedInput));

    }

    public static char[][] parseInput(String input) {
        var lines = input.split("\n|\r\n");
        char [][] result = new char[lines.length][lines[0].length()];
        for (int i = 0; i < lines.length; i++) {
            result[i] = lines[i].toCharArray();
        }
        return result;
    }
    public static int countTraps (char [][] input) {
        int count = 0;
        for (int i = 0; i < input.length; i++) {
            for (int j = 0; j < input[0].length; j++) {
//                System.out.println(String.format("i: '%d' | j: '%d' | count: '%d'", i, j, count));
                var copy = copyInput(input);
                if (copy[i][j] == '.') {
                    copy[i][j] = '#';
                    if (!markPath(copy)) {
//                        System.out.println("Valid trap");
                        count++;
                    }
                }
            }
        }
        return count;
    }

    private static char[][] copyInput (char [][] input) {
        char [][] copy = new char[input.length][input[0].length];
        for (int i = 0; i < input.length; i++) {
            for (int j = 0; j < input[0].length; j++) {
                copy[i][j] = input[i][j];
            }
        }
        return copy;
    }

    public static int countPath(char[][] input) {
        markPath(input);
        int count = 0;
        for (int i = 0; i < input.length; i++) {
            for (int j = 0; j < input[0].length; j++) {
                if (input[i][j] == 'X') count++;
            }
        }
        return count;
    }

    public static boolean markPath(char [][] input) {
        Map<Coordinate, Set<Character>> been = new HashMap<>();
        var start = findStart(input);
        int i = start.i();
        int j = start.j();
        while (i >= 0 && i < input.length && j >= 0 && j < input[0].length) {
            char direction = input[i][j];
            int nextI = i;
            int nextJ = j;
            var dirs = been.getOrDefault(new Coordinate(i, j), new HashSet<>());
            if (dirs.contains(direction)) {
                return false;
            }
            dirs.add(direction);
            been.put(new Coordinate(i, j), dirs);
            char nextDirection;
            if (direction == '^') {
                nextI = i - 1;
                nextDirection = '>';
            } else if (direction == '>') {
                nextJ = j + 1;
                nextDirection = 'v';
            } else if (direction == 'v') {
                nextI = i + 1;
                nextDirection = '<';
            } else if (direction == '<') {
                nextJ = j - 1;
                nextDirection = '^';
            } else {
                throw new RuntimeException("WTF");
            }

            if (nextI >= input.length || nextI < 0 || nextJ >= input[0].length || nextJ < 0 || input[nextI][nextJ] != '#') {
                input[i][j] = 'X';
                i = nextI;
                j = nextJ;
                if (i >= 0 && i < input.length && j >= 0 && j < input[0].length) {
                    input[i][j] = direction;
                }
            } else if (input[nextI][nextJ] == '#') {
                input[i][j] = nextDirection;
            }
        }
        return true;
    }

    private static Coordinate findStart(char [][] input) {
        for (int i = 0; i < input.length; i++) {
            for (int j = 0; j < input[0].length; j++) {
                if (input[i][j] == '^' || input[i][j] == 'v' || input[i][j] == '>' || input[i][j] == '<' ) {
                    return new Coordinate(i, j);
                }
            }
        }
        return null;
    }
}
