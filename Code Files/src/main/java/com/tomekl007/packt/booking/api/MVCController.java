package com.tomekl007.packt.booking.api;

import com.tomekl007.packt.booking.domain.TravelDto;
import com.tomekl007.packt.booking.domain.Travel;
import com.tomekl007.packt.booking.infrastructure.persistance.TravelRepository;
import com.tomekl007.packt.eventbus.api.EventBus;
import com.tomekl007.packt.eventbus.domain.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MVCController {

  @Autowired
  private TravelRepository travelRepository;

  @Autowired
  private EventBus eventBus;

  @RequestMapping("/mvc/all-travels")
  public String indexView(Model model) {
    model.addAttribute("list", travelRepository.findAll());
    return "allTravels";
  }

  @PostMapping("/mvc/travel")
  public String travelSubmit(@ModelAttribute TravelDto travelDto, Model model) {
    travelRepository.save(new Travel(travelDto.getUserId(), travelDto.getSource(), travelDto.getDestination()));
    eventBus.publish(new Event("SAVE", "Save travel " + travelDto));
    model.addAttribute("list", travelRepository.findAll());
    return "allTravels";
  }

  @GetMapping("/mvc/createTravel")
  public String travelForm(Model model) {
    model.addAttribute("travelDto", new TravelDto());
    return "create";
  }
}
