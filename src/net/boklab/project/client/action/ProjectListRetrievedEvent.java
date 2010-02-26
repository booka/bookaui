package net.boklab.project.client.action;

import java.util.ArrayList;

import net.boklab.core.client.model.Bok;
import net.boklab.project.client.model.Project;

import com.google.gwt.event.shared.GwtEvent;

public class ProjectListRetrievedEvent extends GwtEvent<ProjectListRetrievedHandler> {

    public static final Type<ProjectListRetrievedHandler> TYPE = new Type<ProjectListRetrievedHandler>();
    private final ArrayList<Project> projects;

    public ProjectListRetrievedEvent(final ArrayList<Bok> list) {
	projects = new ArrayList<Project>();
	for (final Bok bok : list) {
	    projects.add(new Project(bok));
	}
    }

    @Override
    public Type<ProjectListRetrievedHandler> getAssociatedType() {
	return TYPE;
    }

    public ArrayList<Project> getList() {
	return projects;
    }

    @Override
    protected void dispatch(final ProjectListRetrievedHandler handler) {
	handler.onProjectList(this);
    }

}
