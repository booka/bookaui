package net.boklab.site.client.project.browser;

import net.boklab.core.client.model.Bok;
import net.boklab.site.client.ProjectManager;
import net.boklab.tools.client.mvp.AbstractPresenter;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.inject.Inject;
import com.google.inject.Provider;

public class ProjectPresenter extends AbstractPresenter<ProjectDisplay> {

    private final ProjectManager projects;
    private Bok project;

    @Inject
    public ProjectPresenter(final ProjectManager projects, final Provider<ProjectDisplay> display) {
	super(display);
	this.projects = projects;

    }

    public void setProject(final Bok project) {
	this.project = project;
	getDisplay().getTitleHeader().setText(project.getTitle());

    }

    @Override
    protected void attach(final ProjectDisplay display) {
	display.addClickHandler(new ClickHandler() {
	    @Override
	    public void onClick(final ClickEvent event) {
		GWT.log("JODER: " + project.getTitle() + ": " + project);
		projects.open(project.getId(), project.getTitle(), false);
	    }
	});
    }

}
