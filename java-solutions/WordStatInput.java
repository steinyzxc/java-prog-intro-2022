import java.io.*;
import java.util.*;

public class WordStatInput {
    public static void main(String[] args) {
        Map<String, Integer> words = new LinkedHashMap<>();
        try {
            MyScanner reader = new MyScanner(new File(args[0]));
            try {
                while (reader.hasNextWord()){
                    String word = reader.nextWord().toLowerCase();
                    words.put(word, words.getOrDefault(word, 0) + 1);
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
                for(Map.Entry<String, Integer> entry : words.entrySet()){
                    writer.write(entry.getKey() + " " + entry.getValue());
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