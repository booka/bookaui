package net.boklab.document.client.browser;

import java.util.HashMap;

import net.boklab.core.client.bok.events.BokCreatedEvent;
import net.boklab.core.client.bok.events.BokCreatedHandler;
import net.boklab.core.client.bok.events.BokOpenedEvent;
import net.boklab.core.client.bok.events.BokOpenedHandler;
import net.boklab.core.client.bok.events.BokUpdatedEvent;
import net.boklab.core.client.bok.events.BokUpdatedHandler;
import net.boklab.core.client.model.Bok;
import net.boklab.core.client.ui.browser.AbstractBrowserPresenter;
import net.boklab.core.client.ui.browser.BrowserDisplay;
import net.boklab.core.client.user.UserSessionManager;
import net.boklab.document.client.ArchiveManager;
import net.boklab.document.client.DocumentManager;
import net.boklab.document.client.I18nDocs;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;

@Singleton
public class DocumentBrowserPresenter extends AbstractBrowserPresenter {

    private final HashMap<String, DocumentItemPresenter> idToDocuments;

    @Inject
    public DocumentBrowserPresenter(final DocumentManager documents, final ArchiveManager archives,
	    final UserSessionManager sessions, final Provider<BrowserDisplay> displayProvider,
	    final Provider<DocumentItemPresenter> itemProvider) {
	super(displayProvider);

	idToDocuments = new HashMap<String, DocumentItemPresenter>();

	archives.addOpenedHandler(new BokOpenedHandler() {
	    @Override
	    public void onBokOpened(final BokOpenedEvent event) {
		final Bok archive = event.getBok();
		final BrowserDisplay display = getDisplay();
		idToDocuments.clear();
		display.clearList();
		for (final Bok doc : archive.getChildren()) {
		    final DocumentItemPresenter item = itemProvider.get();
		    item.setDocument(doc);
		    idToDocuments.put(doc.getId(), item);
		    display.addItem(item.getDisplay());
		}
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
		documents.open(event.getBok());
	    }
	});

	// sessions.addSessionChangedHandler(new SessionChangedHandler() {
	// @Override
	// public void onSessionChanged(final SessionChangedEvent event) {
	// GWT.log("BROW SEESS: " + sessions.isLoggedIn());
	// if (sessions.isLoggedIn()) {
	// final BrowserDisplay display = getDisplay();
	// display.getCreate().addClickHandler(new ClickHandler() {
	// @Override
	// public void onClick(final ClickEvent event) {
	// final Bok currentProject = projects.getActive();
	// assert currentProject != null :
	// "You should receive onNewDocument event without documents loaded previously";
	// final int position = currentProject.getChildren().size() + 1;
	// final Bok document = currentProject.newChild("Document",
	// "Sin título",
	// sessions.getUserId(), position);
	// documents.create(document, null);
	// }
	// });
	// display.setCreateVisible(projects.hasActive());
	// }
	// }
	// }, true);
    }

    @Override
    protected void attach(final BrowserDisplay display) {
	// FIXME: i18n
	display.getBrowserTitle().setText(I18nDocs.t.browserTitle());
	super.attach(display);
    }

}
