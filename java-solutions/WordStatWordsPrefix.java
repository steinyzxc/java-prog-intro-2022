import java.io.*;
import java.util.*;


public class WordStatWordsPrefix {

    static boolean checkChar(char c){
        return (Character.isLetter(c) || 
            Character.getType(c) == Character.DASH_PUNCTUATION || c == '\'');
    }

    public static void main(String[] args){
        List<String> prefixes = new ArrayList<String>();
        try {
            BufferedReader reader = new BufferedReader(
                new InputStreamReader(new FileInputStream(args[0]), "utf-8"));
            try {
                char[] inp = new char[512];
                int read = reader.read(inp);
                StringBuilder word = new StringBuilder();
                while (read >= 0){
                    //System.err.println(inp);
                    //System.err.println("-------------");
                    for (int i = 0; i < read; i++) {
                        if (checkChar(inp[i])){
                            word.append(Character.toLowerCase(inp[i]));
                        } else {
                            if (!word.isEmpty()){
                                prefixes.add(word.substring(0, Math.min(3, word.length())));
                            }
                            word = new StringBuilder();
                        }
                    }
                    read = reader.read(inp);
                }
                if (!word.isEmpty()){
                    prefixes.add(word.substring(0, Math.min(3, word.length())));
                }
                Collections.sort(prefixes);
            } catch  (IOException e){
                System.out.println("Read exception:" + e.getMessage());
            } finally {
                try{
                    reader.close();
                } catch (IOException e){
                    System.out.println("Close reader exception:" + e.getMessage());
                }
            }
        } catch (FileNotFoundException e){
            System.out.println("Input file not found:" + e.getMessage());
        } catch (UnsupportedEncodingException e){
            System.out.println("utf-8 not supported...");
        }
        try{
            BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(new FileOutputStream(args[1]), "utf-8"));
            try{
                int cnt = 1;
                for (int i = 0; i < prefixes.size() - 1; i++) {
                    if (prefixes.get(i).equals(prefixes.get(i + 1))) {
                        cnt++;
                    }
                    else{
                        writer.write(prefixes.get(i) + " " + cnt);
                        writer.newLine();
                        cnt = 1;
                    }
                }
                writer.write(prefixes.get(prefixes.size() - 1) + " " + cnt);
                writer.newLine();
            } catch (IOException e){
                System.out.println("Write exception:" + e.getMessage());
            } finally {
                try{
                    writer.close();
                } catch (IOException e){
                    System.out.println("Close writer exception:" + e.getMessage());
                }
            }
        } catch (FileNotFoundException e){
            System.out.println("Output file not found:" + e.getMessage());
        } catch (UnsupportedEncodingException e){
            System.out.println("utf-8 not supported...");
        }
    }
}
 