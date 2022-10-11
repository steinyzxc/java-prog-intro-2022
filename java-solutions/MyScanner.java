import java.io.*;
import java.util.*;
import java.util.function.*;

public class MyScanner {
    private final BufferedReader reader;
    private char[] buffer = new char[512];
    private int index = 0;
    private int read = 0;

    //---------------------constructors w/o buffersize----------------------------
    public MyScanner(File file, String encoding) throws FileNotFoundException, UnsupportedEncodingException { // :NOTE: Charset
        reader = new BufferedReader(new InputStreamReader(
            new FileInputStream(file), encoding)
        );
    }

    public MyScanner(File file) throws FileNotFoundException, UnsupportedEncodingException {
        this(file, "utf-8");
    }

    public MyScanner(String s) {
        reader = new BufferedReader(new StringReader(s));
        //System.out.println(s + " constructor");
    }

    public MyScanner(InputStream in) {
        reader = new BufferedReader(new InputStreamReader(in));
    }

    //---------------constructors with buffer size-------------------------------------
    public MyScanner(File file, String encoding, int size) throws FileNotFoundException, UnsupportedEncodingException {
        this(file, encoding);
        buffer = new char[size];
    }

    public MyScanner(File file, int size) throws FileNotFoundException, UnsupportedEncodingException {
        this(file, "utf-8");
        buffer = new char[size];
    }

    public MyScanner(String s, int size) {
        this(s);
        buffer = new char[size];
    }

    public MyScanner(InputStream in, int size) {
        this(in);
        buffer = new char[size];
    }
//-----------------------------end of constructors!!------------------------------------------------

    public int read() throws IOException {
        if (index < read) {
            return read;
        } else {
            read = reader.read(buffer);
            index = 0;
            return read;
        }
    }

    //----------------------CHECKERS--------------------------------
    private static boolean checkCharForWord(Character c) {
        return Character.isLetter(c) ||
            Character.getType(c) == Character.DASH_PUNCTUATION ||
            c == '\'';
    }

    private final Predicate<Character> checkCharForWord = MyScanner::checkCharForWord;

    private static boolean checkNotWhitespace(Character c) {
        return !Character.isWhitespace(c);
    }

    private final Predicate<Character> checkNotWhitespace = MyScanner::checkNotWhitespace;

    private static boolean checkIsLineSeparator(Character c) {
        return !(c.equals('\n') || c.equals('\r'));
    }

    private final Predicate<Character> checkIsLineSeparator = MyScanner::checkIsLineSeparator;

    //------------------------END OF CHECKERS--------------------
    private String next(Predicate<Character> checker) throws IOException {
        StringBuilder word = new StringBuilder();
        this.read();
        while (read > 0) { // :NOTE: very bad
            for (; index < read; index++) {
                if (checker.test(buffer[index])) {
                    word.append(buffer[index]);
                } else {
                    if (checker == checkIsLineSeparator) {
                        if (index + 1 < read) {
                            if (buffer[index] == '\r' && buffer[index + 1] == '\n') {
                                index++;
                            }
                        } else {
                            if (buffer[index] == '\r') {
                                index = read;
                                if (this.read() > 0) {
                                    if (buffer[0] != '\n') {
                                        index--;
                                    }
                                }
                            }
                        }
                        index++;
                        return word.toString();
                    }
                    if (!word.isEmpty()) {
                        index++;
                        return word.toString();
                    }
                }
            }
            this.read();
        }
        if (!word.isEmpty()) {
            return word.toString();
        } else {
            throw new NoSuchElementException();
        }
    }

    public String nextWord() throws IOException {
        try {
            return next(checkCharForWord);
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("No more words here");
        }
    }

    public int nextInt() throws IOException {
        try {
            String number = next(checkNotWhitespace);
            Character c = Character.toLowerCase(number.charAt(number.length() - 1));
            if (c.equals('o')) {
                if (number.charAt(0) == '-') {
                    return -(Integer.parseUnsignedInt(number.substring(1, number.length() - 1), 8));
                } else {
                    return Integer.parseUnsignedInt(number.substring(0, number.length() - 1), 8);
                }
            } else {
                return Integer.parseInt(number);
            }
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("No more ints here");
        }
    }

    public String nextLine() throws IOException {
        try {
            return next(checkIsLineSeparator);
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("No more lines here");
        }
    }

    public boolean hasNextWord() throws IOException {
        this.read();
        while (read > 0) {
            for (int i = index; i < read; i++) {
                if (checkCharForWord(buffer[i])) {
                    return true;
                }
            }
            index = read;
            this.read();
        }
        return false;
    }

    public boolean hasNextInt() throws IOException {
        this.read();
        while (read > 0) {
            for (int i = index; i < read; i++) {
                if (Character.isDigit(buffer[i]) || buffer[i] == '-') {
                    return true;
                }
            }
            index = read;
            this.read();
        }
        return false;
    }

    public boolean hasNextLine() throws IOException {
        return this.read() > 0;
    }

    public void close() {
        try {
            reader.close();
        } catch (IOException e) {
            System.out.println("Close exception: " + e.getMessage());
        }
    }
}