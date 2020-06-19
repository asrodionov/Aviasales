package ru.netology.manager;

import ru.netology.domain.TicketOffer;
import ru.netology.repository.TicketOfferRepository;

import java.util.Arrays;
import java.util.Comparator;

public class TicketOfferManager {

    private TicketOfferRepository repository;

    public TicketOfferManager(TicketOfferRepository repository) {
        this.repository = repository;
    }

    public TicketOffer[] findAll(String from, String to, Comparator<TicketOffer> comparator) {
        TicketOffer[] result = new TicketOffer[0];
        for (TicketOffer ticket : repository.findAll()) {
            if (ticket.matches(from, to)) {
                TicketOffer[] tmp = new TicketOffer[result.length + 1];
                System.arraycopy(result, 0, tmp, 0, result.length);
                tmp[tmp.length - 1] = ticket;
                result = tmp;
            }
        }
        Arrays.sort(result,comparator);
        return result;
    }

    public void add(TicketOffer item) {
        repository.save(item);
    }

    public void removeById(int id) {
        repository.removeById(id);
    }

    public void removeAll() {
        repository.removeAll();
    }

    public TicketOffer findById(int id) { return repository.findById(id); }
}
