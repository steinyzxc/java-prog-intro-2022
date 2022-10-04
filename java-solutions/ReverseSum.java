import java.util.*;

public class ReverseSum{

    static int[] add(int[] arr, int n){
        if (arr[arr.length - 1] == arr.length - 1) {
            int[] brr = new int[(arr.length) * 2 - 1];
            for (int i = 0; i < arr.length - 1; i++) {
                brr[i] = arr[i];
            }
            brr[brr.length - 1] = arr[arr.length - 1] + 1;
            brr[brr[brr.length - 1] - 1] = n;
            return brr;
        }
        else{

            arr[arr[arr.length - 1]] = n;
            arr[arr.length - 1] += 1;
            return arr;
        }
    }


    public static void main(String[] args) {
        ArrayList<int[]> f = new ArrayList<>();
        Scanner scanLines = new Scanner(System.in);
        int maxLen = 0;
        while (scanLines.hasNextLine()) {
            String line = scanLines.nextLine();
            if (!line.isEmpty()) {
                Scanner ints = new Scanner(line);
                int[] n = new int[2];
                while (ints.hasNextInt()){
                    int k = ints.nextInt();
                    n = add(n, k);
                }
                maxLen = Math.max(n[n.length - 1], maxLen);
                f.add(n);
                
            } else {
                f.add(new int[0]);
            }
        }
        int[] linesSum = new int[f.size()];
        int[] columnSum = new int[maxLen];
        for (int i = 0; i < f.size(); i++) {
            if (f.get(i).length != 0){
                for (int j = 0; j < f.get(i)[f.get(i).length - 1]; j++) {
                    linesSum[i] += f.get(i)[j];
                    columnSum[j] += f.get(i)[j];
                }
            }
        }
        for (int i = 0; i < f.size(); i++) {
            if (f.get(i).length != 0){
                for (int j = 0; j < f.get(i)[f.get(i).length - 1]; j++) {
                    System.out.print(linesSum[i] + columnSum[j] - f.get(i)[j]);
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
        /*
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
        
        for (int i = 0; i < lineCount; i++) {
            for (int j = lines[i]; j < lines[i + 1]; j++) {
                System.out.print(linesSum[i] + columnSum[j - lines[i]] - numbers[j]);
                System.out.print(" ");
            }
            System.out.println();
        }
        */

        
        /*
        for (int i = lineCount; i > 0; i--) {
            for (int j = lines[i] - 1; j >= lines[i - 1]; j--) {
                System.out.print(numbers[j]);
                System.out.print(" ");
            }
            System.out.print("\n");
        }
        */

    }
}