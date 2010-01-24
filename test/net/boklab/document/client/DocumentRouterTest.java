package net.boklab.document.client;

import static org.junit.Assert.assertEquals;
import net.boklab.document.client.manager.OpenDocumentEvent;
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
	test.get(DocumentRouter.class);
    }

    @Test
    public void shouldLoadDocument() {
	test.router.fireRequest(new Place("documents", "1"));
	assertEquals(OpenDocumentEvent.class, bus.getLastEventType());
    }

}
