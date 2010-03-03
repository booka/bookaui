package net.boklab.site.client.action;

import net.boklab.site.client.model.Project;

import com.google.gwt.event.shared.GwtEvent;

public class ProjectOpenedEvent extends GwtEvent<ProjectOpenedHandler> {

    private static final Type<ProjectOpenedHandler> TYPE = new Type<ProjectOpenedHandler>();

    public static Type<ProjectOpenedHandler> getType() {
	return TYPE;
    }

    private final Project project;
    private final boolean changePlace;

    public ProjectOpenedEvent(final Project project, final boolean changePlace) {
	this.project = project;
	this.changePlace = changePlace;
    }

    @Override
    public Type<ProjectOpenedHandler> getAssociatedType() {
	return getType();
    }

    public Project getProject() {
	return project;
    }

    public boolean isChangePlace() {
	return changePlace;
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
