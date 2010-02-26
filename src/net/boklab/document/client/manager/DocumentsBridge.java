package net.boklab.document.client.manager;

import net.boklab.core.client.session.Sessions;
import net.boklab.document.client.model.Clip;
import net.boklab.document.client.model.Document;
import net.boklab.tools.client.eventbus.EventBus;

import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class DocumentsBridge implements Documents {

    private final EventBus eventBus;
    private final Sessions sessions;

    @Inject
    public DocumentsBridge(final EventBus eventBus, final Sessions sessions) {
	this.eventBus = eventBus;
	this.sessions = sessions;
    }

    @Override
    public void createClip(final Clip clip) {
	eventBus.fireEvent(new CreateBokEvent(clip));
    }

    @Override
    public void createDocument(final Document document) {
	eventBus.fireEvent(new CreateBokEvent(document));
    }

    @Override
    public boolean isUserLoggedIn() {
	return sessions.isLoggedIn();
    }

    @Override
    public void onDocumentOpened(final DocumentOpenedHandler handler) {
	eventBus.addHandler(DocumentOpenedEvent.TYPE, handler);
    }

    @Override
    public void openDocument(final String documentId) {
	eventBus.fireEvent(new GetBokEvent(documentId, Document.TYPE));
    }

    @Override
    public void update(final Document document) {
	eventBus.fireEvent(new UpdateBokEvent(document));
    }

    @Override
    public void updateClip(final Clip clip) {
	eventBus.fireEvent(new UpdateBokEvent(clip));
    }

}
