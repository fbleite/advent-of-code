import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import Jama.Matrix;

public class Day13 {
    public record Machine(LongCoordinate aButton, LongCoordinate bButton, LongCoordinate prize) {
    }

    public record LongCoordinate(long i, long j) {
    }

    public static void main(String[] args) throws URISyntaxException, IOException {
        var input = FileUtil.getFileString("Day13.txt");
        var machines = parseInput(input);
        System.out.println(priceForAllPrizes(machines));
        System.out.println(priceForAllPrizesModifiedPrizeLocation(machines));
    }

    public static List<Machine> parseInput(String input) {
        var stringMachines = input.split("\n\n|\r\n\r\n");
        List<Machine> machines = new ArrayList<>();
        Pattern pattern = Pattern.compile("Button A: X\\+([0-9]+), Y\\+([0-9]+)(?:\\r\\n|\\n)Button B: X\\+([0-9]+), Y\\+([0-9]+)(?:\\r\\n|\\n)Prize: X=([0-9]+), Y=([0-9]+)");
        for (var sm : stringMachines) {
            var matcher = pattern.matcher(sm);
            if (matcher.find()) {
                var aButton = new LongCoordinate(Integer.valueOf(matcher.group(1)), Integer.valueOf(matcher.group(2)));
                var bButton = new LongCoordinate(Integer.valueOf(matcher.group(3)), Integer.valueOf(matcher.group(4)));
                var prize = new LongCoordinate(Integer.valueOf(matcher.group(5)), Integer.valueOf(matcher.group(6)));
                machines.add(new Machine(aButton, bButton, prize));
            } else {
                throw new RuntimeException("Didn't match the pattern");
            }
        }
        return machines;
    }

    public static long priceForAllPrizes(List<Machine> machines) {
        return machines.stream().mapToLong(Day13::costPerMachine).sum();
    }

    public static long PRIZE_OFFSET = 10000000000000L;

    public static long priceForAllPrizesModifiedPrizeLocation(List<Machine> machines) {
        System.out.println("Let's go");
        return machines.stream()
                .map(m -> new Machine(m.aButton, m.bButton, new LongCoordinate(m.prize.i() + PRIZE_OFFSET, m.prize.j() + PRIZE_OFFSET)))
                .mapToLong(m -> Day13.costPerMachine(m, false)).sum();
    }


    public static long costPerMachine(Machine machine) {
        return costPerMachine(machine, true);
    }
    // There was just no way to solve these equations by hand, so had to pull in a library to help
    public static long costPerMachine(Machine machine, boolean max100) {
        var valuesMatrix = new Matrix(new double[][]{
                {(double) machine.aButton.i, (double) machine.bButton.i},
                {(double) machine.aButton.j, (double) machine.bButton.j},
        });
        var equalMatrix = new Matrix(new double[]
                {(double) machine.prize.i, (double) machine.prize.j}, 2);
        var ans = valuesMatrix.solve(equalMatrix);
        var ansArray = ans.getArray();
        if (ansArray[0].length > 1 || ansArray[1].length > 1) {
            System.out.println(machine);
        }
        long a = Math.round(ans.get(0, 0));
        long b = Math.round(ans.get(1, 0));
        long aXPos = machine.aButton.i() * a;
        long bXPos = machine.bButton.i() * b;
        long aYPos = machine.aButton.j() * a;
        long bYPos = machine.bButton.j() * b;
        if (machine.prize.i() == aXPos + bXPos && machine.prize.j() == aYPos + bYPos) {
            if ((max100 && a <= 100 && b <= 100) || !max100) {
                return 3 * a + b;
            }
        }
        return 0;
    }

    public static long costPerMachine_infinite(Machine machine) {
        Long costPerMachine = null;
        long a = 0;
        while (a * machine.aButton.i() <= machine.prize.i && a * machine.aButton.j() <= machine.prize.j) {
            long b = 0L;
            while (b * machine.bButton.i() <= machine.prize.i && b * machine.bButton.j() <= machine.prize.j) {
                long aXPos = machine.aButton.i() * a;
                long bXPos = machine.bButton.i() * b;
                long aYPos = machine.aButton.j() * a;
                long bYPos = machine.bButton.j() * b;

                if (machine.prize.i() == aXPos + bXPos && machine.prize.j() == aYPos + bYPos) {
                    long currPrice = a * 3 + b;
                    if (costPerMachine == null || costPerMachine > currPrice) {
                        costPerMachine = currPrice;
                    }
                }
                b++;
            }
            a++;
        }
        return costPerMachine == null ? 0L : costPerMachine;
    }

    public static long costPerMachine_old(Machine machine) {
        Long costPerMachine = null;
        for (int i = 0; i <= 100; i++) {

            for (int j = 0; j <= 100; j++) {
                int aTimes = i;
                int bTimes = j;
                long aXPos = machine.aButton.i() * aTimes;
                long bXPos = machine.bButton.i() * bTimes;
                long aYPos = machine.aButton.j() * aTimes;
                long bYPos = machine.bButton.j() * bTimes;

                if (machine.prize.i() == aXPos + bXPos && machine.prize.j() == aYPos + bYPos) {
                    long currPrice = aTimes * 3 + bTimes;
                    if (costPerMachine == null || costPerMachine > currPrice) {
                        costPerMachine = currPrice;
                    }
                }
            }
        }
        return costPerMachine == null ? 0L : costPerMachine;
    }
}
