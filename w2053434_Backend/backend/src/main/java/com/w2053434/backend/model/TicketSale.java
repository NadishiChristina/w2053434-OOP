package com.w2053434.backend.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "ticket_sales")
public class TicketSale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int customerId;
    private String ticket;

    @Column(name = "purchase_time")
    private LocalDateTime purchaseTime;

    public TicketSale() {}

    public TicketSale(int customerId, String ticket, LocalDateTime purchaseTime) {
        this.customerId = customerId;
        this.ticket = ticket;
        this.purchaseTime = purchaseTime;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public int getCustomerId() { return customerId; }
    public void setCustomerId(int customerId) { this.customerId = customerId; }

    public String getTicket() { return ticket; }
    public void setTicket(String ticket) { this.ticket = ticket; }

    public LocalDateTime getPurchaseTime() { return purchaseTime; }
    public void setPurchaseTime(LocalDateTime purchaseTime) { this.purchaseTime = purchaseTime; }
}


