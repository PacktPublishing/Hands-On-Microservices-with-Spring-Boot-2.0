package com.tomekl007.packt.booking.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Travel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String userId;
    private String source;
    private String destination;

    public Travel() {
    }

    public Travel(String userId, String source, String destination) {
        this.userId = userId;
        this.source = source;
        this.destination = destination;
    }

    public String getUserId() {
        return userId;
    }

    public String getSource() {
        return source;
    }

    public String getDestination() {
        return destination;
    }

    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Travel{" +
                "id=" + id +
                ", userId='" + userId + '\'' +
                ", source='" + source + '\'' +
                ", destination='" + destination + '\'' +
                '}';
    }
}
