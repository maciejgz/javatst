package pl.mg.javatst.codewars;

public class CreatePhoneNumber {

    public static void main(String[] args) {
        System.out.println(createPhoneNumber(new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 0}));
    }

    public static String createPhoneNumber(int[] numbers) {
        StringBuilder builder = new StringBuilder();
        builder.append("(");
        for (int i = 0; i < 3; i++) {
            builder.append(numbers[i]);
        }
        builder.append(") ");
        for (int i = 3; i < 6; i++) {
            builder.append(numbers[i]);
        }
        builder.append("-");
        for (int i = 6; i < 10; i++) {
            builder.append(numbers[i]);
        }
        return builder.toString();
    }
}
