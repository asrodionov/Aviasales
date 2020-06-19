package ru.netology.domain;

import java.util.Comparator;

public class TicketByFlightTimeAscComparator implements Comparator<TicketOffer> {
    @Override
    public int compare(TicketOffer o1, TicketOffer o2) {
        return o1.getFlightTime() - o2.getFlightTime();
    }
}
