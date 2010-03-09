package net.boklab.document.client;

import static org.junit.Assert.assertEquals;
import net.boklab.core.client.bok.events.RetrieveBokEvent;
import net.boklab.testing.BookaTester;
import net.boklab.testing.EventBusTester;
import net.boklab.tools.client.place.Place;

import org.junit.Before;
import org.junit.Test;

public class DocumentRouterTest {
    private BookaTester test;
    private EventBusTester bus;

    @Before
    public void setup() {
	test = new BookaTester();
	bus = test.getEventBus();
    }

    @Test
    public void shouldLoadDocument() {
	test.router.request(new Place("documents", "1"));
	assertEquals(RetrieveBokEvent.class, bus.getLastEventType());
    }

}
