package net.boklab.site.client.action;

import com.google.gwt.event.shared.GwtEvent;

public class ProjectRequestEvent extends GwtEvent<ProjectRequestHandler> {

    private static final Type<ProjectRequestHandler> TYPE = new Type<ProjectRequestHandler>();

    public static Type<ProjectRequestHandler> getType() {
	return TYPE;
    }

    private final String projectId;
    private final boolean changePlace;

    public ProjectRequestEvent(final String projectId, final boolean changePlace, final String knownTitle) {
	this.projectId = projectId;
	this.changePlace = changePlace;
    }

    @Override
    public Type<ProjectRequestHandler> getAssociatedType() {
	return TYPE;
    }

    public String getProjectId() {
	return projectId;
    }

    public boolean isChangePlace() {
	return changePlace;
    }

    @Override
    protected void dispatch(final ProjectRequestHandler handler) {
	handler.onProjectRequest(this);
    }

}
