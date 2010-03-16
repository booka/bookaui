package net.boklab.core.client.session;

import java.util.HashMap;

import net.boklab.core.client.bok.events.BokRetrievedEvent;
import net.boklab.core.client.bok.events.BokRetrievedHandler;
import net.boklab.core.client.bok.events.OpenBokEvent;
import net.boklab.core.client.model.Bok;
import net.boklab.tools.client.eventbus.EventBus;

import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class ClientSession {
    private final EventBus eventBus;
    private final HashMap<String, Bok> byType;
    private Bok site;
    private Bok project;

    @Inject
    public ClientSession(final EventBus eventBus) {
	this.eventBus = eventBus;
	byType = new HashMap<String, Bok>();

	eventBus.addHandler(BokRetrievedEvent.getType(), new BokRetrievedHandler() {
	    @Override
	    public void onBokRetrieved(final BokRetrievedEvent event) {
		store(event.getBok());
	    }
	});
    }

    public Bok getProject() {
	return project;
    }

    public Bok getProjectBok(final String bokType) {
	assert !Bok.SITE.equals(bokType) && !Bok.PROJECT.equals(bokType) : "ClientSession: use get methods instead";
	return byType.get(bokType);
    }

    public Bok getSite() {
	return site;
    }

    public boolean has(final String bokType) {
	assert !Bok.SITE.equals(bokType) && !Bok.PROJECT.equals(bokType) : "ClientSession: use get methods instead";
	return byType.get(bokType) != null;
    }

    public boolean isCurrent(final String bokType, final String id) {
	final Bok bok = byType.get(bokType);
	return bok != null && bok.getId().equals(id);
    }

    private void setProject(final Bok bok) {
	if (project == null || !bok.getId().equals(project.getId())) {
	    project = bok;
	    byType.clear();
	}
    }

    private void setSite(final Bok bok) {
	site = bok;
    }

    private void store(final Bok bok) {
	final String bokType = bok.getBokType();
	if (bokType.equals(Bok.SITE)) {
	    setSite(bok);
	} else if (bokType.equals(Bok.PROJECT)) {
	    setProject(bok);
	    if (site == null) {
		eventBus.fireEvent(new OpenBokEvent(Bok.SITE, "1", null, null));
	    }
	} else {
	    byType.put(bokType, bok);
	    if (project == null || !project.getId().equals(bok.getProjectId())) {
		eventBus.fireEvent(new OpenBokEvent(Bok.PROJECT, bok.getProjectId(), null, null));
	    }
	}
    }
}
