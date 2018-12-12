package com.tomekl007.packt.booking.infrastructure.security;

import com.tomekl007.packt.booking.infrastructure.persistance.TravelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecretController {

    @Autowired
    private TravelRepository travelRepository;

    @RequestMapping("/secret")
    public Long numberOfTravels() {
        return travelRepository.count();
    }
}
