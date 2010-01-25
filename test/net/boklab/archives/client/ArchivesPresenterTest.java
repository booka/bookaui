package net.boklab.archives.client;

import static org.mockito.Mockito.verify;
import net.boklab.booka.client.ui.archives.ArchivesPresenter;
import net.boklab.browser.client.ui.DocumentBrowserDisplay;
import net.boklab.project.client.action.ProjectOpenedEvent;
import net.boklab.testing.Boky;
import net.boklab.testing.BookaTester;
import net.boklab.testing.EventBusTester;
import net.boklab.workspace.client.ui.WorkspaceDisplay;

import org.junit.Before;
import org.junit.Test;

import com.google.inject.Inject;

public class ArchivesPresenterTest {

    @Inject
    EventBusTester eventBus;
    @Inject
    WorkspaceDisplay workspace;
    @Inject
    DocumentBrowserDisplay browser;
    @Inject
    ArchivesPresenter presenter;

    @Before
    public void setup() {
	new BookaTester(this);
    }

    @Test
    public void shouldShowBrowserOnWorkspaceWhenProjectOpened() {
	eventBus.fireEvent(new ProjectOpenedEvent(Boky.project()));
	verify(workspace).setRight(browser);
    }
}
