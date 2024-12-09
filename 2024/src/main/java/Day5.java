import java.io.IOException;
import java.net.URISyntaxException;
import java.util.*;

public class Day5 {

    public record ParsedInput(Map<Integer, List<Integer>> rules, List<Map<Integer, Integer>> pages){};

    public static void main (String [] args) throws URISyntaxException, IOException {
        String input = FileUtil.getFileString("Day5.txt");
        var parsedInput = parseInput(input);
        System.out.println(sumOfMiddle(parsedInput));
        System.out.println(sumOfIncorrectMiddle(parsedInput));

    }

    public static int sumOfIncorrectMiddle(ParsedInput input) {
        int sum = 0;
        for (int i = 0; i < input.pages.size(); i++) {
            if (!isPageValid(input, i)) {
                var sortedPage = sortPage(input, i);
                sum += getMiddlePage(sortedPage);
            }
        }
        return sum;
    }

    public static Map<Integer, Integer> sortPage(ParsedInput input, int pageIdx) {
        Map<Integer, Integer> inboundCount = new HashMap<>();
        var page = input.pages.get(pageIdx);
        var rules = input.rules;
        for (var num : page.keySet()) {
            inboundCount.put(num, 0);
        }

        for (var num : page.keySet()) {
            var rule = rules.getOrDefault(num, new ArrayList<>());
            for (var left : rule) {
                if (inboundCount.containsKey(left)) {
                    inboundCount.put(left, inboundCount.get(left) + 1);
                }
            }
        }
        Queue<Integer> queue = new LinkedList<>();
        for (var key : inboundCount.keySet()) {
            if (inboundCount.get(key).equals(0)) {
                queue.add(key);
            }
        }

        List<Integer> orderedPage = new ArrayList<>();
        while (!queue.isEmpty()) {
            int current = queue.remove();
            orderedPage.add(current);
            for (int potentialNext : rules.getOrDefault(current, new ArrayList<>())) {
                if (inboundCount.containsKey(potentialNext)) {
                    inboundCount.put(potentialNext, inboundCount.get(potentialNext) - 1);
                    if (inboundCount.get(potentialNext).equals(0)) {
                        queue.add(potentialNext);
                    }
                }
            }

        }
        var orderedPageMap = new HashMap<Integer, Integer>();
        for (int i = 0; i < orderedPage.size(); i++) {
            orderedPageMap.put(orderedPage.get(i), i);
        }
        return orderedPageMap;
    }


    public static int sumOfMiddle(ParsedInput input) {
        int sum = 0;
        for (int i = 0; i < input.pages.size(); i++) {
            if (isPageValid(input, i)) {
                sum += getMiddlePage(input.pages.get(i));
            }
        }
        return sum;
    }

    public static ParsedInput parseInput(String input) {
        var split = input.split("\n\n|\r\n\r\n");
        return new ParsedInput(
                parseRules(split[0]),
                parsePages(split[1]));
    }

    protected static Map<Integer, List<Integer>> parseRules(String input) {
        Map<Integer, List<Integer>> rules = new HashMap<>();

        var lines = input.split("\n|\r\n");
        for (var line : lines) {
            var components = line.split("\\|");
            var left = Integer.valueOf(components[0]);
            var right = Integer.valueOf(components[1]);
            var rights = rules.getOrDefault(left, new ArrayList<>());
            rights.add(right);
            rules.put(left, rights);
        }
        return rules;
    }

    protected static List<Map<Integer, Integer>> parsePages(String input) {
        var lines = input.split("\n|\r\n");
        List<Map<Integer, Integer>> pages = new ArrayList<>();
        for (var line : lines) {
            Map<Integer, Integer> pageIndex = new HashMap<>();
            var pageNums = line.split(",");
            for (int i = 0; i < pageNums.length; i++) {
                int pn = Integer.valueOf(pageNums[i]);
                pageIndex.put(pn, i);
            }
            pages.add(pageIndex);
        }
        return pages;
    }


    protected static boolean isPageValid(ParsedInput parsedInput, int pageId) {
        var page = parsedInput.pages.get(pageId);
        for (var key : page.keySet()) {
            var numRules = parsedInput.rules.getOrDefault(key, new ArrayList<>());
            var leftId = page.get(key);
            for (var right : numRules) {
                if (leftId > page.getOrDefault(right, Integer.MAX_VALUE)) {
                    return false;
                }
            }
        }
        return true;
    }

    protected static int getMiddlePage(Map<Integer, Integer> page) {
        int middleIdx = (page.size() / 2);
        for (var key: page.keySet()) {
            if (page.get(key).equals(middleIdx)) {
                return key;
            }
        }
        throw new RuntimeException("Something wrong!");
    }

}
