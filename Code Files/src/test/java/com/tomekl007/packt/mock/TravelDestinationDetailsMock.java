package com.tomekl007.packt.mock;


import com.tomekl007.packt.destinations.api.TravelDestinationsDetails;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.Map;

@Component
@Profile("integration")
public class TravelDestinationDetailsMock implements TravelDestinationsDetails {
  private final Map<String, String> info = new LinkedHashMap<>();

  public void addInfo(String key, String value){
    info.put(key, value);
  }

  @Override
  public String getInfoAboutCity(String cityName) {
    return info.get(cityName);
  }
}
