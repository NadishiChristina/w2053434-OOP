package org.example;
import java.io.*;
import java.util.Scanner;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Configuration {
    //System Configuration Parameters
    private int totalTickets;
    private int ticketReleaseRate;
    private int customerRetrievalRate;
    private int maxTicketCapacity;

    public int getTotalTickets() {
        return totalTickets;
    }

    public int getTicketReleaseRate() {
        return ticketReleaseRate;
    }

    public int getCustomerRetrievalRate() {
        return customerRetrievalRate;
    }

    public int getMaxTicketCapacity() {
        return maxTicketCapacity;
    }


    //Method that prompts and performs validation for setting system parameters
    public void configureSystem() {
        System.out.println("WELCOME TO THE EVENT TICKETING SYSTEM!");
        Scanner scanner = new Scanner(System.in);

        totalTickets = getValidInput(scanner, "Enter Total Number of Tickets (Enter a value greater than 0): ");
        ticketReleaseRate = getValidInput(scanner, "Enter Ticket Release Rate (Enter a value greater than 0): ");
        customerRetrievalRate = getValidInput(scanner, "Enter Customer Retrieval Rate (Enter a value greater than 0): ");
        maxTicketCapacity = getValidBoundedInput(scanner, totalTickets);

        //Save all inputs into a json file
        saveConfigToFile("config.json");

        //Display the entered inputs on the console
        displayConfig();

        //Command to start the system
        startTicketingSystem();
    }

    // Method called to get valid input from user (positive integer)
    private int getValidInput(Scanner scanner, String prompt) {
        int value;
        while (true) {
            try {
                System.out.print(prompt);
                String input = scanner.nextLine().trim();
                value = Integer.parseInt(input); // Parse the input to an integer

                // Validating that the input is greater than 0
                if (value > 0) {
                    break; // Exit loop if input is valid
                } else {
                    System.out.println("Please enter a positive integer.");
                }
            } catch (NumberFormatException e) {
                // Handle non-integer inputs
                System.out.println("Invalid input. Please enter a valid integer.");
            }
        }
        return value;
    }


    // Method called to get valid bounded input for maximumTicketCapacity that is positive and <= totalTickets
    private int getValidBoundedInput(Scanner scanner, int upperLimit) {
        int value;
        while (true) {
            try {
                System.out.print("Enter Maximum Ticket Capacity (Enter a value greater than 0): ");
                String input = scanner.nextLine().trim();
                value = Integer.parseInt(input);// Parse the input to an integer

                if (value > 0 && value <= upperLimit) {
                    break; // Exit loop if input is valid
                } else {
                    System.out.println("Please enter a positive integer that is less than or equal to " + upperLimit + ".");
                }
            } catch (NumberFormatException e) {
                // Handle non-integer inputs
                System.out.println("Invalid input. Please enter a valid integer.");
            }
        }
        return value;
    }

    // Starting Command
    public void startTicketingSystem() {
        // Prompt user to provide start command
        System.out.println("Type 'start' to begin");
        Scanner scanner = new Scanner(System.in);

        while (true) {
            String input = scanner.nextLine().trim().toLowerCase();

            //Validating start command
            if ("start".equals(input)) {
                System.out.println("Starting the Ticketing System...");
                break;
            } else {
                System.out.println("Invalid input. Please type 'start':");
            }
        }
    }

    //Saving the new configuration inputs into JSON file
    public void saveConfigToFile(String fileName) {
        try (Writer writer = new FileWriter(fileName)) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(this, writer);
            System.out.println("Configuration saved to " + fileName);
        } catch (IOException e) {
            System.out.println("Error saving configuration: " + e.getMessage());
        }
    }

    // Load configuration from a JSON file
    public void loadConfigFromFile(String fileName) {
        try (Reader reader = new FileReader(fileName)) {
            Gson gson = new Gson();
            Configuration loadedConfig = gson.fromJson(reader, Configuration.class);

            // loading the existing previous values
            this.totalTickets = loadedConfig.totalTickets;
            this.ticketReleaseRate = loadedConfig.ticketReleaseRate;
            this.customerRetrievalRate = loadedConfig.customerRetrievalRate;
            this.maxTicketCapacity = loadedConfig.maxTicketCapacity;

            System.out.println("Configuration loaded from " + fileName);
        } catch (FileNotFoundException e) {
            System.out.println("Configuration file not found. Please configure the system.");
            configureSystem();
        } catch (IOException e) {
            System.out.println("Error loading configuration: " + e.getMessage());
        }
    }

    //Displaying the configuration inputs after entering
    public void displayConfig() {
        System.out.println("Configuration complete.");
        System.out.println("-------------------------------");
        System.out.println("Ticketing System Configuration:");
        System.out.println("-------------------------------");
        System.out.println("Total Number of Tickets: " + totalTickets);
        System.out.println("Ticket Release Rate: " + ticketReleaseRate);
        System.out.println("Customer Retrieval Rate: " + customerRetrievalRate);
        System.out.println("Maximum Ticket Capacity: " + maxTicketCapacity);
        System.out.println();
    }
}

