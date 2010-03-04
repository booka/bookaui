package net.boklab.document.client.persistence;

import net.boklab.core.client.bok.events.BokCreatedHandler;
import net.boklab.core.client.bok.events.BokUpdatedHandler;
import net.boklab.core.client.model.Bok;
import net.boklab.core.client.persistence.BokManager;
import net.boklab.document.client.model.Clip;
import net.boklab.document.client.model.Document;

public interface Documents extends BokManager {

    public void addDocumentRequestHandler(DocumentRequestHandler handler);

    public void addUpdatedHandler(BokUpdatedHandler handler);

    public void createDocument(Bok document, BokCreatedHandler handler);

    public void openDocument(String documentId);

    public void update(Document document);

    public void updateClip(Clip clip);

    void addDocumentRetrievedHandler(DocumentRetrievedHandler handler);

    void createClip(Clip clip, BokCreatedHandler handler);

}
