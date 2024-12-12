package com.w2053434.backend.service;
import java.util.concurrent.ConcurrentLinkedQueue;

public class TicketPool {
    private final ConcurrentLinkedQueue<String> ticketQueue = new ConcurrentLinkedQueue<>();
    private final int maxCapacity;
    private boolean messageLogged = false;

    public TicketPool(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    public synchronized void addTickets(String ticket) {
        while (ticketQueue.size() >= maxCapacity) {
            try {
                if (!messageLogged) { //to print message only once
                    System.out.println("Ticket pool is full, vendor is waiting...");
                    messageLogged = true;
                }
                wait(); // Wait until space is available
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }
        ticketQueue.offer(ticket);
        messageLogged = false;
        notifyAll(); // Notify waiting threads
    }

    public synchronized String removeTicket() {
        while (ticketQueue.isEmpty()) {
            try {
                System.out.println("No tickets available, waiting for vendor...");
                wait(); // Wait until a ticket becomes available
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return null;
            }
        }
        String ticket = ticketQueue.poll();
        notifyAll();
        return ticket;
    }

}

