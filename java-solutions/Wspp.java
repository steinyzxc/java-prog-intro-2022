import java.io.*;
import java.util.*;

public class Wspp {
    public static void main(String[] args) {
        Map<String, IntList> words = new LinkedHashMap<>();
        try {
            MyScanner reader = new MyScanner(new File(args[0]));
            try {
                int wordCount = 1;
                while (reader.hasNextWord()){
                    String word = reader.nextWord().toLowerCase();
                    if (words.get(word) != null){
                        words.get(word).add(wordCount);
                    } else {
                        words.put(word, new IntList(new int[]{wordCount}));
                    }
                    wordCount++;
                }
            } catch (FileNotFoundException e){
                System.out.println("File not found: " + e.getMessage());
            } catch (IOException e){
                System.out.println("Read error: " + e.getMessage());
            } finally {
                reader.close();
            }
        } catch (FileNotFoundException e) {
            System.out.println("Input file not found: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Read error: " + e.getMessage());
        }
        try {
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(args[1]), "utf-8"));
            try {
                for(Map.Entry<String, IntList> entry : words.entrySet()){
                    writer.write(entry.getKey() + " " + entry.getValue().getSize());
                    for (int i = 0; i < entry.getValue().getSize(); i++) {
                        writer.write(" " + entry.getValue().getInt(i));
                    }
                    writer.newLine();
                }
            } finally {
                writer.close();
            }
        } catch (FileNotFoundException e) {
            System.out.println("Output file not found: " + e.getMessage());
        } catch (IOException e){
            System.out.println("Write error: " + e.getMessage());
        }
    }
}