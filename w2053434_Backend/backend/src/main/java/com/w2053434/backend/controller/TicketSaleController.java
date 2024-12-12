package com.w2053434.backend.controller;
import com.w2053434.backend.exception.TicketNotFoundException;
import com.w2053434.backend.model.TicketSale;
import com.w2053434.backend.repository.TicketSaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/api/ticket-sales")
public class TicketSaleController {

    private final TicketSaleRepository ticketSaleRepository;

    @Autowired
    public TicketSaleController(TicketSaleRepository ticketSaleRepository) {
        this.ticketSaleRepository = ticketSaleRepository;
    }

    @GetMapping //to fetch all purchased ticket info
    List<TicketSale> getAllTicketSales(){
        return ticketSaleRepository.findAll();
    }

    @GetMapping("/{id}") //to fetch all purchased ticket info by ticket_sales record id
    TicketSale getTicketById(@PathVariable Long id) {
        return ticketSaleRepository.findById(id)
                .orElseThrow(() -> new TicketNotFoundException(id));
    }
}

