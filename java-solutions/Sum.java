
public class Sum {
    public static void main(String[] args) {
        String[] num = String.join(" ", args).split("\\p{javaWhitespace}+");
        int sum = 0;
        for (int i = 0; i < num.length; i++) {
            if (!num[i].isEmpty()) {
                sum += Integer.parseInt(num[i]);
            }
        }
        System.out.println(sum);
    }
}