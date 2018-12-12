package com.tomekl007.packt.booking.api.rest;

import com.tomekl007.packt.booking.infrastructure.persistance.ReactiveTravelRepository;
import com.tomekl007.packt.booking.domain.TravelDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import javax.ws.rs.core.MediaType;
import java.util.List;

@RestController()
public class ReactiveController {

    private final ReactiveTravelRepository travelRepository;

    @Autowired
    public ReactiveController(ReactiveTravelRepository travelRepository) {
        this.travelRepository = travelRepository;
    }

    @GetMapping("/reactive/travel/{userId}")
    public Mono<List<TravelDto>> getTravels(@PathVariable final String userId) {
        return travelRepository.getTravels(userId);
    }

    @PostMapping(value = "/reactive/travel", consumes = MediaType.APPLICATION_JSON)
    public Mono<TravelDto> addTravel(@RequestBody TravelDto travel) {
        return travelRepository.addTravel(travel);
    }

}
