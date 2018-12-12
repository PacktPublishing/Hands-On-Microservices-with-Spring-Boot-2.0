package com.tomekl007.packt.eventbus.api;

import com.tomekl007.packt.eventbus.domain.Event;

public interface EventBus {
  void publish(Event event);
  Event receive();
}
