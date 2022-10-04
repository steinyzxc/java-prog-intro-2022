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
        List<int[]> f = new ArrayList<>();
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
    }
}