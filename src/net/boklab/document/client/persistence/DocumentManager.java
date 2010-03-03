package net.boklab.document.client.persistence;

import net.boklab.core.client.persistence.BokCreatedEvent;
import net.boklab.core.client.persistence.BokCreatedHandler;
import net.boklab.core.client.persistence.BokRetrievedEvent;
import net.boklab.core.client.persistence.BokRetrievedHandler;
import net.boklab.core.client.persistence.CreateBokEvent;
import net.boklab.core.client.persistence.RetrieveBokEvent;
import net.boklab.core.client.persistence.UpdateBokEvent;
import net.boklab.core.client.session.Sessions;
import net.boklab.document.client.I18nDocs;
import net.boklab.document.client.model.Clip;
import net.boklab.document.client.model.Document;
import net.boklab.tools.client.eventbus.EventBus;
import net.boklab.workspace.client.event.UserMessageEvent;

import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class DocumentManager implements Documents {

    private final EventBus eventBus;
    private final Sessions sessions;

    @Inject
    public DocumentManager(final EventBus eventBus, final Sessions sessions) {
	this.eventBus = eventBus;
	this.sessions = sessions;
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
    public void createClip(final Clip clip, final ClipCreatedHandler handler) {
	eventBus.fireEvent(new CreateBokEvent(clip, new BokCreatedHandler() {
	    @Override
	    public void onBokCreated(final BokCreatedEvent event) {
		final ClipCreatedEvent clipCreatedEvent = new ClipCreatedEvent(new Clip(event.getBok()), event
			.getResponse());
		if (handler != null) {
		    handler.onClipCreated(clipCreatedEvent);
		}
		eventBus.fireEvent(clipCreatedEvent);
	    }
	}));
    }

    @Override
    public void createDocument(final Document document, final BokCreatedHandler handler) {
	eventBus.fireEvent(new CreateBokEvent(document, handler));
    }

    @Override
    public boolean isUserLoggedIn() {
	return sessions.isLoggedIn();
    }

    @Override
    public void openDocument(final String documentId) {
	eventBus.fireEvent(new DocumentRequestEvent(documentId));
	eventBus.fireEvent(new RetrieveBokEvent(documentId, new BokRetrievedHandler() {
	    @Override
	    public void onBokRetrieved(final BokRetrievedEvent event) {
		final Document document = new Document(event.getBok(), event.getChildren());
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
