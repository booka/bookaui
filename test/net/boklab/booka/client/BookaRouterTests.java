package net.boklab.booka.client;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.verify;
import net.boklab.browser.client.ui.DocumentBrowserDisplay;
import net.boklab.project.client.ui.ProjectListDisplay;
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

    @Before
    public void setup() {
	tester = new BookaTester();
	router = tester.getRouter();
	workspace = tester.get(WorkspaceDisplay.class);
	tester.get(BookaRouter.class);
    }

    @Test
    public void shouldShowDocumentListWhenArchives() {
	ProjectListDisplay projectList = tester.get(ProjectListDisplay.class);
	assertNotNull(projectList);
	router.fireRequest(new Place("entrance"));
	verify(workspace).setEast(projectList);
    }

    @Test
    public void shouldShowProjectListWhenEntrance() {
	DocumentBrowserDisplay browser = tester.get(DocumentBrowserDisplay.class);
	router.fireRequest(new Place("archives"));
	verify(workspace).setWest(browser);
    }

}
