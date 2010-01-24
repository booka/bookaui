package net.boklab.document.client.manager;

import net.boklab.document.client.model.Document;

public interface DocumentManager {

    public void createDocument(Document document);

    public void getDocumentClips(String documentId);

    public void update(Document document);

}
