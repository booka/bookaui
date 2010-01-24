package net.boklab.testing;

import static org.junit.Assert.assertNotNull;
import net.boklab.booka.client.BookaRouter;
import net.boklab.booka.client.ui.navigation.NavigationDisplay;
import net.boklab.booka.client.ui.navigation.NavigationPresenter;
import net.boklab.document.client.manager.DefaultDocumentManager;
import net.boklab.document.client.manager.DocumentManager;
import net.boklab.project.client.action.DefaultProjectManager;
import net.boklab.project.client.action.ProjectManager;
import net.boklab.project.client.ui.ProjectListDisplay;
import net.boklab.tools.client.place.PlaceManager;

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
	assertNotNull(test.get(ProjectListDisplay.class));
    }

    @Test
    public void shouldCreateManagers() {
	assertNotNull(test.get(DocumentManager.class));
	assertNotNull(test.get(ProjectManager.class));
	assertNotNull(test.get(DefaultDocumentManager.class));
	assertNotNull(test.get(DefaultProjectManager.class));
	assertNotNull(test.get(PlaceManager.class));
    }

    @Test
    public void shouldCreatePresenters() {
	assertNotNull(test.get(NavigationPresenter.class));
    }

    @Test
    public void shouldCreateRouters() {
	assertNotNull(test.get(BookaRouter.class));
    }

}