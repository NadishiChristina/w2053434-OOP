package org.example;
public class Customer implements Runnable {
    private final String customerId;
    private final int retrievalInterval;
    private final TicketPool ticketPool;

    public Customer(String customerId, int retrievalInterval, TicketPool ticketPool) {
        this.customerId = customerId;
        this.retrievalInterval = retrievalInterval;
        this.ticketPool = ticketPool;
    }

    public void run() {
        while (true) {
            try {
                Thread.sleep(retrievalInterval * 1000);  //Pausing the thread for this amount of time
                ticketPool.removeTicket(customerId); // customer buys ticket
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Customer " + customerId + " interrupted. Stopping.");
                break;
            }
        }
    }
}

