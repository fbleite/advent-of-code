import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Day3Test {

    @Test
    void multiply() {
        assertEquals(161, Day3.multiply("xmul(2,4)%&mul[3,7]!@^do_not_mul(5,5)+mul(32,64]then(mul(11,8)mul(8,5))"));
    }

    @Test
    void multiplyDoAndDont() {
        assertEquals(48, Day3.multiplyDoAndDont("xmul(2,4)&mul[3,7]!^don't()_mul(5,5)+mul(32,64](mul(11,8)undo()?mul(8,5))"));
    }
}