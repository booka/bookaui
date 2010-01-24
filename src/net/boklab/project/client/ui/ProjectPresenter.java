package net.boklab.project.client.ui;

import net.boklab.project.client.model.Project;
import net.boklab.tools.client.mvp.AbstractPresenter;
import net.boklab.tools.client.place.Place;
import net.boklab.tools.client.router.Router;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.inject.Inject;
import com.google.inject.Provider;

public class ProjectPresenter extends AbstractPresenter<ProjectDisplay> {

    private String projectId;

    @Inject
    public ProjectPresenter(final Router router, Provider<ProjectDisplay> display) {
	super(display);
	getDisplay().addClickHandler(new ClickHandler() {
	    @Override
	    public void onClick(ClickEvent event) {
		router.fireRequest(new Place("archives", projectId));
	    }
	});
    }

    public void setProject(Project project) {
	this.projectId = project.getId();
	getDisplay().getTitleHeader().setText(project.getTitle());

    }

}
