package com.tomekl007.packt;

import com.tomekl007.packt.booking.api.BookingService;
import com.tomekl007.packt.booking.domain.Travel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BookingServiceIntegrationTest {

    @Autowired
    BookingService bookingService;

    @Test
    public void shouldBookATravel() {
        bookingService.book(new Travel("user_me", "LONDON", "PARIS"));
    }

}
