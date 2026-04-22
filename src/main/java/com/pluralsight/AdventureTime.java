package com.pluralsight;

import java.awt.*;
import java.io.*;
import java.util.*;


public class AdventureTime {
    static void main(String[] args) {
        homeScreen();

    }

    public static void homeScreen() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to Adventure Time.");
        String message = ("""
                -----------------------------------
                Press (P) to play
                Press (Q) to quit
                Your choice:\s""");
        System.out.print(message);
        String choice = scanner.nextLine();
        while(true) {
            if(choice.equalsIgnoreCase("Q")) {
                break;
            }
            gameScreen(2);
            System.out.print(message);
            choice = scanner.nextLine();

        }
    }

    public static void gameScreen(int id) {
        for(StepClass step : loadAdventure()) {
            if(step.getId() == id) {
                System.out.println("Story text: " + Colors.TRON.printWithColor(step.getStoryText()) + "\n");
                System.out.println("1) " + Colors.CRIMSON.printWithColor(step.getOption1Text()) + "\n");
                System.out.println("2) " + Colors.AMBER.printWithColor(step.getOption2Text()) + "\n");
            }
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
