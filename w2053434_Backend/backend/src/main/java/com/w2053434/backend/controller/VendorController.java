package com.w2053434.backend.controller;
import com.w2053434.backend.model.Vendor;
import com.w2053434.backend.repository.VendorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
public class VendorController {

    @Autowired
    private VendorRepository vendorRepository;

    @PostMapping("/vendor") // to save new vendor info
    Vendor newVendor(@RequestBody Vendor newVendor) {
        return vendorRepository.save(newVendor);
    }

    @GetMapping("/vendors") // Getting ticket info and returning all tickets (list)
    List<Vendor> getAllVendors() {
        return vendorRepository.findAll();
    } //findAll given by JPA

}

