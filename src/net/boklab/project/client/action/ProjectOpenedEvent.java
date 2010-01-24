package net.boklab.project.client.action;

import net.boklab.project.client.model.Project;

import com.google.gwt.event.shared.GwtEvent;

public class ProjectOpenedEvent extends GwtEvent<ProjectOpenedHandler> {

    public static final Type<ProjectOpenedHandler> TYPE = new Type<ProjectOpenedHandler>();
    private final Project Project;

    public ProjectOpenedEvent(Project Project) {
	this.Project = Project;
    }

    @Override
    public Type<ProjectOpenedHandler> getAssociatedType() {
	return TYPE;
    }

    @Override
    protected void dispatch(ProjectOpenedHandler handler) {
	handler.onProject(Project);
    }

}
