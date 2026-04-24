package com.pluralsight;

import java.awt.*;
import java.io.*;
import java.util.*;
import java.util.Random;


public class AdventureTime
{
    static HashMap<Integer, StepClass> adventureSteps;
    static Scanner scanner = new Scanner(System.in);

    static void main()
    {
        adventureSteps = loadAdventure();

        // start the application
        homeScreen();
    }

    public static void homeScreen()
    {
        System.out.println("Welcome to Adventure Time!");
        System.out.println("--------------------------");
        System.out.println();
        System.out.println("(P)lay");
        System.out.println("(Q)uit");
        System.out.print("Make a selection: ");

        String choice = scanner.nextLine().toUpperCase().strip();

        switch (choice)
        {
            case "P":
                gameScreen(1);
                break;
            case "Q":
                System.out.println("Goodbye!");
                break;
            default:
                System.out.println("Invalid selection. Please try again.");
                homeScreen();
        }

    }

    public static  void gameScreen(int id)
    {
        // stop the game if the id is -1 (end of the game)
        if(id == -1) return;

        // 1 - finding the step

        StepClass step = findStep(id);

        if (step == null)
        {
            System.out.println();
            System.out.println("An error occurred. The step was not found.");
        }
        else
        {
            // 2 - display the step info
            System.out.println();
            System.out.println(Colors.TRON.printWithColor(step.getStoryText()));
            System.out.println();
            System.out.println(Colors.AMBER.printWithColor("1) " + step.getOption1Text()));
            System.out.println(Colors.CRIMSON.printWithColor("2) " + step.getOption2Text()));
            System.out.print("Make a selection: ");
            String userInput = scanner.nextLine().strip().toLowerCase();

            switch (userInput)
            {
                case "1":
                    gameScreen(step.getOption1NextStepId());
                    break;
                case "2":
                    gameScreen(step.getOption2NextStepId());
                    break;
            }
        }


    }

    public static StepClass findStep(int id)
    {
        if (adventureSteps.containsKey(id))
        {
            return adventureSteps.get(id);
        }
        return null;
    }

    public static HashMap<Integer, StepClass> loadAdventure()
    {
        // create the container
        // ArrayLists grow as needed when you add new items
        HashMap<Integer, StepClass> steps = new HashMap<>();

        // populate the container
        try {
            FileReader fileReader = new FileReader("adventure1.csv");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = bufferedReader.readLine();
            line = bufferedReader.readLine();
            while (line != null)
            {
                String[] columns = line.split("\\|");

                int id = Integer.parseInt(columns[0]);
                String text = columns[1];
                String option1Text = columns[2];
                int option1NextId = Integer.parseInt(columns[3]);
                String option2Text = columns[4];
                int option2NextId = (columns[5].equals("END")) ? -1 : Integer.parseInt(columns[5]);

                // create a Step from the data in the current line
                StepClass step = new StepClass(id, text, option1Text, option1NextId, option2Text, option2NextId);

                // add the current step to the container (ArrayList)
                steps.put(step.getId(),step);


                // read the next line
                line = bufferedReader.readLine();
            }
        }
        catch(Exception ex)
        {
            System.err.println(ex);
        }

        // return the container
        return steps;
    }
}