package com.pluralsight;

import java.awt.*;
import java.io.*;
import java.util.*;

public class AdventureTime {
    static void main(String[] args) {
        for(StepClass steps : loadAdventure()) {
            System.out.println(Colors.TRON.printWithColor(String.valueOf(steps)));
        }
    }

    public static ArrayList<StepClass> loadAdventure() {
        ArrayList<StepClass> steps = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader("adventure1.csv"));
            String line = reader.readLine(); // Skips first line
            while((line = reader.readLine()) != null) {
                //System.out.println(Colors.TRON.printWithColor(line));
                String[] columns = line.split("\\|");
                int id = Integer.parseInt(columns[0]);
                String storyText = columns[1];
                String option1Text = columns[2];
                int option1NextStepId = Integer.parseInt(columns[3]);
                String option2Text = columns[4];
                int option2NextStepId = columns[5].equals("END") ? -1 : Integer.parseInt(columns[5]);

                StepClass stepCLass = new StepClass(id, storyText, option1Text, option1NextStepId, option2Text, option2NextStepId);
                steps.add(stepCLass);
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("Something went wrong");
        }

        return steps;
    }
}
