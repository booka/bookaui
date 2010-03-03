package net.boklab.testing;

import static org.junit.Assert.assertNotNull;
import net.boklab.document.client.persistence.Documents;
import net.boklab.site.client.action.ProjectManager;
import net.boklab.site.client.ui.ProjectBrowserDisplay;
import net.boklab.tools.client.place.PlaceManager;
import net.boklab.workspace.client.ui.navigation.NavigationDisplay;
import net.boklab.workspace.client.ui.navigation.NavigationPresenter;

import org.junit.Before;
import org.junit.Test;

public class BookaTesterTests {
    private BookaTester test;

    @Before
    public void setup() {
	test = new BookaTester();
    }

    @Test
    public void shouldCreateDisplays() {
	assertNotNull(test.get(NavigationDisplay.class));
	assertNotNull(test.get(ProjectBrowserDisplay.class));
    }

    @Test
    public void shouldCreateManagers() {
	assertNotNull(test.get(Documents.class));
	assertNotNull(test.get(ProjectManager.class));
	assertNotNull(test.get(PlaceManager.class));
    }

    @Test
    public void shouldCreatePresenters() {
	assertNotNull(test.get(NavigationPresenter.class));
    }

}
