package net.boklab.document.client.manager;

import net.boklab.core.client.persistence.BokCreatedHandler;
import net.boklab.document.client.model.Clip;
import net.boklab.document.client.model.Document;

public interface Documents {

    public void createDocument(Document document, BokCreatedHandler handler);

    public void openDocument(String documentId);

    public void update(Document document);

    public void updateClip(Clip clip);

    void createClip(Clip clip, BokCreatedHandler handler);

    boolean isUserLoggedIn();

    void onDocumentOpened(DocumentOpenedHandler handler);

}
