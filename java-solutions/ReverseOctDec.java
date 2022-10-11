import java.util.*;
import java.io.*;

public class ReverseOctDec {
    public static void main(String[] args) {
        MyScanner lines = new MyScanner(System.in);
        try {
            List<IntList> numbers = new ArrayList<IntList>();
            while (lines.hasNextLine()){
                String s = lines.nextLine();
                //System.err.println(s);
                MyScanner ints = new MyScanner(s);
                IntList n = new IntList();
                while (ints.hasNextInt()){
                    n.add(ints.nextInt());
                    //System.err.println(n.getInt(n.getSize() - 1));
                }
                numbers.add(n);
            }
            for (int i = numbers.size() - 1; i >= 0; i--) {
                for (int j = numbers.get(i).getSize() - 1; j >= 0; j--) {
                    System.out.print(numbers.get(i).getInt(j) + " ");
                }
                System.out.println();
            }
        } catch (IOException e){
            System.out.println("Read error: " + e.getMessage());
        } finally {
            lines.close();
        }
    }
}