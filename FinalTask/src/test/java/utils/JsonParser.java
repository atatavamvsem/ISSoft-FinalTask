package utils;

import com.google.gson.Gson;

import java.io.*;

public class JsonParser {
    private final Gson gson;

    public JsonParser() {
        gson = new Gson();
    }

    public User[] readFromFile(File file) {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            return gson.fromJson(reader.readLine(), User[].class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}

