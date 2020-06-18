package ru.netology.repository;

import ru.netology.domain.TicketOffer;

public class TicketOfferRepository {

    private TicketOffer[] items = new TicketOffer[0];

    public void save(TicketOffer item) {
        int length = items.length + 1;
        TicketOffer[] tmp = new TicketOffer[length];
        System.arraycopy(items, 0, tmp, 0, items.length);
        int lastIndex = tmp.length - 1;
        tmp[lastIndex] = item;
        items = tmp;
    }

    public void removeAll() {
        items = new TicketOffer[0];
    }

    public void removeById(int id) {
        int length = items.length - 1;
        TicketOffer[] tmp = new TicketOffer[length];
        int index = 0;
        for (TicketOffer item : items) {
            if (item.getId() != id) {
                tmp[index] = item;
                index++;
            }
        }
        items = tmp;
    }

    public TicketOffer findById(int id) {
        TicketOffer tmp = null;
        for (TicketOffer item : items) {
            if (item.getId() == id) {
                tmp = item;
            }
        }
        return tmp;
    }

    public TicketOffer[] findAll() {
        return items;
    }

}
