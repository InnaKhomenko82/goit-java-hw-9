import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;


public class Words {
    private static  String PATH = "src\\main\\resources\\words.txt";

    public static void main(String[] args){
        File file = new File(PATH);
        System.out.println("\nСчитываем данные из файла:\n" + file);
        isExist (file);
        countQuantity (readFile (file));
    }

    private static void isExist (File file) {
        if (!file.exists()) {
            System.out.println("Не найден файл " + file.getName());
        }
    }
    private static String[] readFile (File file){
        StringBuilder builder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            String words = bufferedReader.readLine();
            while (words != null) {
                builder.append(words.trim());
                builder.append(" ");
                words = bufferedReader.readLine();
            }
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

        String words = builder.toString();
        System.out.println("\nФайл содержит текст:\n" + words);
        String [] array = words.split(" ");
       // System.out.println(Arrays.toString(array));
        return array;
    }

    private static void countQuantity (String [] array){

        Map<String, Integer> map = new HashMap<>();
        for (int i=0; i<array.length; i++) {
            if (map.containsKey(array[i])){
                map.put(array[i], map.get(array[i])+1);
            } else {
                map.put(array[i], 1);
            }
        }
        System.out.println("\nЧастота каждого слова в файле:");
        System.out.println(map);
    }
}
