package org.example;
public class Vendor implements Runnable {
    private final String vendorId;
    private final Ticket ticket;
    private final int ticketsPerRelease;
    private final int releaseInterval;
    private final TicketPool ticketPool;

    public Vendor(String vendorId, Ticket ticket, int ticketsPerRelease, int releaseInterval, TicketPool ticketPool) {
        this.vendorId = vendorId;
        this.ticket = ticket;
        this.ticketsPerRelease = ticketsPerRelease;
        this.releaseInterval = releaseInterval;
        this.ticketPool = ticketPool;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(releaseInterval * 1000); //Pausing the thread for this amount of time
                ticketPool.addTickets(ticketsPerRelease, vendorId, ticket); // vendor adds a ticket

                // Stop the vendor thread if all totalTickets have been released
                if (ticketPool.getTicketsReleased() == ticketPool.getTotalTickets()) {
                    System.out.println("Vendor " + vendorId + ": All tickets have been released.");
                    break;
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}

