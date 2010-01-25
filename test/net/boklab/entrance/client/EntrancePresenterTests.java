package net.boklab.entrance.client;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import net.boklab.booka.client.ui.entrance.EntrancePresenter;
import net.boklab.project.client.action.GetProjectsEvent;
import net.boklab.project.client.action.GotProjectsEvent;
import net.boklab.project.client.action.OpenProjectEvent;
import net.boklab.project.client.ui.ProjectListDisplay;
import net.boklab.testing.Boky;
import net.boklab.testing.BookaTester;
import net.boklab.testing.EventBusTester;
import net.boklab.testing.RouterTester;
import net.boklab.tools.client.place.Place;
import net.boklab.workspace.client.ui.WorkspaceDisplay;

import org.junit.Before;
import org.junit.Test;

public class EntrancePresenterTests {

    private WorkspaceDisplay workspace;
    private BookaTester tester;
    private EventBusTester eventBus;
    private RouterTester router;

    @Before
    public void setup() {
	tester = new BookaTester();
	router = tester.getRouter();
	eventBus = tester.getEventBus();
	workspace = tester.get(WorkspaceDisplay.class);
	tester.get(EntrancePresenter.class);
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

    @Test
    public void shouldShowProjectListOnWorkspace() {
	Boky.emptyDocument();
	eventBus.fireEvent(new GotProjectsEvent(Boky.projectList()));
	ProjectListDisplay projectList = tester.get(ProjectListDisplay.class);
	verify(workspace).setLeft(projectList);
    }
}
