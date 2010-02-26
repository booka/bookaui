package net.boklab.project.client.action;

import net.boklab.project.client.model.Project;

import com.google.gwt.event.shared.GwtEvent;

public class ProjectOpenedEvent extends GwtEvent<ProjectOpenedHandler> {

    private static final Type<ProjectOpenedHandler> TYPE = new Type<ProjectOpenedHandler>();

    public static Type<ProjectOpenedHandler> getType() {
	return TYPE;
    }

    private final Project project;

    public ProjectOpenedEvent(final Project project) {
	this.project = project;
    }

    @Override
    public Type<ProjectOpenedHandler> getAssociatedType() {
	return getType();
    }

    public Project getProject() {
	return project;
    }

    @Override
    public String toDebugString() {
	return super.toDebugString() + project.toDebugString();
    }

    @Override
    protected void dispatch(final ProjectOpenedHandler handler) {
	handler.onProject(this);
    }

}
