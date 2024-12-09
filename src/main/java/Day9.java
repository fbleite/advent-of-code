import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashSet;
import java.util.Set;

public class Day9 {
    public static void main (String [] args) throws URISyntaxException, IOException {
        String input = FileUtil.getFileString("Day9.txt");
        var files = decompress(input);
        compactDisk(files);
        System.out.println(calculateChecksum(files));

        files = decompress(input);
        compactDiskNoFragmentation(files);
        System.out.println(calculateChecksum(files));

    }

    public static char [] decompress(String input) {
        int totalSize = input.chars().map(c -> c - '0').sum();
        var decompressed = new char[totalSize];
        int decompressedId = 0;
        for (int i = 0; i < input.length(); i++) {
            int blockSize = input.charAt(i) - '0';
            char c;
            if (i % 2 == 0) {
                c = (char)(i/2 + '0');
            } else {
                c = '.';
            }
            int endBlockId =  blockSize + decompressedId;
            for (; decompressedId < endBlockId; decompressedId++ ) {
                decompressed[decompressedId] = c;
            }
        }
        return decompressed;
    }

    public static void compactDisk(char [] files) {
        int left = 0;
        int right = files.length -1;
        while (left < right) {
            while (files[left] != '.') {
                left++;
            }
            while (files[right] == '.') {
                right--;
            }
            if (left < right) {
                var temp = files[right];
                files[right] = files[left];
                files[left] = temp;
            }
        }
    }



    public static void compactDiskNoFragmentation(char [] files) {
        Set<Character> attempted = new HashSet<>();
        int right = files.length -1;
        while (right >= 0) {
            while (files[right] == '.') {
                right--;
            }
            char currentFileId = files[right]; //- '0';
            if (attempted.contains(currentFileId)) {
                right --;
                continue;
            }
            attempted.add(currentFileId);
            int fileStart = right;
            while (right >=0 && files[right] == currentFileId) {
                right--;
            }
            int fileEnd = right;
            int fileSize = fileStart - fileEnd;

            int emptySize = 0;
            int emptyStart = 0;
            int emptyEnd = 0;
            int left = 0;
            while(left <= right && emptySize < fileSize) {
                while(left <= right && files[left] != '.') {
                    left++;
                }
                emptyStart = left;
                while(left <= right && files[left] == '.') {
                    left++;
                }
                emptyEnd = left;
                emptySize = emptyEnd - emptyStart;
            }

            if (emptySize >= fileSize) {
                for (int i = emptyStart; i < emptyStart + fileSize; i++) {
                    files[i] = currentFileId;
                }

                for (int i = fileStart; i > fileEnd; i--) {
                    files[i] = '.';
                }
            }
        }
    }

    public static long calculateChecksum(char [] files) {
        long sum = 0;
        for (int i = 0; i < files.length; i++) {
            if (files[i] != '.') {
                sum += (i * (files[i] - '0'));
            }
        }
        return sum;
    }

}
