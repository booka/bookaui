package net.boklab.browser.client.ui;

import java.util.HashMap;

import net.boklab.core.client.bok.events.BokCreatedEvent;
import net.boklab.core.client.bok.events.BokCreatedHandler;
import net.boklab.core.client.bok.events.BokOpenedEvent;
import net.boklab.core.client.bok.events.BokOpenedHandler;
import net.boklab.core.client.bok.events.BokUpdatedEvent;
import net.boklab.core.client.bok.events.BokUpdatedHandler;
import net.boklab.core.client.model.Bok;
import net.boklab.core.client.session.SessionChangedEvent;
import net.boklab.core.client.session.SessionChangedHandler;
import net.boklab.core.client.session.Sessions;
import net.boklab.document.client.persistence.Documents;
import net.boklab.site.client.ProjectManager;
import net.boklab.tools.client.mvp.AbstractPresenter;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;

@Singleton
public class DocumentBrowserPresenter extends AbstractPresenter<DocumentBrowserDisplay> {

    private final HashMap<String, DocumentItemPresenter> idToDocuments;

    @Inject
    public DocumentBrowserPresenter(final Documents documents, final ProjectManager projects, final Sessions sessions,
	    final Provider<DocumentBrowserDisplay> displayProvider, final Provider<DocumentItemPresenter> itemProvider) {
	super(displayProvider);

	idToDocuments = new HashMap<String, DocumentItemPresenter>();

	projects.addOpenedHandler(new BokOpenedHandler() {
	    @Override
	    public void onBokOpened(final BokOpenedEvent event) {
		final Bok project = event.getBok();
		final DocumentBrowserDisplay display = getDisplay();
		idToDocuments.clear();
		display.getList().clear();
		for (final Bok doc : project.getChildren()) {
		    final DocumentItemPresenter item = itemProvider.get();
		    item.setDocument(doc);
		    idToDocuments.put(doc.getId(), item);
		    display.getList().add(item.getDisplay().asWidget());
		}
		display.setCreateVisible(sessions.isLoggedIn());
	    }
	});

	documents.addUpdatedHandler(new BokUpdatedHandler() {
	    @Override
	    public void onBokUpdated(final BokUpdatedEvent event) {
		final Bok bok = event.getBok();
		final DocumentItemPresenter docPresenter = idToDocuments.get(bok.getId());
		if (docPresenter != null) {
		    docPresenter.setDocument(bok);
		}
	    }
	});

	documents.addCreatedHandler(new BokCreatedHandler() {
	    @Override
	    public void onBokCreated(final BokCreatedEvent event) {
		documents.open(event.getBok(), false);
	    }
	});

	sessions.addSessionChangedHandler(new SessionChangedHandler() {
	    @Override
	    public void onSessionChanged(final SessionChangedEvent event) {
		GWT.log("BROW SEESS: " + sessions.isLoggedIn());
		if (sessions.isLoggedIn()) {
		    final DocumentBrowserDisplay display = getDisplay();
		    display.getCreate().addClickHandler(new ClickHandler() {
			@Override
			public void onClick(final ClickEvent event) {
			    final Bok currentProject = projects.getActiveProject();
			    assert currentProject != null : "You should receive onNewDocument event without documents loaded previously";
			    final int position = currentProject.getChildren().size() + 1;
			    final Bok document = currentProject.newChild("Document", "Sin título",
				    sessions.getUserId(), position);
			    documents.createDocument(document, null);
			}
		    });
		    display.setCreateVisible(projects.hasActiveProject());
		}
	    }
	}, true);
    }

}
