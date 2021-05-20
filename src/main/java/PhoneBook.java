import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class PhoneBook{
    private static final String PATH = "file1.txt";

    public static void main(String[] args) {
        File file = new File(PATH);
        System.out.println("\nСчитываем данные из файла:\n" + file);
        System.out.println("\nВалидные номера телефонов:");
        readFileOnBuffer(file);
    }

    private static void readFileOnBuffer (File file){
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            String number = bufferedReader.readLine();
            while (number != null) {
                if (validNumber(number)) {
                    System.out.println(number);
                }
                number = bufferedReader.readLine();
            }
        } catch (IOException ioException) {
            System.err.println(ioException.getMessage());
        }
    }

    private static boolean validNumber(String numbers){
        String type1 = "\\([0-9]{3}\\) [0-9]{3}-[0-9]{4}";
        String type2 = "[0-9]{3}-[0-9]{3}-[0-9]{4}";
        return numbers.matches(type1) || numbers.matches(type2);
    }
}
