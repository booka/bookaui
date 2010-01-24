package net.boklab.project.client.action;

import com.google.gwt.event.shared.GwtEvent;

public class OpenProjectEvent extends GwtEvent<OpenProjectHandler> {
    public static final Type<OpenProjectHandler> TYPE = new Type<OpenProjectHandler>();
    private final String projectId;

    public OpenProjectEvent(String projectId) {
	this.projectId = projectId;
    }

    @Override
    public GwtEvent.Type<OpenProjectHandler> getAssociatedType() {
	return TYPE;
    }

    public String getProjectId() {
	return projectId;
    }

    @Override
    protected void dispatch(OpenProjectHandler handler) {
	handler.onOpenProject(this);
    }

}
