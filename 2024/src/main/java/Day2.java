import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Day2 {


    public static void main(String [] args) throws IOException, URISyntaxException {
        var input = FileUtil.getFileString("Day2.txt");
        System.out.println(countSafeReports(input));
        System.out.println(countDampenerSafeReports(input));
    }

    public static long countSafeReports(String input) {
        var reports = parseInput(input);
        return reports.stream().filter(r -> Day2.isSafe(r) == -1).count();
    }

    public static long countDampenerSafeReports(String input) {
        var reports = parseInput(input);
        return reports.stream().filter(Day2::isDampenerSafe).count();
    }

    public static List<List<Integer>> parseInput(String input) {
        String[] inputLines = input.split("\n|\r\n");
        return Arrays.stream(inputLines)
                .map(l -> {
                    var e = l.split(" ");
                    return Arrays.stream(e).map(Integer::valueOf).collect(Collectors.toList());
                })
                .collect(Collectors.toList());
    }

    public static int isSafe(List<Integer> report) {
        int lastNum = report.getFirst();
        boolean increasing = report.getLast() - report.getFirst() > 0;
        for (int i = 1; i < report.size(); i++ ) {
            int currNum = report.get(i);
            int diff = currNum - lastNum;
            if (increasing && diff < 0) {
                return i;
            } else if (!increasing && diff > 0) {
                return i;
            } else if (Math.abs(diff) < 1 || Math.abs(diff) > 3) {
                return i;
            }
            lastNum = currNum;
        }
        return -1;
    }


    public static boolean isDampenerSafe(List<Integer> report) {
        int isSafe = isSafe(report);
        if (isSafe == -1) {
            return true;
        }
        var rCopy = new ArrayList<>(report);
        rCopy.remove(isSafe);
        if (isSafe(rCopy) == -1) {
            return true;
        } else if (isSafe > 0) {
            rCopy = new ArrayList<>(report);
            rCopy.remove(isSafe - 1);
            return isSafe(rCopy) == -1;
        }

        return false;
    }
}
