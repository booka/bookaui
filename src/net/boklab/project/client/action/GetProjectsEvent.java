package net.boklab.project.client.action;

import com.google.gwt.event.shared.GwtEvent;

public class GetProjectsEvent extends GwtEvent<GetProjectsHandler> {
    public static final Type<GetProjectsHandler> TYPE = new Type<GetProjectsHandler>();

    @Override
    public Type<GetProjectsHandler> getAssociatedType() {
	return TYPE;
    }

    @Override
    protected void dispatch(GetProjectsHandler handler) {
	handler.onGetProjects(this);
    }

}
