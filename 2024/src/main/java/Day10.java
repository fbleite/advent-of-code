import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

public class Day10 {

    public static void main(String[] args) throws URISyntaxException, IOException {
        String input = FileUtil.getFileString("Day10.txt");
        var map = parseInput(input);
        System.out.println(calculateMapScore(map, false));
        System.out.println(calculateMapScore(map, true));
    }

    public static int[][] parseInput(String input) {
        var lines = input.split("\n|\r\n");
        var result = new int[lines.length][lines[0].length()];
        for (int i = 0; i < lines.length; i++) {
            for (int j = 0; j < lines[0].length(); j++) {
                result[i][j] = lines[i].charAt(j) - '0';
            }
        }
        return result;
    }

    public static int calculateMapScore(int[][] map, boolean multiplePath) {
        int sum = 0;
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if (map[i][j] == 0) {
                    Map<Coordinate, Integer> tops = new HashMap<>();
                    getTrailheadTops(i, j, map, tops);
                    if (multiplePath) {
                        for (var key : tops.keySet()) {
                            sum += tops.get(key);
                        }
                    } else {
                        sum += tops.size();
                    }
                }
            }
        }
        return sum;
    }

    public static void getTrailheadTops(int i, int j, int[][] map, Map<Coordinate, Integer> tops) {
        if (map[i][j] == 9) {
            tops.put(new Coordinate(i, j), tops.getOrDefault(new Coordinate(i, j), 0) + 1);
        }
        if (i - 1 >= 0 && map[i - 1][j] == map[i][j] + 1) {
            getTrailheadTops(i - 1, j, map, tops);
        }

        if (j - 1 >= 0 && map[i][j - 1] == map[i][j] + 1) {
            getTrailheadTops(i, j - 1, map, tops);
        }

        if (i + 1 < map.length && map[i + 1][j] == map[i][j] + 1) {
            getTrailheadTops(i + 1, j, map, tops);
        }

        if (j + 1 < map[0].length && map[i][j + 1] == map[i][j] + 1) {
            getTrailheadTops(i, j + 1, map, tops);
        }
    }
}
