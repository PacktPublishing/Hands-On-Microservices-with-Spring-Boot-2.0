package com.tomekl007.packt.destinations.api;

import com.tomekl007.packt.mock.TravelDestinationDetailsMock;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Java6Assertions.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("integration")
public class DestinationDetailsControllerTest {

  @Autowired
  private TravelDestinationDetailsMock travelDestinationDetailsMock;

  @Autowired
  private DestinationDetailsController destinationDetailsController;

  @Test
  public void test() throws Exception {
    //given
    travelDestinationDetailsMock.addInfo("SUPER_CITY", "Beautiful Town");

    //when
    String res = destinationDetailsController.getInfoAboutDestination("SUPER_CITY");

    //then
    assertThat(res).isEqualTo("Beautiful Town");

  }

}