package ru.ratanov.kinoman;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

public class Test {

    private void test() throws FileNotFoundException {
        BufferedReader reader = new BufferedReader(new FileReader(""));

        Gson gson = new GsonBuilder().create();

        List<ItemView> items = gson.fromJson(reader, new TypeToken<List<String>>() {}.getType());
    }
}
