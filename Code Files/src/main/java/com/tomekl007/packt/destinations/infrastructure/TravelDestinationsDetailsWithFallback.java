package com.tomekl007.packt.destinations.infrastructure;

import com.google.common.cache.CacheLoader;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.tomekl007.packt.destinations.api.TravelDestinationsDetails;
import com.tomekl007.packt.eventbus.api.EventBus;
import com.tomekl007.packt.eventbus.domain.Event;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@Component
@Profile("!integration")
public class TravelDestinationsDetailsWithFallback implements TravelDestinationsDetails {
  static Logger log = Logger.getLogger(TravelDestinationsDetailsWithFallback.class.getName());
  //call for info about a capitol
  //https://restcountries.eu/rest/v2/capital/MADRID
  //http://localhost:8080/travel/destination-details/BARCELONA - result in close circuit
  private final RestTemplate restTemplate;
  private EventBus eventBus;

  @Autowired
  public TravelDestinationsDetailsWithFallback(
      RestTemplate rest,
      EventBus eventBus) {
    this.restTemplate = rest;
    this.eventBus = eventBus;
  }

  @HystrixCommand(fallbackMethod = "reliable")
  @Override
  public String getInfoAboutCity(String cityName) {
    return request(cityName);
  }

  public String request(String cityName) {
    URI uri = URI.create("https://restcountries.eu/rest/v2/capital/" + cityName);

    return this.restTemplate.getForObject(uri, String.class);
  }

  public String reliable(String cityName) {
    return "Info about city: " + cityName + " is not currently not available";
  }

  @Scheduled(fixedRate = 1000)
  public void processEvents() {
    Event event = eventBus.receive();
    if (event != null) {
      log.info("received event to process :" + event);
    }

  }
}
