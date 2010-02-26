package net.boklab.document.client.manager;

import net.boklab.document.client.model.Clip;
import net.boklab.document.client.model.Document;
import net.boklab.tools.client.eventbus.EventBus;

import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class DocumentsBridge implements Documents {

    private final EventBus eventBus;

    @Inject
    public DocumentsBridge(final EventBus eventBus) {
	this.eventBus = eventBus;
    }

    @Override
    public void createClip(final Document document, final Clip clip) {
	eventBus.fireEvent(new CreateClipEvent(document, clip));
    }

    @Override
    public void createDocument(final Document document) {
	eventBus.fireEvent(new CreateDocumentEvent(document));
    }

    @Override
    public void onDocumentOpened(final DocumentOpenedHandler handler) {
	eventBus.addHandler(DocumentOpenedEvent.TYPE, handler);
    }

    @Override
    public void openDocument(final String documentId) {
	eventBus.fireEvent(new OpenDocumentEvent(documentId));
    }

    @Override
    public void update(final Document document) {
	eventBus.fireEvent(new UpdateDocumentEvent(document));
    }

}
