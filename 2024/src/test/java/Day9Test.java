import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Day9Test {

    @Test
    void decompress_12345() {
        var input = "12345";
        var expected = "0..111....22222".toCharArray();
        assertArrayEquals(expected, Day9.decompress(input));
    }

    @Test
    void decompress() {
        var input = "2333133121414131402";
        var expected = "00...111...2...333.44.5555.6666.777.888899".toCharArray();
        assertArrayEquals(expected, Day9.decompress(input));
    }


    @Test
    void compact_12345() {
        var input = "12345";
        var expected = "022111222......".toCharArray();
        var files = Day9.decompress(input);
        Day9.compactDisk(files);
        assertArrayEquals(expected, files);
    }


    @Test
    void compact() {
        var input = "2333133121414131402";
        var expected = "0099811188827773336446555566..............".toCharArray();
        var files = Day9.decompress(input);
        Day9.compactDisk(files);
        assertArrayEquals(expected, files);
    }

    @Test
    void checksum() {
        var input = "2333133121414131402";
        var files = Day9.decompress(input);
        Day9.compactDisk(files);
        assertEquals(1928, Day9.calculateChecksum(files));
    }

    @Test
    void compact_noFragment() {
        var input = "2333133121414131402";
        var expected = "00992111777.44.333....5555.6666.....8888..".toCharArray();
        var files = Day9.decompress(input);
        Day9.compactDiskNoFragmentation(files);
        assertArrayEquals(expected, files);
    }

    @Test
    void compact_noFragment_12345() {
        var input = "12345";
        var expected = "0..111....22222".toCharArray();
        var files = Day9.decompress(input);
        Day9.compactDiskNoFragmentation(files);
        assertArrayEquals(expected, files);
    }
    @Test
    void compact_noFragment_12354() {
        var input = "12355";
        var expected = "0..11122222.....".toCharArray();
        var files = Day9.decompress(input);
        Day9.compactDiskNoFragmentation(files);
        assertArrayEquals(expected, files);
    }

    @Test
    void checksum_noFragment() {
        var input = "2333133121414131402";
        var files = Day9.decompress(input);
        Day9.compactDiskNoFragmentation(files);
        assertEquals(2858, Day9.calculateChecksum(files));
    }

}