package com.tomekl007.packt.booking.api;

import com.tomekl007.packt.booking.domain.Travel;

public interface BookingService {
    boolean book(Travel travel);
}
