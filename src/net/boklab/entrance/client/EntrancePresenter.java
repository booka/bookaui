package net.boklab.entrance.client;

import java.util.ArrayList;

import net.boklab.project.client.action.GotProjectsHandler;
import net.boklab.project.client.action.Projects;
import net.boklab.project.client.model.Project;
import net.boklab.project.client.ui.ProjectListPresenter;
import net.boklab.tools.client.mvp.AbstractPresenter;
import net.boklab.workspace.client.ui.WorkspaceDisplay;

import com.allen_sauer.gwt.log.client.Log;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;

@Singleton
public class EntrancePresenter extends AbstractPresenter<WorkspaceDisplay> {

    @Inject
    public EntrancePresenter(Projects projects, final ProjectListPresenter projectList,
	    final Provider<WorkspaceDisplay> display) {
	super(display);

	Log.debug("Entrance presenter created");

	projects.onProjectList(new GotProjectsHandler() {
	    @Override
	    public void onProjectList(ArrayList<Project> list) {
		Log.debug("EntrancePresenter: project list!");
		getDisplay().setLeft(projectList.getDisplay());
	    }
	});
    }

}
