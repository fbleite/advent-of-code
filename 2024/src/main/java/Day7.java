import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class Day7 {

    public static void main (String [] args) throws URISyntaxException, IOException {
        String input = FileUtil.getFileString("Day7.txt");
        var parsedInput = parseInput(input);
        System.out.println(sumOfValidEquations(parsedInput));
        System.out.println(sumOfValidEquationsWithConcat(parsedInput));
        // This is not only cleaner but also much faster!
        System.out.println(sumOfValidEquationsWithConcatRecursive(parsedInput));
    }

    public record Calibration(Long target, List<Long> values) {
    }

    public static List<Calibration> parseInput(String input) {
        return Arrays.stream(input.split("\n|\r\n"))
                .map(l -> l.split(":"))
                .map(c -> new Calibration(
                        Long.valueOf(c[0]),
                        Arrays.stream(c[1].split(" "))
                                .filter(s -> !s.isEmpty())
                                .map(Long::valueOf)
                                .toList()
                ))
                .toList();
    }


    public static long sumOfValidEquations(List<Calibration> input) {
        return input.stream()
                .filter(Day7::isValidEquation)
                .mapToLong(c -> c.target)
                .sum();
    }

    public static long sumOfValidEquationsWithConcat(List<Calibration> input) {
        return input.stream()
                .filter(Day7::isValidEquationWithConcat)
                .mapToLong(c -> c.target)
                .sum();
    }


    public static long sumOfValidEquationsWithConcatRecursive(List<Calibration> input) {
        return input.stream()
                .filter(c -> isValidEquationRecursion(0, c, 0L))
                .mapToLong(c -> c.target)
                .sum();
    }

    public static boolean isValidEquation(Calibration calibration) {
        int maxOperations = (int) Math.pow(2, calibration.values.size() - 1);
        return IntStream.range(0, maxOperations)
                .anyMatch(operations -> isTargetMatches(operations, calibration));
    }

    public static boolean isValidEquationWithConcat(Calibration calibration) {
        int maxOperations = (int) Math.pow(2, calibration.values.size() - 1);
        return IntStream.range(0, maxOperations)
                .anyMatch(operations -> IntStream.range(0, maxOperations)
                        .anyMatch(concatOperation -> isTargetMatches(operations, concatOperation, calibration)));
    }

    public static boolean isTargetMatches(int operations, Calibration calibration) {
        return isTargetMatches(operations, 0, calibration);
    }

    // This is a mess of bitmaps to get the total permutation of operations, I started part 1 with only 1 bitmap, but when another operation got included I needed to somehow make a 3rd state to my bits :|
    public static boolean isTargetMatches(int operations, int concatOperations, Calibration calibration) {
        var currValue = calibration.values.get(0);
        int mask = ((int) Math.pow(2, calibration.values.size() - 1)) >> 1;
        for (int i = 1; i < calibration.values.size(); i++) {
            if ((mask & concatOperations) > 0) {
                currValue = Long.valueOf(String.valueOf(currValue) + calibration.values.get(i));
            } else if ((mask & operations) > 0) {
                currValue = currValue * calibration.values.get(i);
            } else {
                currValue = currValue + calibration.values.get(i);
            }
            if (currValue > calibration.target()) {
                break;
            }
            mask = mask >> 1;
        }
        return currValue.equals(calibration.target);
    }


    /**
     * This is really nice and clean, much better than what I came up with myself with the odd bitmap masking shenanigans
     */
    public static boolean isValidEquationRecursion(int index, Calibration calibration, Long currentValue) {
        if (currentValue > calibration.target) {
            return false;
        }
        if (index == calibration.values.size()) {
            return calibration.target.equals(currentValue);
        }
        return isValidEquationRecursion(index + 1, calibration, currentValue + calibration.values.get(index)) ||
                isValidEquationRecursion(index + 1, calibration, currentValue * calibration.values.get(index)) ||
                isValidEquationRecursion(index + 1, calibration, Long.valueOf(currentValue +  String.valueOf(calibration.values.get(index))));
    }

}
