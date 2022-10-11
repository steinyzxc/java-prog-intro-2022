import java.io.*;
import java.util.*;

public class WsppLastL {
    public static void main(String[] args) {
        Map<String, IntList> words = new LinkedHashMap<>();
        Map<String, Integer> wordsCounter = new HashMap<>();
        try {
            MyScanner reader = new MyScanner(new File(args[0]));
            try {
                while (reader.hasNextLine()){
                    MyScanner wordsScanner = new MyScanner(reader.nextLine());
                    Map<String, Integer> wordsAtLine = new LinkedHashMap<>();
                    int wordCount = 1;
                    while (wordsScanner.hasNextWord()){
                        String word = wordsScanner.nextWord().toLowerCase();
                        wordsCounter.put(word, wordsCounter.getOrDefault(word, 0) + 1);
                        //System.out.println(word);
                        wordsAtLine.put(word, wordCount);
                        wordCount++;
                    }
                    for (Map.Entry<String, Integer> e : wordsAtLine.entrySet()){
                        if (words.get(e.getKey()) != null){
                            words.get(e.getKey()).add(e.getValue());
                        } else {
                            words.put(e.getKey(), new IntList(new int[]{e.getValue()}));
                        }
                    }
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
                    writer.write(entry.getKey() + " " + wordsCounter.get(entry.getKey()));
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