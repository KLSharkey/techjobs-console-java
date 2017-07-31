package org.launchcode.techjobs.console;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;
import java.util.*;
/**
 * Created by LaunchCode
 */
public class TechJobs {

    private static Scanner in = new Scanner(System.in);

    public static void main(String[] args) {

        // Initialize our field map with key/name pairs
        HashMap<String, String> columnChoices = new HashMap<>();
        columnChoices.put("core competency", "Skill");
        columnChoices.put("employer", "Employer");
        columnChoices.put("location", "Location");
        columnChoices.put("position type", "Position Type");
        columnChoices.put("all", "All");

        // Top-level menu options
        HashMap<String, String> actionChoices = new HashMap<>();
        actionChoices.put("search", "Search");
        actionChoices.put("list", "List");

        System.out.println("Welcome to LaunchCode's TechJobs App!");

        // Allow the user to search until they manually quit
        while (true) {

            String actionChoice = getUserSelection("View jobs by:", actionChoices);

            if (actionChoice.equals("list")) {

                String columnChoice = getUserSelection("List", columnChoices);

                if (columnChoice.equals("all")) {
                    //ArrayList<String> orderedJobs = JobData.findAll();

                    printJobs(JobData.findAll());
                } else {

                    ArrayList<String> results = JobData.findAll(columnChoice);
                    //ArrayList<String> orderedJobs = Collections.sort(results);
                    System.out.println("\n*** All " + columnChoices.get(columnChoice) + " Values ***");
                    Collections.sort(results); // added in to sort ArrayList(results) alpha before printing

                    // Print list of skills, employers, etc
                    for (String item : results) {
                        System.out.println(item);
                    }
                }

            } else { // choice is "search"

                // How does the user want to search (e.g. by skill or employer)
                String searchField = getUserSelection("Search by:", columnChoices);

                // What is their search term?
                System.out.println("\nSearch term: ");
                String searchTerm = in.nextLine(); //gets there search term

                if (searchField.equals("all")) {
                    printJobs(JobData.findByValue(searchTerm));
                    //System.out.println("Search all fields not yet implemented.");
                } else {
                    printJobs(JobData.findByColumnAndValue(searchField, searchTerm)); //field and term entered
                }
            }
        }
    }


    // ﻿Returns the key of the selected item from the choices Dictionary
    private static String getUserSelection(String menuHeader, HashMap<String, String> choices) {

        Integer choiceIdx;
        Boolean validChoice = false;
        String[] choiceKeys = new String[choices.size()];

        // Put the choices in an ordered structure so we can
        // associate an integer with each one
        Integer i = 0;
        for (String choiceKey : choices.keySet()) {
            choiceKeys[i] = choiceKey;
            i++;
        }

        do {

            System.out.println("\n" + menuHeader);

            // Print available choices
            for (Integer j = 0; j < choiceKeys.length; j++) {
                System.out.println("" + j + " - " + choices.get(choiceKeys[j]));
            }

            choiceIdx = in.nextInt();
            in.nextLine();

            // Validate user's input
            if (choiceIdx < 0 || choiceIdx >= choiceKeys.length) {
                System.out.println("Invalid choice. Try again.");
            } else {
                validChoice = true;
            }

        } while (!validChoice);

        return choiceKeys[choiceIdx];
    }

    // Print a list of jobs
    private static void printJobs(ArrayList<HashMap<String, String>> someJobs) {
        //ArrayList jobLists = new ArrayList<HashMap<String, String>>;

        //System.out.println("printJobs is not implemented yet");
        if (someJobs.size() == 0) {
            System.out.print("No jobs available");
        }else {
                for (Map<String, String> entry : someJobs) {
                    System.out.println("*****");
                    for (Map.Entry<String, String> myValues : entry.entrySet()) {
                        System.out.println(myValues.getKey() + ":" + myValues.getValue());
                    }
                    System.out.println("*****"+"\n");
                }



            }
        }


    }