public class SumOctal {
    public static void main(String[] args) {
        String[] num = String.join(" ", args).split("\\p{javaWhitespace}+");
        int sum = 0;
        for (int i = 0; i < num.length; i++) {
            if (!num[i].isEmpty()) {
                sum += Character.toLowerCase(num[i].charAt(num[i].length() - 1)) == 'o' ? 
                    Integer.parseInt(num[i].substring(0, num[i].length() - 1), 8) : Integer.parseInt(num[i]);
            }
        }
        System.out.println(sum);
    }
}