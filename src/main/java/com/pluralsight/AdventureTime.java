package com.pluralsight;

import java.awt.*;
import java.io.*;
import java.util.*;
import java.util.Random;


public class AdventureTime {
    static final Scanner scanner = new Scanner(System.in);
    static void main(String[] args) {
        homeScreen();

    }

    public static void homeScreen() {
        Random random = new Random();
        System.out.println("Welcome to Adventure Time.");
        String message = ("""
                -----------------------------------
                Press (P) to play
                Press (Q) to quit
                Your choice:\s""");
        System.out.print(message);
        String choice = scanner.nextLine().strip();
        while(true) {
            if(choice.equalsIgnoreCase("Q")) {
                break;
            } else if(choice.equalsIgnoreCase("P")) {
                gameScreen(random.nextInt(24) + 1);
            }
        }
    }

    public static void gameScreen(int id) {
        // 1- finding the step
        int nextId = id;

        while(nextId != -1) {

            StepClass step = findStep(nextId);
            // Display the step info
            System.out.println();
            System.out.println(Colors.TRON.printWithColor(step.getStoryText()));
            System.out.println();
            System.out.println(Colors.CRIMSON.printWithColor("1) " + step.getOption1Text()));
            System.out.println(Colors.AMBER.printWithColor("2) " + step.getOption2Text()));
            System.out.print("\nMake a selection: ");
            String userInput = scanner.nextLine().strip().toLowerCase();

            switch(userInput) {
                case "1" -> nextId = step.getOption1NextStepId();
                case "2" -> nextId = step.getOption2NextStepId();

            }
        }
    }

    public static StepClass findStep(int id) {
        for(int i = 0; i < loadAdventure().size(); i++) {
            StepClass step = loadAdventure().get(i);
            if(step.getId() == id) {
                return step;
            }
        }
        return null;
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
