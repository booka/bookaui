package net.boklab.entrance.client;

import static org.mockito.Mockito.verify;
import net.boklab.core.client.SimpleBok;
import net.boklab.document.client.manager.DefaultDocumentManager;
import net.boklab.document.client.model.Document;
import net.boklab.document.client.model.DocumentClips;
import net.boklab.project.client.ui.ProjectListDisplay;
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
    }

    @Test
    public void shouldShowProjectList() {
	Document document = SimpleBok.newDocument("1");
	DocumentClips documentClips = new DocumentClips(document, null);
	DefaultDocumentManager.fireDocumentClips(eventBus, documentClips);

	ProjectListDisplay projectList = tester.get(ProjectListDisplay.class);
	verify(workspace).setLeft(projectList);

    }
}
