package com.w2053434.backend.service;

import com.w2053434.backend.config.SystemConfig;
import com.w2053434.backend.model.TicketSale;
import com.w2053434.backend.repository.TicketSaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class TicketingSystemService {
    private final SystemConfig systemConfig;
    private final List<Thread> threads = new ArrayList<>();
    private volatile boolean isRunning = true;

    @Autowired
    public TicketingSystemService(SystemConfig systemConfig) {
        this.systemConfig = systemConfig;
    }

    public void startTicketingSystem() {
        isRunning = true;
        TicketPool ticketPool = new TicketPool(systemConfig.getMaxTicketCapacity());

        // Start vendor threads
        Vendor[] vendors = new Vendor[7];
        for (int i = 0; i < vendors.length; i++) {
            vendors[i] = new Vendor(ticketPool, systemConfig.getTicketReleaseRate(), i + 1, this);
            Thread vendorThread = new Thread(vendors[i], "Vendor ID-" + i);
            vendorThread.start();
        }

        // Start customer threads
        Customer[] customers = new Customer[3];
        for (int i = 0; i < customers.length; i++) {
            customers[i] = new Customer(ticketPool, systemConfig.getCustomerRetrievalRate(), i + 1, this);
            Thread customerThread = new Thread(customers[i], "Customer ID-" + i);
            customerThread.start();
        }
    }

    public void stopTicketingSystem() {
        isRunning = false; // A signal for threads to stop
        for (Thread thread : threads) {
            thread.interrupt();
            System.out.println("Ticketing system stopped.");
        }
        threads.clear();
    }

    public boolean isRunning() {
        return isRunning;
    }

    @Autowired // to record ticket sales data into the database
    private TicketSaleRepository ticketSaleRepository;

    public void recordSale(int customerId, String ticket) {
        TicketSale sale = new TicketSale(customerId, ticket, LocalDateTime.now());
        ticketSaleRepository.save(sale);
    }
}
