package net.boklab.booka.client;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import net.boklab.booka.client.ui.app.BookaAppDisplay;
import net.boklab.testing.BookaTester;
import net.boklab.testing.RouterTester;
import net.boklab.tools.client.place.Place;
import net.boklab.workspace.client.ui.WorkspaceDisplay;

import org.junit.Before;
import org.junit.Test;

public class BookaRouterTests {
    private RouterTester router;
    private WorkspaceDisplay workspace;
    private BookaTester tester;
    private BookaAppDisplay booka;

    @Before
    public void setup() {
	tester = new BookaTester();
	router = tester.getRouter();
	workspace = tester.get(WorkspaceDisplay.class);
	booka = tester.get(BookaAppDisplay.class);
	tester.get(BookaRouter.class);
    }

    @Test
    public void shouldRedirectOnEmptyToken() {
	router.fireRequest(new Place());
	assertEquals(new Place("entrance"), router.getLastRequest());
    }

    @Test
    public void shouldShowWorkspaceWhenArchives() {
	shouldShowWorkspace(new Place("entrance"));
    }

    @Test
    public void shouldShowWorkspaceWhenEntrance() {
	shouldShowWorkspace(new Place("archives"));
    }

    private void shouldShowWorkspace(Place place) {
	router.fireRequest(place);
	verify(booka).setContent(workspace);
	assertEquals(place, router.getLastChanged());
    }

}
