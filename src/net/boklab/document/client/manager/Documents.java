package net.boklab.document.client.manager;

import net.boklab.document.client.model.Clip;
import net.boklab.document.client.model.Document;

public interface Documents {

    public void createDocument(Document document);

    public void openDocument(String documentId);

    public void update(Document document);

    void createClip(Document document, Clip clip);

    void onDocumentOpened(DocumentOpenedHandler handler);

}
