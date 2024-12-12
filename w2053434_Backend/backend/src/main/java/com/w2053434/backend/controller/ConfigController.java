package com.w2053434.backend.controller;
import com.w2053434.backend.config.SystemConfig;
import com.w2053434.backend.service.TicketingSystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/config")
public class ConfigController {

    private final SystemConfig systemConfig;
    private final TicketingSystemService ticketingSystemService;

    @Autowired// Injects the `SystemConfig` bean
    public ConfigController(SystemConfig systemConfig, TicketingSystemService ticketingSystemService) {
        this.systemConfig = systemConfig;
        this.ticketingSystemService = ticketingSystemService;
    }

    @PostMapping("/set") // to provide system configuration inputs
    public String setConfiguration(@RequestBody SystemConfig newConfig) {
        System.out.println("Received Config: " + newConfig);
        systemConfig.setTotalTickets(newConfig.getTotalTickets());
        systemConfig.setTicketReleaseRate(newConfig.getTicketReleaseRate());
        systemConfig.setCustomerRetrievalRate(newConfig.getCustomerRetrievalRate());
        systemConfig.setMaxTicketCapacity(newConfig.getMaxTicketCapacity());
        return "Configuration updated successfully.";
    }

    @GetMapping("/get") // to receive the system configuration inputs entered
    public SystemConfig getConfiguration() {
        return systemConfig;
    }

    @PostMapping("/start-ticketing") //to start the ticketing system
    public String startTicketing() {
        ticketingSystemService.startTicketingSystem();
        return "Ticketing system started with vendor and customer threads.";
    }

    @PostMapping("/stop-ticketing") //to stop the ticketing system
    public String stopTicketing() {
        ticketingSystemService.stopTicketingSystem();
        System.out.println("Ticketing system has stopped");
        return "Ticketing system stopped.";
    }
}


