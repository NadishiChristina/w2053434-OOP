package com.w2053434.backend.model;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "Vendor")
public class Vendor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String vendorName;
    private String eventname;
    private BigDecimal price;

    // Getters and Setters
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public String getVendorName() {
        return vendorName;
    }
    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public String getEventname() {
        return eventname;
    }
    public void setEventname(String eventname) {
        this.eventname = eventname;
    }

    public BigDecimal getPrice() {
        return price;
    }
    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}





