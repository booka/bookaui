package net.boklab.module.archives.client.archive.browser;

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
import net.boklab.document.client.DocumentManager;
import net.boklab.document.client.I18nDocs;
import net.boklab.module.archives.client.archive.ArchiveManager;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;

@Singleton
public class ArchiveBrowserPresenter extends
	AbstractBrowserPresenter<ArchiveBrowserPresenter, ArchiveItemPresenter> {

    private final HashMap<String, ArchiveItemPresenter> idToDocuments;

    @Inject
    public ArchiveBrowserPresenter(final Provider<BrowserDisplay> displayProvider,
	    final ArchiveBrowserActions actions, final DocumentManager documents,
	    final ArchiveManager archives, final UserSessionManager sessions,
	    final Provider<ArchiveItemPresenter> itemProvider) {
	super(displayProvider, actions);
	idToDocuments = new HashMap<String, ArchiveItemPresenter>();

	archives.addOpenedHandler(new BokOpenedHandler() {
	    @Override
	    public void onBokOpened(final BokOpenedEvent event) {
		final Bok archive = event.getBok();
		final BrowserDisplay display = getDisplay();
		idToDocuments.clear();
		display.clearList();
		for (final Bok doc : archive.getChildren()) {
		    final ArchiveItemPresenter item = itemProvider.get();
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
		final ArchiveItemPresenter docPresenter = idToDocuments.get(bok.getId());
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
    }

    @Override
    protected void attach(final BrowserDisplay display) {
	display.getBrowserTitle().setText(I18nDocs.t.browserTitle());
	super.attach(display);
    }

}
