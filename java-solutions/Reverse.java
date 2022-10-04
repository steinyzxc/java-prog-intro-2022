import java.util.*;

public class Reverse{
    public static void main(String[] args) {
        int[] numbers = new int[1_000_000];
        int[] lines = new int[1_000_000];
        Scanner scanLines = new Scanner(System.in);
        int lineCount = 0;
        int lineIndex = 0;
        int maxLen = 0;
        while (scanLines.hasNextLine()) {
            String line = scanLines.nextLine();
            Scanner ints = new Scanner(line);
            lines[lineCount] = lineIndex;
            int len = 0;
            while (ints.hasNextInt()) {
                numbers[lineIndex] = ints.nextInt();
                //System.err.print(numbers[lineIndex] + " ");
                lineIndex++;
                len++;
            }
            //System.err.println();
            maxLen = Math.max(len, maxLen);
            len = 0;
            lineCount++;
        }
        lines[lineCount] = lineIndex;
        int[] linesSum = new int[lineCount];
        int[] columnSum = new int[maxLen];
        for (int i = 0; i < lineCount; i++) {
            for (int j = lines[i]; j < lines[i + 1]; j++) {
                linesSum[i] += numbers[j];
                columnSum[j - lines[i]] += numbers[j];
            }
        }
        for (int i = lineCount; i > 0; i--) {
            for (int j = lines[i] - 1; j >= lines[i - 1]; j--) {
                System.out.print(numbers[j]);
                System.out.print(" ");
            }
            System.out.print("\n");
        }
    }
}