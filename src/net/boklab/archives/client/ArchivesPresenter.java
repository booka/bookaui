package net.boklab.archives.client;

import net.boklab.browser.client.ui.DocumentBrowserPresenter;
import net.boklab.document.client.ui.DocumentPresenter;
import net.boklab.project.client.action.ProjectOpenedHandler;
import net.boklab.project.client.action.Projects;
import net.boklab.project.client.model.Project;
import net.boklab.tools.client.mvp.AbstractPresenter;
import net.boklab.tools.client.mvp.Display;
import net.boklab.workspace.client.ui.WorkspaceDisplay;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;

@Singleton
public class ArchivesPresenter extends AbstractPresenter<WorkspaceDisplay> {

    @Inject
    public ArchivesPresenter(Projects projects, final DocumentBrowserPresenter browser,
	    final Provider<WorkspaceDisplay> displayProvider, DocumentPresenter document) {
	super(displayProvider);

	final WorkspaceDisplay display = getDisplay();
	display.setCenter(document.getDisplay());

	projects.onProject(new ProjectOpenedHandler() {
	    @Override
	    public void onProject(Project Project) {
		display.setRight(browser.getDisplay());
		display.setLeft(Display.NONE);
	    }
	});
    }

}
