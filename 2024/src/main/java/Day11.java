import java.io.IOException;
import java.net.URISyntaxException;
import java.util.*;

public class Day11 {


    public static Map<Long, List<Long>> MEMO = new HashMap<>();
    public static Map<Long, Map<Long, Long>> MEMO_FAST = new HashMap<>();

    public static void main (String [] args) throws URISyntaxException, IOException {
        var input = FileUtil.getFileString("Day11.txt");
        var stones = parseInput(input);
        long start = System.currentTimeMillis();
//        System.out.println(countStonesAfterNBlinks(stones, 25));
        System.out.println(countStonesAfterNBlinks_fast(stones, 25));
        long part1 = System.currentTimeMillis();
        System.out.println("Part 1: " + (part1 - start));
//        System.out.println(countStonesAfterNBlinks(stones, 75));
        System.out.println(countStonesAfterNBlinks_fast(stones, 75));
        long part2 = System.currentTimeMillis();
        System.out.println("Part 2: " + (part2 - part1));

    }

    public static List<Long> parseInput(String input) {
        return Arrays.stream(input.split(" +"))
                .map(n -> Long.valueOf(n))
                .toList();
    }

    public static long countStonesAfterNBlinks_fast(List<Long> stones, long blinks) {
        long sum = 0;
        for (var stone : stones) {
            sum += countStonesAfterBlinksPerStone(stone, blinks);
        }
        return sum;
    }

    /**
     * This is so much incredibly faster than the other approach I was going for, amazing!
     */
    public static long countStonesAfterBlinksPerStone(Long stone, long blinks) {
        if (MEMO_FAST.containsKey(stone) && MEMO_FAST.get(stone).containsKey(blinks)) {
            return MEMO_FAST.get(stone).get(blinks);
        }
        if (blinks == 0) {
            return 1;
        }
        var blinkedStone = new ArrayList<Long>();
        if (stone.equals(0L)) {
            blinkedStone.add(1L);
        } else if (stone.toString().length() % 2 == 0) {
            int middle = stone.toString().length()/2;
            blinkedStone.add(Long.valueOf(stone.toString().substring(0, middle)));
            blinkedStone.add(Long.valueOf(stone.toString().substring(middle)));
        } else {
            blinkedStone.add(stone * 2024L);
        }

        long count = 0;
        for (var bs : blinkedStone) {
            count += countStonesAfterBlinksPerStone(bs, blinks - 1);
        }
        var stoneMemo = MEMO_FAST.getOrDefault(stone, new HashMap<>());
        stoneMemo.put(blinks, count);
        MEMO_FAST.put(stone, stoneMemo);
        return MEMO_FAST.get(stone).get(blinks);

    }

    public static long countStonesAfterNBlinks(List<Long> stones, int blinks) {
        return blinkNTimes(stones, blinks).size();
    }

    public static List<Long> blinkNTimes(List<Long> stones, int n) {
        var currentStones = stones;
        int remainingBlinks = n;
        while (remainingBlinks > 0) {
            System.out.println(remainingBlinks);
            currentStones = blink(currentStones);
            remainingBlinks--;
        }
        return  currentStones;
    }

    public static List<Long> blink(List<Long> stones) {
        List<Long> blinkedStones = new LinkedList<>();
        for (var stone : stones) {
            if (MEMO.containsKey(stone)) {
                blinkedStones.addAll(MEMO.get(stone));
                continue;
            }
            var blinkedStone = new ArrayList<Long>();
            if (stone.equals(0L)) {
                blinkedStone.add(1L);
            } else if (stone.toString().length() % 2 == 0) {
                int middle = stone.toString().length()/2;
                blinkedStone.add(Long.valueOf(stone.toString().substring(0, middle)));
                blinkedStone.add(Long.valueOf(stone.toString().substring(middle)));
            } else {
                blinkedStone.add(stone * 2024L);
            }
            blinkedStones.addAll(blinkedStone);
            MEMO.put(stone, blinkedStone);
        }
        return blinkedStones;
    }
}
