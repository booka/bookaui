package net.boklab.entrance.client;

import static org.mockito.Mockito.verify;
import net.boklab.project.client.action.GotProjectsEvent;
import net.boklab.project.client.ui.ProjectListDisplay;
import net.boklab.testing.Boky;
import net.boklab.testing.BookaTester;
import net.boklab.tools.client.eventbus.DefaultEventBus;
import net.boklab.workspace.client.ui.WorkspaceDisplay;

import org.junit.Before;
import org.junit.Test;

public class EntrancePresenterTests {

    private DefaultEventBus eventBus;
    private WorkspaceDisplay workspace;
    private BookaTester tester;

    @Before
    public void setup() {
	tester = new BookaTester();
	eventBus = tester.getEventBus();
	workspace = tester.get(WorkspaceDisplay.class);
	tester.get(EntrancePresenter.class);
    }

    @Test
    public void shouldShowProjectList() {
	Boky.emptyDocumentClips();

	eventBus.fireEvent(new GotProjectsEvent(Boky.projectList()));

	ProjectListDisplay projectList = tester.get(ProjectListDisplay.class);
	verify(workspace).setLeft(projectList);

    }
}
