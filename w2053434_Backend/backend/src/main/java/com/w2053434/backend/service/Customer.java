package com.w2053434.backend.service;

public class Customer implements Runnable {
    private final TicketPool ticketPool;
    private final int customerRetrievalRate;  // Time to wait before attempting to buy a ticket
    private final int customerId;             // To uniquely identify each customer
    private final TicketingSystemService service;

    public Customer(TicketPool ticketPool, int customerRetrievalRate, int customerId, TicketingSystemService service) {
        this.ticketPool = ticketPool;
        this.customerRetrievalRate = customerRetrievalRate;
        this.customerId = customerId;
        this.service = service;
    }

    @Override
    public void run() {
        while (service.isRunning()) {
            try {
                // Buying a ticket
                String ticket = ticketPool.removeTicket();
                if (ticket != null) {
                    System.out.println("Customer " + customerId + " purchased " + ticket);
                    service.recordSale(customerId, ticket);
                    Thread.sleep(customerRetrievalRate* 1000);
                } else {
                    // If no tickets are available, logs that no tickets are left
                    System.out.println("No tickets available.");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
