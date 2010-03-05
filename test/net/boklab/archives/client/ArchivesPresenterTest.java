package net.boklab.archives.client;

import static org.mockito.Mockito.verify;
import net.boklab.browser.client.ui.DocumentBrowserDisplay;
import net.boklab.places.client.ArchivesController;
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
    ArchivesController presenter;

    @Before
    public void setup() {
	new BookaTester(this);
    }

    @Test
    public void shouldShowBrowserOnWorkspaceWhenProjectOpened() {
	verify(workspace).setRight(browser);
    }
}
