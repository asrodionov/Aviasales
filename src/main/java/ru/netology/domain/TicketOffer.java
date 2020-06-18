package ru.netology.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class TicketOffer implements Comparable<TicketOffer>{
    private int id;
    private int price;
    private String departureAirport;
    private String arrivalAirport;
    private int flightTime;

    public boolean matches(String from, String to) {
        return getDepartureAirport().equalsIgnoreCase(from) & (getArrivalAirport().equalsIgnoreCase(to));
    }

    @Override
    public int compareTo(TicketOffer o) {
        return price - o.price;
    }
}
