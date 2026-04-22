package com.pluralsight;

import java.io.*;
import java.util.*;

public class AdventureTime {
    static void main(String[] args) {
        loadAdventure();
    }

    public static void loadAdventure() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("adventure1.csv"));
            String line = reader.readLine(); // Skips first line
            while((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("Something went wrong");
        }
    }
}
