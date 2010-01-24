package net.boklab.document.client.manager;

import net.boklab.document.client.model.Document;

public interface DocumentManager {

    public void createDocument(Document document);

    public void getDocumentClips(Document document);

    public void update(Document document);

}
