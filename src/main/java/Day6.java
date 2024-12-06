import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class Day6 {

    public static void main (String [] args) throws URISyntaxException, IOException {
        String input = FileUtil.getFileString("Day6.txt");
        char[][] parsedInput = parseInput(input);
        System.out.println(countPath(parsedInput));
        parsedInput = parseInput(input);

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
        var start = findStart(input);
        int i = start.i;
        int j = start.j;
        int count = 0;
        List <Coordinate> obstructions = new ArrayList<>();
        while (i >= 0 && i < input.length && j >= 0 && j < input[0].length) {
            char direction = input[i][j];
            if (direction == '^') {
                if (i - 1 < 0 || input[i - 1][j] != '#') {
                    i = i - 1;
                } else if (input[i - 1][j] == '#') {
                    direction = '>';
                }
            } else if (direction == '>') {
                if (j + 1 >= input[0].length || input[i][j + 1] != '#') {
                    j = j + 1;
                } else if (input[i][j + 1] == '#') {
                    direction = 'v';
                }
            } else if (direction == 'v') {
                if (i + 1 >= input.length || input[i + 1][j] != '#') {
                    i = i + 1;
                } else if (input[i + 1][j] == '#') {
                    direction = '<';
                }
            } else if (direction == '<') {
                if (j - 1 < 0 || input[i][j - 1] != '#') {
                    j = j - 1;
                } else if (input[i][j - 1] == '#') {
                    direction = '^';
                }
            } else {
                throw new RuntimeException("WTF");
            }
            if (i >= 0 && i < input.length && j >= 0 && j < input[0].length) {
                input[i][j] = direction;
            }
        }
        return count;
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

    public static void markPath(char [][] input) {
        var start = findStart(input);
        int i = start.i;
        int j = start.j;
        while (i >= 0 && i < input.length && j >= 0 && j < input[0].length) {
            char direction = input[i][j];
            if (direction == '^') {
                if (i - 1 < 0 || input[i - 1][j] != '#') {
                    input[i][j] = 'X';
                    i = i - 1;
                } else if (input[i - 1][j] == '#') {
                    direction = '>';
                }
            } else if (direction == '>') {
                if (j + 1 >= input[0].length || input[i][j + 1] != '#') {
                    input[i][j] = 'X';
                    j = j + 1;
                } else if (input[i][j + 1] == '#') {
                    direction = 'v';
                }
            } else if (direction == 'v') {
                if (i + 1 >= input.length || input[i + 1][j] != '#') {
                    input[i][j] = 'X';
                    i = i + 1;
                } else if (input[i + 1][j] == '#') {
                    direction = '<';
                }
            } else if (direction == '<') {
                if (j - 1 < 0 || input[i][j - 1] != '#') {
                    input[i][j] = 'X';
                    j = j - 1;
                } else if (input[i][j - 1] == '#') {
                    direction = '^';
                }
            } else {
                throw new RuntimeException("WTF");
            }
            if (i >= 0 && i < input.length && j >= 0 && j < input[0].length) {
                input[i][j] = direction;
            }
        }
    }

    public record Coordinate(int i, int j){}
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
