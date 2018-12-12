package com.tomekl007.packt.booking.service;

import com.tomekl007.packt.booking.api.BookingService;
import com.tomekl007.packt.booking.infrastructure.configuration.BookingServiceSettings;
import com.tomekl007.packt.booking.domain.Travel;
import com.tomekl007.packt.booking.infrastructure.persistance.TravelRepository;
import com.tomekl007.packt.eventbus.api.EventBus;
import com.tomekl007.packt.eventbus.domain.Event;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
public class FlightBookingService implements BookingService {
    static Logger log = Logger.getLogger(FlightBookingService.class.getName());

    private final BookingServiceSettings bookingServiceSettings;
    private final TravelRepository travelRepository;
    private EventBus eventBus;

    @Autowired
    public FlightBookingService(BookingServiceSettings bookingServiceSettings,
                                TravelRepository travelRepository,
                                EventBus eventBus) {
        this.bookingServiceSettings = bookingServiceSettings;
        this.travelRepository = travelRepository;
        this.eventBus = eventBus;
    }

    @Override
    public boolean book(Travel travel) {
        if (bookingServiceSettings.getSupportedDestinations().contains(travel.getDestination())) {
            travelRepository.save(travel);
            eventBus.publish(new Event("SAVED", "Saved travel " + travel));
            return true;
        }
        eventBus.publish(new Event("REJECTED", "Rejected travel " + travel));
        return false;
    }

    @PostConstruct
    public void init() {
        log.info("in init method");
    }

    @PreDestroy
    public void cleanup() {
        log.info("in cleanup method. Possible to release some resources");
    }
}
