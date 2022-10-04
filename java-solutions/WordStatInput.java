import java.io.*;
import java.util.*;

public class WordStatInput {
    public static void main(String[] args) {
        HashMap<String, Integer> words = new HashMap<>();
        try {
            BufferedReader reader = new BufferedReader(
                new InputStreamReader(new FileInputStream(args[0]), "utf-8"));
            try {
                String line = reader.readLine();
                while (line != null) {
                    if (!line.isEmpty()){
                        StringBuilder word = new StringBuilder();
                        for (int i = 0; i < line.length(); i++) {
                            if (Character.isLetter(line.charAt(i)) ||
                                Character.getType(line.charAt(i)) == Character.DASH_PUNCTUATION ||
                                line.charAt(i) == '\'') 
                            {
                                word.append(Character.toLowerCase(line.charAt(i)));
                            } else {
                                if (!(word.isEmpty())){
                                    if (words.containsKey(word.toString())) {
                                        words.put(word.toString(), words.get(word.toString()) + 1);
                                    }
                                    else{
                                        words.put(word.toString(), 1);
                                    }
                                }
                                word = new StringBuilder();
                            }
                        }
                        if (!(word.isEmpty())) {
                            if (words.containsKey(word.toString())) {
                                words.put(word.toString(), words.get(word.toString()) + 1);
                            }
                            else{
                                words.put(word.toString(), 1);
                            }
                        }
                    }
                    line = reader.readLine();
                }
            } finally {
                reader.close();
            }
            reader = new BufferedReader(
                new InputStreamReader(new FileInputStream(args[0]), "utf-8"));
            try{
                BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(new FileOutputStream(args[1]), "utf-8"));
                try {
                    String line = reader.readLine();
                    while (line != null) {
                        if (!line.isEmpty()){
                            StringBuilder word = new StringBuilder();
                            for (int i = 0; i < line.length(); i++) {
                                if (Character.isLetter(line.charAt(i)) ||
                                    Character.getType(line.charAt(i)) == Character.DASH_PUNCTUATION ||
                                    line.charAt(i) =='\'') 
                                {
                                        word.append(Character.toLowerCase(line.charAt(i)));
                                } else {
                                    if (!(word.isEmpty())){
                                        if (words.get(word.toString()) > 0) {
                                            writer.write(word.toString());
                                            writer.write(" ");
                                            writer.write(Integer.toString(words.get(word.toString())));
                                            writer.newLine();
                                            words.put(word.toString(), -1);
                                        }
                                        word = new StringBuilder();
                                    }
                                }
                            }
                            if (!(word.isEmpty())){
                                if (words.get(word.toString()) > 0) {
                                    writer.write(word.toString());
                                    writer.write(" ");
                                    writer.write(Integer.toString(words.get(word.toString())));
                                    writer.newLine();
                                    words.put(word.toString(), -1);
                                }
                            }
                        }
                        line = reader.readLine();
                    }
                } finally {
                    writer.close();
                }
            } finally {
                reader.close();
            }
        } catch (IOException e){
            System.out.println("File not found!" + e.getMessage());
        }
    }
}