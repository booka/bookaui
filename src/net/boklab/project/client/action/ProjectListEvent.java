package net.boklab.project.client.action;

import java.util.ArrayList;

import net.boklab.project.client.model.Project;

import com.google.gwt.event.shared.GwtEvent;

public class ProjectListEvent extends GwtEvent<ProjectListHandler> {

    public static final Type<ProjectListHandler> TYPE = new Type<ProjectListHandler>();
    private final ArrayList<Project> list;

    public ProjectListEvent(ArrayList<Project> list) {
	this.list = list;
    }

    @Override
    public Type<ProjectListHandler> getAssociatedType() {
	return TYPE;
    }

    @Override
    protected void dispatch(ProjectListHandler handler) {
	handler.onProjectList(list);
    }

}
