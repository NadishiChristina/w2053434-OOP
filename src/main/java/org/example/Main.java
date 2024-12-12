package org.example;
import java.math.BigDecimal;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Configuration config = new Configuration();

        // Load existing configuration or configure a new system
        Scanner scanner = new Scanner(System.in);
        String choice = "";

        while (true) {
            System.out.print("Do you want to load the existing configuration [Yes] or input new configuration [NO]? (yes/no): ");
            choice = scanner.nextLine().trim().toLowerCase();

            if (choice.equals("yes") || choice.equals("no")) {
                break;  // Exit the loop if the input is valid
            } else {
                System.out.println("Invalid input. Please enter 'yes' or 'no'."); // Re-prompt for valid input
            }
        }

        if (choice.equals("yes")) {
            config.loadConfigFromFile("config.json");
            config.displayConfig();
            config.startTicketingSystem();
        } else {
            config.configureSystem();
        }

        int totalTickets = config.getTotalTickets();
        TicketPool ticketPool = new TicketPool(config.getMaxTicketCapacity(), totalTickets);

        //Sample Ticket types
        Ticket ticket1 = new Ticket(1, "Musical", new BigDecimal(10000));
        Ticket ticket2 = new Ticket(2, "DanceShow", new BigDecimal(20000));


        // Sample Vendor Threads
        Vendor[] vendors = {
                new Vendor("A", ticket1, 2 , config.getTicketReleaseRate(), ticketPool),
                new Vendor("B", ticket2, 3, config.getTicketReleaseRate(), ticketPool),
                new Vendor("C", ticket2, 3, config.getTicketReleaseRate(), ticketPool),
                new Vendor("D", ticket1, 2, config.getTicketReleaseRate(), ticketPool)
        };

        // Sample Customer Threads
        Customer[] customers = {
                new Customer("1 Kelly", config.getCustomerRetrievalRate(), ticketPool),
                new Customer("2 Sam", config.getCustomerRetrievalRate(), ticketPool),
                new Customer("3 Kim", config.getCustomerRetrievalRate(), ticketPool),
                new Customer("4 John", config.getCustomerRetrievalRate(), ticketPool)
        };

        // Start vendor threads
        for (Vendor vendor : vendors) {
            new Thread(vendor).start();
        }

        // Start customer threads
        for (Customer customer : customers) {
            new Thread(customer).start();
        }

    }
}