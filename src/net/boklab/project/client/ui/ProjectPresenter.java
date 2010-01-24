package net.boklab.project.client.ui;

import net.boklab.project.client.model.Project;
import net.boklab.tools.client.mvp.AbstractPresenter;
import net.boklab.tools.client.place.Place;
import net.boklab.tools.client.router.Router;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.inject.Inject;

public class ProjectPresenter extends AbstractPresenter<ProjectDisplay> {

    private String projectId;

    @Inject
    public ProjectPresenter(final Router router, ProjectDisplay display) {
	super(display);
	display.addClickHandler(new ClickHandler() {
	    @Override
	    public void onClick(ClickEvent event) {
		router.fireRequest(new Place("archives", projectId));
	    }
	});
    }

    public void setProject(Project project) {
	this.projectId = project.getId();
	display.getTitleHeader().setText(project.getTitle());

    }

}
