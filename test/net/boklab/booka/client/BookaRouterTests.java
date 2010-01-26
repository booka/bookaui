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

import com.google.inject.Inject;

public class BookaRouterTests {
    @Inject
    private RouterTester router;
    @Inject
    private WorkspaceDisplay workspace;
    @Inject
    private BookaAppDisplay booka;
    @Inject
    BookaRouter bookaRouter;

    @Before
    public void setup() {
	new BookaTester(this);
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
