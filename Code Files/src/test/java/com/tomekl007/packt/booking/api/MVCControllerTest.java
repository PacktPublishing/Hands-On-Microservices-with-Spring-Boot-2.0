package com.tomekl007.packt.booking.api;

import com.tomekl007.packt.PacktApplication;
import com.tomekl007.packt.booking.domain.Travel;
import com.tomekl007.packt.booking.domain.TravelDto;
import com.tomekl007.packt.booking.infrastructure.persistance.TravelRepository;
import com.tomekl007.packt.booking.infrastructure.security.SpringSecurityWebAppConfig;
import com.tomekl007.packt.eventbus.api.EventBus;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.hamcrest.core.StringContains.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@ContextConfiguration(classes = {PacktApplication.class, SpringSecurityWebAppConfig.class})
@WebMvcTest(
    controllers = MVCController.class
)
public class MVCControllerTest {

  @MockBean
  private TravelRepository travelRepository;

  @MockBean
  private EventBus eventBus;

  @Autowired
  private MockMvc mockMvc;

  @Test
  public void shouldReturnAllAvailableTravels() throws Exception {
    //given
    Iterable<Travel> travels = Arrays.asList(new Travel("u1", "WARSAW", "PARIS"));
    when(travelRepository.findAll()).thenReturn(travels);

    //when, then
    this.mockMvc.perform(get("/mvc/all-travels")).andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().string(containsString("u1")))
        .andExpect(content().string(containsString("WARSAW")))
        .andExpect(content().string(containsString("PARIS")))
        .andReturn();
  }

  @Test
  public void shouldReturnFormForCreatingTravel() throws Exception {
    //when, then
    this.mockMvc.perform(get("/mvc/createTravel")).andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().string(containsString(
            "<form action=\"/mvc/travel\" method=\"post\">\n" +
                "    <p>userId: <input type=\"text\" id=\"userId\" name=\"userId\" value=\"\"/></p>\n" +
                "    <p>source: <input type=\"text\" id=\"source\" name=\"source\" value=\"\"/></p>\n" +
                "    <p>destination: <input type=\"text\" id=\"destination\" name=\"destination\" value=\"\"/></p>\n" +
                "    <p><input type=\"submit\" value=\"Submit\"/> <input type=\"reset\" value=\"Reset\"/></p>\n" +
                "</form>")))

        .andReturn();
  }

  @Test
  public void shouldPostNewTravel() throws Exception {
    //given
    TravelDto travelDto = new TravelDto();
    travelDto.setUserId("u_id1");
    travelDto.setSource("NY");
    travelDto.setDestination("PARIS");

    mockMvc.perform(
        post("/mvc/travel")
            .contentType(MediaType.APPLICATION_FORM_URLENCODED)
            .sessionAttr("travelDto", travelDto)
    )
        .andExpect(status().isOk())
        .andExpect(view().name("allTravels"));
  }
}