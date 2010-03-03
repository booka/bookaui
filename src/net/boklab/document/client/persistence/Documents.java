package net.boklab.document.client.persistence;

import net.boklab.core.client.persistence.BokCreatedHandler;
import net.boklab.document.client.model.Clip;
import net.boklab.document.client.model.Document;

public interface Documents {

    public void addDocumentRequestHandler(DocumentRequestHandler handler);

    public void createDocument(Document document, BokCreatedHandler handler);

    public void openDocument(String documentId);

    public void update(Document document);

    public void updateClip(Clip clip);

    void addDocumentRetrievedHandler(DocumentRetrievedHandler handler);

    void createClip(Clip clip, ClipCreatedHandler handler);

    boolean isUserLoggedIn();

}
