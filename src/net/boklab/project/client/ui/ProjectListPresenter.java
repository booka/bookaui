package net.boklab.project.client.ui;

import java.util.ArrayList;

import net.boklab.project.client.action.GotProjectsHandler;
import net.boklab.project.client.action.Projects;
import net.boklab.project.client.model.Project;
import net.boklab.tools.client.mvp.AbstractPresenter;

import com.google.inject.Inject;
import com.google.inject.Provider;

public class ProjectListPresenter extends AbstractPresenter<ProjectListDisplay> {

    @Inject
    public ProjectListPresenter(Projects projects, final Provider<ProjectPresenter> provider,
	    final Provider<ProjectListDisplay> display) {
	super(display);

	projects.onProjectList(new GotProjectsHandler() {
	    @Override
	    public void onProjectList(ArrayList<Project> list) {
		getDisplay().clearList();
		for (Project project : list) {
		    ProjectPresenter presenter = provider.get();
		    presenter.setProject(project);
		    getDisplay().add(presenter.getDisplay());
		}

	    }
	});

    }

}
