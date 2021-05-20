import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;


public class Words {
    private static  String PATH = "words.txt";

    public static void main(String[] args){
        File file = new File(PATH);
        System.out.println("\nСчитываем данные из файла:\n" + file);
        System.out.println(countQuantity (readFile (file)));
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
            System.err.println(ioException.getMessage());
        }

        String words = builder.toString();
        System.out.println("\nФайл содержит текст:\n" + words);
        String [] array = words.split(" ");
        // System.out.println(Arrays.toString(array));
        return array;
    }

    private static Map<String, Integer> countQuantity (String [] array){

        Map<String, Integer> map = new HashMap<>();
        for (int i=0; i<array.length; i++) {
            if (map.containsKey(array[i])){
                map.put(array[i], map.get(array[i])+1);
            } else {
                map.put(array[i], 1);
            }
        }
        System.out.println("\nЧастота каждого слова в файле:");
        return map.entrySet().stream()
               .sorted(Map.Entry.<String, Integer> comparingByValue().reversed())
               .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
    }
}