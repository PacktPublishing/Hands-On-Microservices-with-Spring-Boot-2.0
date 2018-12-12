package com.tomekl007.packt.booking.domain;

public class TravelDto {

    private String userId;
    private String source;
    private String destination;

    public TravelDto() {
    }

    public TravelDto(String userId, String source, String destination) {
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

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    @Override
    public String toString() {
        return "TravelDto{" +
                "userId='" + userId + '\'' +
                ", source='" + source + '\'' +
                ", destination='" + destination + '\'' +
                '}';
    }
}
