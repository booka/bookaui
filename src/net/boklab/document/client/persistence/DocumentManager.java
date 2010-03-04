package net.boklab.document.client.persistence;

import net.boklab.core.client.bok.events.BokCreatedHandler;
import net.boklab.core.client.bok.events.BokRetrievedEvent;
import net.boklab.core.client.bok.events.BokRetrievedHandler;
import net.boklab.core.client.bok.events.CreateBokEvent;
import net.boklab.core.client.bok.events.RetrieveBokEvent;
import net.boklab.core.client.bok.events.UpdateBokEvent;
import net.boklab.core.client.model.Bok;
import net.boklab.core.client.persistence.AbstractBokManager;
import net.boklab.core.client.persistence.PlainBokManager;
import net.boklab.document.client.I18nDocs;
import net.boklab.document.client.model.Clip;
import net.boklab.document.client.model.Document;
import net.boklab.tools.client.eventbus.EventBus;
import net.boklab.workspace.client.event.UserMessageEvent;

import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class DocumentManager extends AbstractBokManager implements Documents {

    @Inject
    public DocumentManager(final EventBus eventBus, final PlainBokManager manager) {
	super(Document.TYPE, eventBus, manager);
    }

    @Override
    public void addDocumentRequestHandler(final DocumentRequestHandler handler) {
	eventBus.addHandler(DocumentRequestEvent.getType(), handler);
    }

    @Override
    public void addDocumentRetrievedHandler(final DocumentRetrievedHandler handler) {
	eventBus.addHandler(DocumentRetrievedEvent.getType(), handler);
    }

    @Override
    public void createClip(final Clip clip, final BokCreatedHandler handler) {
	eventBus.fireEvent(new CreateBokEvent(clip, handler));
    }

    @Override
    public void createDocument(final Bok document, final BokCreatedHandler handler) {
	eventBus.fireEvent(new CreateBokEvent(document, handler));
    }

    @Override
    public void openDocument(final String documentId) {
	eventBus.fireEvent(new DocumentRequestEvent(documentId));
	eventBus.fireEvent(new RetrieveBokEvent(documentId, new BokRetrievedHandler() {
	    @Override
	    public void onBokRetrieved(final BokRetrievedEvent event) {
		final Document document = new Document(event.getBok());
		eventBus.fireEvent(new DocumentRetrievedEvent(document));
		eventBus.fireEvent(new UserMessageEvent(I18nDocs.t.documentOpened(document.getTitle())));
	    }
	}));
    }

    @Override
    public void update(final Document document) {
	eventBus.fireEvent(new UpdateBokEvent(document, null));
    }

    @Override
    public void updateClip(final Clip clip) {
	eventBus.fireEvent(new UpdateBokEvent(clip, null));
    }

}
