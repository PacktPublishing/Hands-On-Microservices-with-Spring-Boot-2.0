package com.tomekl007.packt.eventbus.domain;

import java.util.Objects;

public class Event {
  private final String action;
  private final String description;

  public Event(String action, String description) {
    this.action = action;
    this.description = description;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Event event = (Event) o;
    return Objects.equals(action, event.action) &&
        Objects.equals(description, event.description);
  }

  @Override
  public int hashCode() {
    return Objects.hash(action, description);
  }


  @Override
  public String toString() {
    return "Event{" +
        "action='" + action + '\'' +
        ", description='" + description + '\'' +
        '}';
  }
}
