package net.boklab.document.client.manager;

import net.boklab.document.client.model.Clip;
import net.boklab.document.client.model.Document;

public interface Documents {

    public void createDocument(Document document);

    public void openDocument(String documentId);

    public void update(Document document);

    public void updateClip(Clip clip);

    void createClip(Clip clip);

    boolean isUserLoggedIn();

    void onDocumentOpened(DocumentOpenedHandler handler);

}
