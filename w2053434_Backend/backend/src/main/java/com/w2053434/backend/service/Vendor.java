package com.w2053434.backend.service;

public class Vendor implements Runnable {
    private final TicketPool ticketPool;
    private final int ticketReleaseRate;  // Time to wait before releasing the next ticket
    private final int vendorId;           // To uniquely identify each vendor
    private final TicketingSystemService service;

    public Vendor(TicketPool ticketPool, int ticketReleaseRate, int vendorId, TicketingSystemService service) {
        this.ticketPool = ticketPool;
        this.ticketReleaseRate = ticketReleaseRate;
        this.vendorId = vendorId; // Store the vendorId
        this.service = service;
    }

    @Override
    public void run() {
        while (service.isRunning()) {
            try {
                // Create a new ticket with vendorId included
                String ticket = "Ticket_" + vendorId + "_" + System.currentTimeMillis();
                ticketPool.addTickets(ticket);
                // Vendor releases a ticket
                System.out.println("Vendor " + vendorId + " released " + ticket);
                Thread.sleep(ticketReleaseRate* 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
