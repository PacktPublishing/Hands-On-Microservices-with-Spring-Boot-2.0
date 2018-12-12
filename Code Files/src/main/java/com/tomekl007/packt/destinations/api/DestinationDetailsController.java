package com.tomekl007.packt.destinations.api;


import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.cache.RemovalListener;
import com.google.common.cache.RemovalNotification;
import com.tomekl007.packt.booking.service.FlightBookingService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.metrics.annotation.Timed;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.core.MediaType;
import java.util.concurrent.TimeUnit;

@RestController
public class DestinationDetailsController {
  private static Logger log = Logger.getLogger(DestinationDetailsController.class);


  private TravelDestinationsDetails travelDestinationsDetails;

  private LoadingCache<String, String> loader =
      CacheBuilder
          .newBuilder()
          .maximumSize(1) //on prod should be higher
          .expireAfterWrite(10, TimeUnit.SECONDS)
          .removalListener((RemovalListener<String, String>) removalNotification -> {
            log.info("entry from cache expired: " + removalNotification);
          })
          .build(
              new CacheLoader<String, String>() {
                @Override
                public String load(String key) {
                  return travelDestinationsDetails.getInfoAboutCity(key);
                }
              });

  @Autowired
  public DestinationDetailsController(TravelDestinationsDetails travelDestinationsDetails) {
    this.travelDestinationsDetails = travelDestinationsDetails;
  }

  //http://localhost:8080/travel/destination-details/MADRID
  @Timed
  @RequestMapping(value = "/travel/destination-details/{city}", produces = MediaType.APPLICATION_JSON)
  String getInfoAboutDestination(@PathVariable final String city) throws Exception {
    return loader.get(city);
  }
}