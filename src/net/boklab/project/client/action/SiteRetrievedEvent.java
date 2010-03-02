package net.boklab.project.client.action;

import java.util.ArrayList;

import net.boklab.core.client.model.Bok;
import net.boklab.project.client.model.Project;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.GwtEvent;

public class SiteRetrievedEvent extends GwtEvent<SiteRetrievedHandler> {

    public static final Type<SiteRetrievedHandler> TYPE = new Type<SiteRetrievedHandler>();
    private final ArrayList<Project> projects;

    public SiteRetrievedEvent(final Bok site, final ArrayList<Bok> list) {
	projects = new ArrayList<Project>();
	for (final Bok bok : list) {
	    projects.add(new Project(bok));
	}
	GWT.log("Site retrieved: " + projects.size() + " projects");
    }

    @Override
    public Type<SiteRetrievedHandler> getAssociatedType() {
	return TYPE;
    }

    public ArrayList<Project> getList() {
	return projects;
    }

    @Override
    protected void dispatch(final SiteRetrievedHandler handler) {
	handler.onProjectList(this);
    }

}
