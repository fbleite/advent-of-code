import java.io.IOException;
import java.net.URISyntaxException;

public class Day4 {

    public static void main(String[] args) throws URISyntaxException, IOException {
        var input = FileUtil.getFileString("Day4.txt");
        var grid = parseInput(input);
        System.out.println(countXmas(grid));
        System.out.println(countX_mas(grid));
    }

    public static char[][] parseInput(String input) {
        var lines = input.split("\n|\r\n");
        char[][] result = new char[lines.length][lines[0].length()];
        for (int i = 0; i < lines.length; i++) {
            result[i] = lines[i].toCharArray();
        }
        return result;
    }

    public static int countX_mas(char[][] input) {
        int count = 0;

        for (int i = 0; i < input.length; i++) {
            for (int j = 0; j < input[0].length; j++) {
                count = isX_mas(new int[]{i, j}, input) ? count + 1 : count;
            }
        }
        return count;
    }

    public static int countXmas(char[][] input) {
        int count = 0;
        for (int i = 0; i < input.length; i++) {
            for (int j = 0; j < input[0].length; j++) {
                if (input[i][j] == 'X') {
                    // Check forward
                    if (isXmas(new int[][]{{i, j}, {i, j + 1}, {i, j + 2}, {i, j + 3}}, input)) count++;

                    // Check backward
                    if (isXmas(new int[][]{{i, j}, {i, j - 1}, {i, j - 2}, {i, j - 3}}, input)) count++;

                    // Check up
                    if (isXmas(new int[][]{{i, j}, {i - 1, j}, {i - 2, j}, {i - 3, j}}, input)) count++;

                    // Check down
                    if (isXmas(new int[][]{{i, j}, {i + 1, j}, {i + 2, j}, {i + 3, j}}, input)) count++;

                    // Check diagonal up right
                    if (isXmas(new int[][]{{i, j}, {i - 1, j + 1}, {i - 2, j + 2}, {i - 3, j + 3}}, input)) count++;

                    // Check diagonal up left
                    if (isXmas(new int[][]{{i, j}, {i - 1, j - 1}, {i - 2, j - 2}, {i - 3, j - 3}}, input)) count++;

                    // Check diagonal down right
                    if (isXmas(new int[][]{{i, j}, {i + 1, j + 1}, {i + 2, j + 2}, {i + 3, j + 3}}, input)) count++;

                    // Check diagonal down left
                    if (isXmas(new int[][]{{i, j}, {i + 1, j - 1}, {i + 2, j - 2}, {i + 3, j - 3}}, input)) count++;
                }
            }
        }
        return count;
    }

    public static boolean isXmas(int[][] spots, char[][] grid) {
        return isWord("XMAS", spots, grid);
    }

    public static boolean isX_mas(int[] center, char[][] grid) {
        var descDiagonal = new int[][]{
                {center[0]-1, center[1]-1},
                {center[0], center[1]},
                {center[0] + 1, center[1] + 1},
        };

        var ascDiagonal = new int[][]{
                {center[0] + 1, center[1] - 1},
                {center[0], center[1]},
                {center[0] - 1, center[1] + 1},
        };
        return  (isWord("MAS", descDiagonal, grid) || isWord("SAM", descDiagonal, grid)) &&
                (isWord("MAS", ascDiagonal, grid) || isWord("SAM", ascDiagonal, grid));

    }


    public static boolean isWord(String word, int[][] spots, char[][] grid) {
        for (int i = 0; i < spots.length; i++) {
            if (spots[i][0] < 0 || spots[i][1] < 0 || spots[i][0] >= grid.length || spots[i][1] >= grid[0].length) {
                return false;
            }
            if (grid[spots[i][0]][spots[i][1]] != word.charAt(i)) {
                return false;
            }
        }
        return true;
    }
}
