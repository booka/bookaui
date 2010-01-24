package net.boklab.document.client.manager;

import net.boklab.document.client.model.Document;
import net.boklab.tools.client.eventbus.EventBus;

import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class DocumentsBridge implements Documents {

    private final EventBus eventBus;

    @Inject
    public DocumentsBridge(EventBus eventBus) {
	this.eventBus = eventBus;
    }

    @Override
    public void createDocument(Document document) {
	eventBus.fireEvent(new CreateDocumentEvent(document));
    }

    @Override
    public void onDocumentOpened(DocumentOpenedHandler handler) {
	eventBus.addHandler(DocumentOpenedEvent.TYPE, handler);
    }

    @Override
    public void openDocument(String documentId) {
	eventBus.fireEvent(new OpenDocumentEvent(documentId));
    }

    @Override
    public void update(Document document) {
	assert false : "not implemented";
    }

}
