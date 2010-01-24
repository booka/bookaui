package net.boklab.browser.client.ui;

import net.boklab.document.client.model.Document;
import net.boklab.tools.client.mvp.AbstractPresenter;
import net.boklab.tools.client.place.Place;
import net.boklab.tools.client.router.Router;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.inject.Inject;

public class DocumentItemPresenter extends AbstractPresenter<DocumentItemDisplay> {

    private String documentId;

    @Inject
    public DocumentItemPresenter(final Router router, DocumentItemDisplay display) {
	super(display);
	display.getClickeable().addClickHandler(new ClickHandler() {
	    @Override
	    public void onClick(ClickEvent event) {
		router.fireRequest(new Place("documents", documentId));
	    }
	});
    }

    public void setDocument(Document document) {
	this.documentId = document.getId();
	display.getDocumentTitle().setText(document.getTitle());
	display.getDescription().setHTML(document.getDescription());
	display.getExtra().setHTML("Este es el id: " + documentId);
    }
}
