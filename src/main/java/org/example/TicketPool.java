package org.example;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class TicketPool {
    // Used Collections.synchronizedList to store the tickets
    private final List<String> ticketPool = Collections.synchronizedList(new LinkedList<>());
    private final int maxTicketCapacity; // The max ticket count system can hold at a given time
    private final int totalTickets; // Total tickets that will be issued in total by all vendors
    private int ticketsReleased = 0; // Tracks total tickets released so far

    public TicketPool(int maxTicketCapacity, int totalTickets) {
        this.maxTicketCapacity = maxTicketCapacity;
        this.totalTickets = totalTickets;
    }
    public int getTicketsReleased() {
        return ticketsReleased;
    }
    public int getTotalTickets() {
        return totalTickets;
    }


    // Used by vendor to add tickets
    public synchronized void addTickets(int ticketsPerRelease, String vendorId, Ticket ticket) {

        // when the total ticket count is reached stop the vendor threads from issuing tickets.
        if (ticketsReleased == totalTickets) {
            return;
        }

        // Calculate the maximum number of tickets the vendor can release at a given time according to the pool capacity and assigned count of tickets
        int ticketsRemaining = totalTickets - ticketsReleased; // Let's say we have 100 total tickets and 98 released --( 2 = 100 - 98 )
        int ticketsToRelease = Math.min(ticketsPerRelease, ticketsRemaining); // Vendor's normal release rate is 4, he can't release that amount, so that's why we take the min - 2
        ticketsToRelease = Math.min(ticketsToRelease, maxTicketCapacity - ticketPool.size());

        // Notify vendor that no tickets can be added due to capacity constraints
        if (ticketPool.size() == maxTicketCapacity) {
            System.out.println("Vendor " + vendorId + ": Max capacity reached!");

            try {
                wait(); // Wait for space in the pool to become available
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Vendor " + vendorId + " interrupted while waiting.");
            }
            return;
        }

        for (int i = 0; i < ticketsToRelease; i++) {
            ticketPool.add("Ticket-" + (ticketsReleased + 1)); // Add a unique ticket number
            ticketsReleased++;
        }

        System.out.println("Vendor " + vendorId + " released " + ticketsToRelease + " tickets." + ticket + " Tickets left in pool: " + ticketPool.size() + " | Total Released: " + ticketsReleased);
        notifyAll();
    }


    //Used by customers to buy tickets
    public synchronized void removeTicket(String customerId) {
        if (ticketPool.isEmpty()) {
            System.out.println("Customer " + customerId + ": No tickets available. Customer waiting....");
            return;
       }

        String ticket = ticketPool.remove(0);
        notifyAll();
        System.out.println("Customer " + customerId + " purchased " + ticket + ". Tickets left in pool: " + ticketPool.size());


        //Stop and end the system when all totalTickets are purchased by customers
        if (ticketPool.size() == 0){
            System.out.println("Ticket issuing is over! THANK YOU");
            System.exit(0);
        }
    }
}
