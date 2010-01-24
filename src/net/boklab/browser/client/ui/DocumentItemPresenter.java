package net.boklab.browser.client.ui;

import net.boklab.document.client.model.Document;
import net.boklab.tools.client.mvp.AbstractPresenter;

import com.google.inject.Inject;

public class DocumentItemPresenter extends AbstractPresenter<DocumentItemDisplay> {

    @Inject
    public DocumentItemPresenter(DocumentItemDisplay display) {
	super(display);
    }

    public void setDocument(Document document) {
	display.getDocumentTitle().setText(document.getTitle());
	display.getDescription().setHTML(document.getDescription());
	display.getExtra().setHTML("Este es el id: " + document.getId());
    }
}
