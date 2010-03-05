package net.boklab.entrance.client;

import static org.junit.Assert.assertEquals;
import net.boklab.core.client.bok.events.RetrieveBokEvent;
import net.boklab.places.client.EntranceController;
import net.boklab.testing.BookaTester;
import net.boklab.testing.EventBusTester;
import net.boklab.testing.RouterTester;
import net.boklab.tools.client.place.Place;

import org.junit.Before;
import org.junit.Test;

public class EntrancePresenterTests {

    private BookaTester tester;
    private EventBusTester eventBus;
    private RouterTester router;

    @Before
    public void setup() {
	tester = new BookaTester();
	router = tester.getRouter();
	eventBus = tester.getEventBus();
	tester.get(EntranceController.class);
    }

    @Test
    public void shouldLoadProject() {
	router.fireRequest(new Place("archives", "projectId"));

	assertEquals(RetrieveBokEvent.class, eventBus.getLastEventType());
	final RetrieveBokEvent event = (RetrieveBokEvent) eventBus.getLastEvent();
	assertEquals("projectId", event.getBokId());
    }

}
