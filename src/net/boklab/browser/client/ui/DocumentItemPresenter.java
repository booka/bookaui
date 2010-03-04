package net.boklab.browser.client.ui;

import net.boklab.core.client.model.Bok;
import net.boklab.document.client.persistence.Documents;
import net.boklab.tools.client.mvp.AbstractPresenter;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.inject.Inject;
import com.google.inject.Provider;

public class DocumentItemPresenter extends AbstractPresenter<DocumentItemDisplay> {
    private String documentId;

    @Inject
    public DocumentItemPresenter(final Documents documents, final Provider<DocumentItemDisplay> display) {
	super(display);
	getDisplay().getClickeable().addClickHandler(new ClickHandler() {
	    @Override
	    public void onClick(final ClickEvent event) {
		documents.openDocument(documentId);
	    }
	});
    }

    public void setDocument(final Bok document) {
	documentId = document.getId();
	getDisplay().getDocumentTitle().setText(document.getTitle());
	getDisplay().getDescription().setHTML(document.getDescription());
	getDisplay().getExtra().setHTML("Autor(a):" + document.getUserName());
    }
}
