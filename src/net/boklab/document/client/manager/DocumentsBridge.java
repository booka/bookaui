package net.boklab.document.client.manager;

import net.boklab.core.client.persistence.BokCreatedHandler;
import net.boklab.core.client.persistence.CreateBokEvent;
import net.boklab.core.client.persistence.UpdateBokEvent;
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
    public void createClip(final Clip clip, final BokCreatedHandler handler) {
	eventBus.fireEvent(new CreateBokEvent(clip, handler));
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
    public void onDocumentOpened(final DocumentOpenedHandler handler) {
	eventBus.addHandler(DocumentOpenedEvent.TYPE, handler);
    }

    @Override
    public void openDocument(final String documentId) {
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
