package net.boklab.project.client;

import static org.junit.Assert.assertEquals;
import net.boklab.project.client.action.GetProjectsEvent;
import net.boklab.project.client.action.OpenProjectEvent;
import net.boklab.testing.BookaTester;
import net.boklab.testing.EventBusTester;
import net.boklab.testing.RouterTester;
import net.boklab.tools.client.place.Place;

import org.junit.Before;
import org.junit.Test;

public class ProjectRouterTester {

    private RouterTester router;
    private EventBusTester eventBus;

    @Before
    public void setup() {
	BookaTester test = new BookaTester();
	router = test.getRouter();
	eventBus = test.getEventBus();
	test.get(ProjectRouter.class);
    }

    @Test
    public void shouldLoadProject() {
	router.fireRequest(new Place("archives", "projectId"));
	assertEquals(OpenProjectEvent.class, eventBus.getLastEventType());
	OpenProjectEvent event = (OpenProjectEvent) eventBus.getLastEvent();
	assertEquals("projectId", event.getProjectId());
    }

    @Test
    public void shouldLoadProjectList() {
	router.fireRequest(new Place("entrance"));
	assertEquals(GetProjectsEvent.class, eventBus.getLastEventType());
    }

}
