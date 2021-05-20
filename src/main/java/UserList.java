import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

public class UserList {
    private static final String PATH = "file2.txt";
    private static final String DESTINATION = "/temp/user.json";

    public static void main(String[] args) {
        File file = new File(PATH);
        System.out.println("\nСчитываем данные из файла:\n" + file);
        File destination = new File(DESTINATION);
        isExist(file);
        List<User> users = new LinkedList<>();
        readFileOnList(file, users);
        isCreated(destination);
        writeFileFromList(destination,users);
    }

    private static void readFileOnList(File file, List users){
        try (BufferedReader reader = new BufferedReader(new FileReader(file))){
            String line = reader.readLine();
            while (line != null) {
                String [] col = line.split(" ");
                if (!col[0].equals("name")&& !col[1].equals("age")){
                    users.add(new User(col[0], Integer.parseInt(col[1])));
                }
                line = reader.readLine();
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    private static void writeFileFromList(File destination, List <User> users){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(destination))){
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String dest = gson.toJson(users);
            System.out.println("\nДанные переведены в формат JSON");
            writer.write(dest);
            System.out.println("\nДанные записаны в файл " + destination.getName());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void isExist(File file) {
        if (!file.exists()) {
            System.out.println("Не найден файл " + file.getName());
        }
    }

    private static void isCreated(File file) {
        if (!file.exists()) {
            file.getParentFile().mkdir();
            try {
                file.createNewFile();
                System.out.println("\nСоздан файл " + file.getName());
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        }
    }
}

        class User {
        private String name;
        private int age;

        public User(String name, int age) {
            this.name = name;
            this.age = age;
        }

    }
