import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;


public class Words {
    private static  String PATH = "src/main/resources/words.txt";

    public static void main(String[] args){
        File file = new File(PATH);
        System.out.println("\nСчитываем данные из файла:\n" + file);
        isExist (file);
        readFile (file);
        //countQuantity (readFile (file));
        countCompareQuantity(readFile (file));
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

    private static void countQuantitySort (String [] array){

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

    private static void countCompareQuantity (String [] array){
        ArrayList <Pair> pairs = new ArrayList();
        for (int i=0; i<array.length; i++){
            if (pairs.contains(new Pair(array[i], 55))){
                //pairs.remove(new Pair(array[i], 1));
                //pairs.add(new Pair(array[i], 5));
                pairs.add(new Pair(array[i], ((pairs.get(i).getValue()) +1)));

            } else {
                pairs.add(new Pair(array[i], 1));
            }
        }
/*
        pairs.add(new Pair(array[0], 1));
        pairs.add(new Pair(array[1], 2));
        pairs.add(new Pair(array[2], 5));
        pairs.add(new Pair(array[4], 8));

        System.out.println("TEST: ");
        System.out.println(array[2]);
        System.out.println(pairs.get(0).getKey() + " " + pairs.get(1).getKey() + " " + pairs.get(2).getKey());
        System.out.println(pairs.get(0).getValue() + " " + pairs.get(1).getValue() + " " + pairs.get(2).getValue());

        System.out.println(pairs.get(0));
        System.out.println(pairs.get(3));
        System.out.println(pairs.get(0).equals(pairs.get(3)));
        System.out.println(pairs.contains(new Pair(array[0], 5)));
        System.out.println((pairs.get(2).getValue()));
*/

        System.out.println("Несортированный список вхождений слов:");
        System.out.println(pairs);
        Collections.sort(pairs);
        System.out.println("Отсортированный список по количеству вхождений слов:");
        System.out.println(pairs);
    }
}

class Pair implements Comparable <Pair> {
    private String key;
    private Integer value;

    public Integer getValue() {
        return value;
    }

    public String getKey() {
        return key;
    }

    @Override
    public int compareTo(Pair pair) {
        return (pair.value - value);
    }

    public Pair(String key, Integer value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public String toString() {
        return key + " - " + value;
    }

    @Override
    public boolean equals(Object obj) {
        Pair p = (Pair) obj;
        return key.equals(p.key);
    }
}