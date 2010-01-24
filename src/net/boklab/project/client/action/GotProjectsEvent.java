package net.boklab.project.client.action;

import java.util.ArrayList;

import net.boklab.project.client.model.Project;

import com.google.gwt.event.shared.GwtEvent;

public class GotProjectsEvent extends GwtEvent<GotProjectsHandler> {

    public static final Type<GotProjectsHandler> TYPE = new Type<GotProjectsHandler>();
    private final ArrayList<Project> list;

    public GotProjectsEvent(ArrayList<Project> list) {
	this.list = list;
    }

    @Override
    public Type<GotProjectsHandler> getAssociatedType() {
	return TYPE;
    }

    @Override
    protected void dispatch(GotProjectsHandler handler) {
	handler.onProjectList(list);
    }

}
