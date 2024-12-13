import Jama.Matrix;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;

class Day13Test {

    @Test
    void parseInput() {
        var input = """
                Button A: X+94, Y+34
                Button B: X+22, Y+67
                Prize: X=8400, Y=5400
                
                Button A: X+26, Y+66
                Button B: X+67, Y+21
                Prize: X=12748, Y=12176
                
                Button A: X+17, Y+86
                Button B: X+84, Y+37
                Prize: X=7870, Y=6450
                
                Button A: X+69, Y+23
                Button B: X+27, Y+71
                Prize: X=18641, Y=10279
                """;
        var expected = List.of(
                new Day13.Machine(new Day13.LongCoordinate(94, 34), new Day13.LongCoordinate(22, 67), new Day13.LongCoordinate(8400, 5400)),
                new Day13.Machine(new Day13.LongCoordinate(26, 66), new Day13.LongCoordinate(67, 21), new Day13.LongCoordinate(12748, 12176)),
                new Day13.Machine(new Day13.LongCoordinate(17, 86), new Day13.LongCoordinate(84, 37), new Day13.LongCoordinate(7870, 6450)),
                new Day13.Machine(new Day13.LongCoordinate(69, 23), new Day13.LongCoordinate(27, 71), new Day13.LongCoordinate(18641, 10279))
        );
        assertIterableEquals(expected, Day13.parseInput(input));
    }

    @Test
    void costPerMachine() {
        var machines = List.of(
                new Day13.Machine(new Day13.LongCoordinate(94, 34), new Day13.LongCoordinate(22, 67), new Day13.LongCoordinate(8400, 5400)),
                new Day13.Machine(new Day13.LongCoordinate(26, 66), new Day13.LongCoordinate(67, 21), new Day13.LongCoordinate(12748, 12176)),
                new Day13.Machine(new Day13.LongCoordinate(17, 86), new Day13.LongCoordinate(84, 37), new Day13.LongCoordinate(7870, 6450)),
                new Day13.Machine(new Day13.LongCoordinate(69, 23), new Day13.LongCoordinate(27, 71), new Day13.LongCoordinate(18641, 10279))
        );
        assertEquals(280L, Day13.costPerMachine(machines.get(0)));
        assertEquals(0L, Day13.costPerMachine(machines.get(1)));
        assertEquals(200L, Day13.costPerMachine(machines.get(2)));
        assertEquals(0L, Day13.costPerMachine(machines.get(3)));
    }

    @Test
    void priceForAllPrizes() {
        var input = """
                Button A: X+94, Y+34
                Button B: X+22, Y+67
                Prize: X=8400, Y=5400

                Button A: X+26, Y+66
                Button B: X+67, Y+21
                Prize: X=12748, Y=12176

                Button A: X+17, Y+86
                Button B: X+84, Y+37
                Prize: X=7870, Y=6450

                Button A: X+69, Y+23
                Button B: X+27, Y+71
                Prize: X=18641, Y=10279
                """;
        var machines = Day13.parseInput(input);

        assertEquals(480L, Day13.priceForAllPrizes(machines));
    }

    @Test
    void JamaTest() {
        double [][] values =  {{94, 22},{34, 67}};
        double[] results = {8400, 5400};
        var valuesMatrix = new Matrix(values);
        var resultsMatrix = new Matrix(results, 2);

        var ans = valuesMatrix.solve(resultsMatrix);
        var array = ans.getArray();
        System.out.println(ans.get(0,0));
        System.out.println(ans.get(1,0));
    }

    @Test
    void priceForAllPrizesOffset() {
        var input = """
                Button A: X+94, Y+34
                Button B: X+22, Y+67
                Prize: X=8400, Y=5400

                Button A: X+26, Y+66
                Button B: X+67, Y+21
                Prize: X=12748, Y=12176

                Button A: X+17, Y+86
                Button B: X+84, Y+37
                Prize: X=7870, Y=6450

                Button A: X+69, Y+23
                Button B: X+27, Y+71
                Prize: X=18641, Y=10279
                """;
        var machines = Day13.parseInput(input);

        assertEquals(875318608908L, Day13.priceForAllPrizesModifiedPrizeLocation(machines));
    }
}