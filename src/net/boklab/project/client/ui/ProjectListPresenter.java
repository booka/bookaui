package net.boklab.project.client.ui;

import net.boklab.project.client.action.ProjectListRetrievedEvent;
import net.boklab.project.client.action.ProjectListRetrievedHandler;
import net.boklab.project.client.action.ProjectManager;
import net.boklab.project.client.model.Project;
import net.boklab.tools.client.mvp.AbstractPresenter;

import com.google.inject.Inject;
import com.google.inject.Provider;

public class ProjectListPresenter extends AbstractPresenter<ProjectListDisplay> {

    @Inject
    public ProjectListPresenter(final ProjectManager projects, final Provider<ProjectPresenter> provider,
	    final Provider<ProjectListDisplay> display) {
	super(display);

	projects.onProjectList(new ProjectListRetrievedHandler() {

	    @Override
	    public void onProjectList(final ProjectListRetrievedEvent event) {
		getDisplay().clearList();
		for (final Project project : event.getList()) {
		    final ProjectPresenter presenter = provider.get();
		    presenter.setProject(project);
		    getDisplay().add(presenter.getDisplay());
		}

	    }
	});

    }

}
