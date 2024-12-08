import java.io.IOException;
import java.net.URISyntaxException;
import java.util.*;

public class Day8 {

    public static void main (String [] args) throws URISyntaxException, IOException {
        String input = FileUtil.getFileString("Day8.txt");
        var grid = parseInput(input);
        System.out.println(countAntinodes(grid, false));
        System.out.println(countAntinodes(grid, true));
    }

    public static char[][] parseInput(String input) {
        var lines = input.split("\n|\r\n");
        char [][] result = new char[lines.length][lines[0].length()];
        for (int i = 0; i < lines.length; i++) {
            result[i] = lines[i].toCharArray();
        }
        return result;
    }

    public record Coordinate(int i, int j){}


    public static int countAntinodes(char [][] grid, boolean resonantFrequency) {
        Map<Character, List<Coordinate>> antennas = new HashMap<>();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] != '.') {
                    var coordinates = antennas.computeIfAbsent(grid[i][j], k -> new ArrayList<>());
                    coordinates.add(new Coordinate(i, j));
                }
            }
        }
        Set<Coordinate> antinodes = new HashSet<>();
        for (var antennaType : antennas.keySet()) {
            var locations = antennas.get(antennaType);
            if (locations.size() > 1 && resonantFrequency) {
                antinodes.addAll(locations);
            }
            for (int i=0; i < locations.size(); i++) {
                for (int j=0; j < locations.size(); j++) {
                    if (i==j) {
                        continue;
                    }
                    int ai = locations.get(i).i;
                    int aj = locations.get(i).j;
                    int resonantOccur = 0;
                    while (ai >= 0 && ai < grid.length && aj >= 0 && aj < grid[0].length && (resonantFrequency || resonantOccur < 1)) {
                        if (locations.get(i).i < locations.get(j).i) {
                            ai = ai - Math.abs(locations.get(j).i - locations.get(i).i);
                        } else {
                            ai = ai + Math.abs(locations.get(j).i - locations.get(i).i);
                        }

                        if (locations.get(i).j < locations.get(j).j) {
                            aj = aj - Math.abs(locations.get(j).j - locations.get(i).j);
                        } else {
                            aj = aj + Math.abs(locations.get(j).j - locations.get(i).j);
                        }
                        // Filter out antinodes out of the grid
                        if (ai >= 0 && ai < grid.length && aj >= 0 && aj < grid[0].length) {
                            antinodes.add(new Coordinate(ai, aj));
                        }
                        resonantOccur++;
                    }
                }
            }
        }
        return antinodes.size();
    }
}
