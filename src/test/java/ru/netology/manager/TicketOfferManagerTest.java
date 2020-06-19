package ru.netology.manager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.netology.domain.TicketByFlightTimeAscComparator;
import ru.netology.domain.TicketOffer;
import ru.netology.manager.TicketOfferManager;
import ru.netology.repository.TicketOfferRepository;

import java.util.Arrays;
import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TicketOfferManagerTest {
    @Mock
    private TicketOfferRepository repository;
    @InjectMocks
    private TicketOfferManager manager;

    private TicketOffer first = new TicketOffer(1, 6000, "LED", "SIP", 3);
    private TicketOffer second = new TicketOffer(2, 3000, "LED", "SIP", 3);
    private TicketOffer third = new TicketOffer(3, 4500, "LED", "SIP", 1);
    private TicketOffer fourth = new TicketOffer(4, 4500, "VKO", "AER", 3);
    private TicketOffer fifth = new TicketOffer(5, 5000, "VKO", "AER", 10);
    private TicketOffer sixth = new TicketOffer(6, 4500, "VKO", "AER", 2);


    @BeforeEach
    public void setUp() {
        manager.add(first);
        manager.add(second);
        manager.add(third);
        manager.add(fourth);
        manager.add(fifth);
        manager.add(sixth);
    }

    @Test
    public void shouldSortOfPrice() {

        TicketOffer[] returned = new TicketOffer[]{first, second, third, fourth, fifth, sixth};
        doReturn(returned).when(repository).findAll();

        TicketByFlightTimeAscComparator comparator = new TicketByFlightTimeAscComparator();

        TicketOffer[] expected = new TicketOffer[]{third, first, second};
        TicketOffer[] actual = manager.findAll("LED", "SIP", comparator);

        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSortOfSamePrice() {

        TicketOffer[] returned = new TicketOffer[]{first, second, third, fourth, fifth, sixth};
        doReturn(returned).when(repository).findAll();

        TicketByFlightTimeAscComparator comparator = new TicketByFlightTimeAscComparator();

        TicketOffer[] expected = new TicketOffer[]{sixth, fourth, fifth};
        TicketOffer[] actual = manager.findAll("VKO", "AER",comparator);

        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSortOfViceVersaAirport() {

        TicketOffer[] returned = new TicketOffer[]{first, second, third, fourth, fifth, sixth};
        doReturn(returned).when(repository).findAll();

        TicketByFlightTimeAscComparator comparator = new TicketByFlightTimeAscComparator();

        TicketOffer[] expected = new TicketOffer[]{};
        TicketOffer[] actual = manager.findAll("AER", "VKO",comparator);

        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSortOfNonexistentAirport() {

        TicketOffer[] returned = new TicketOffer[]{first, second, third, fourth, fifth, sixth};
        doReturn(returned).when(repository).findAll();

        TicketByFlightTimeAscComparator comparator = new TicketByFlightTimeAscComparator();

        TicketOffer[] expected = new TicketOffer[]{};
        TicketOffer[] actual = manager.findAll("GOJ", "FRU",comparator);

        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSortOfOneNonexistentAirport() {

        TicketOffer[] returned = new TicketOffer[]{first, second, third, fourth, fifth, sixth};
        doReturn(returned).when(repository).findAll();

        TicketByFlightTimeAscComparator comparator = new TicketByFlightTimeAscComparator();

        TicketOffer[] expected = new TicketOffer[]{};
        TicketOffer[] actual = manager.findAll("LED", "FRU",comparator);

        assertArrayEquals(expected, actual);
    }
}