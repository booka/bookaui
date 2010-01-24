package net.boklab.project.client.ui;

import net.boklab.project.client.action.ProjectManager;
import net.boklab.project.client.model.Project;
import net.boklab.tools.client.mvp.AbstractPresenter;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.inject.Inject;

public class ProjectPresenter extends AbstractPresenter<ProjectDisplay> {

    private Project project;

    @Inject
    public ProjectPresenter(final ProjectManager projects, ProjectDisplay display) {
	super(display);
	display.addClickHandler(new ClickHandler() {
	    @Override
	    public void onClick(ClickEvent event) {
		projects.getProjectDocuments(project);
	    }
	});
    }

    public void setProject(Project project) {
	this.project = project;
	display.getTitleHeader().setText(project.getTitle());

    }

}
