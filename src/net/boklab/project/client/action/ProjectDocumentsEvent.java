package net.boklab.project.client.action;

import net.boklab.project.client.model.ProjectDocuments;

import com.google.gwt.event.shared.GwtEvent;

public class ProjectDocumentsEvent extends GwtEvent<ProjectDocumentsHandler> {

    public static final Type<ProjectDocumentsHandler> TYPE = new Type<ProjectDocumentsHandler>();
    private final ProjectDocuments projectDocuments;

    public ProjectDocumentsEvent(ProjectDocuments projectDocuments) {
	this.projectDocuments = projectDocuments;
    }

    @Override
    public Type<ProjectDocumentsHandler> getAssociatedType() {
	return TYPE;
    }

    @Override
    protected void dispatch(ProjectDocumentsHandler handler) {
	handler.onProjectDocuments(projectDocuments);
    }

}
