package com.tomekl007.packt.eventbus.infrastructure;

import com.tomekl007.packt.eventbus.api.EventBus;
import com.tomekl007.packt.eventbus.domain.Event;
import org.springframework.stereotype.Component;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingDeque;

@Component
public class InMemoryEventBus implements EventBus {
  private final Queue<Event> queue = new LinkedBlockingDeque<>();

  @Override
  public void publish(Event event) {
    queue.add(event);
  }

  @Override
  public Event receive() {
    return queue.poll();
  }
}
