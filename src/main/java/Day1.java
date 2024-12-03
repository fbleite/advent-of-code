import java.io.IOException;
import java.net.URISyntaxException;
import java.util.*;

public class Day1 {
    public static void main (String[] args) throws URISyntaxException, IOException {
        System.out.println("Day 1");
        var input = parseInput(FileUtil.getFileString("Day1.txt"));
        System.out.println(getDistance(input.left, input.right));
        System.out.println(getSimilarityScore(input.left, input.right));
    }

    public static long getDistance(List<Long> left, List<Long> right) {
        Collections.sort(left);
        Collections.sort(right);
        int distance = 0;
        for (int i = 0; i < left.size(); i++) {
            distance += (int) Math.abs(right.get(i) - left.get(i));
        }
        return distance;
    }

    public static long getSimilarityScore(List<Long> left, List<Long> right) {
        Map<Long, Integer> charFrequency = new HashMap<>();
        for (var r : right) {
            charFrequency.put(r, charFrequency.getOrDefault(r, 0) + 1);
        }
        long score = 0;
        for (var l : left) {
            score += l * charFrequency.getOrDefault(l, 0);
        }
        return score;
    }


    private record Lists(List<Long> left, List<Long> right) {}

    private static Lists parseInput(String input) {
        String [] lines = input.split("\n|\r\n");
        var left = new ArrayList<Long>();
        var right = new ArrayList<Long>();
        for (var line : lines) {
            String [] components = line.split(" +");
            left.add(Long.valueOf(components[0]));
            right.add(Long.valueOf(components[1]));
        }
        return new Lists(left, right);
    }

    private static String getTestInput() {
        return """
                3   4
                4   3
                2   5
                1   3
                3   9
                3   3
                """;
    }
}
