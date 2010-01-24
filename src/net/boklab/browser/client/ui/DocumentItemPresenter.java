package net.boklab.browser.client.ui;

import net.boklab.document.client.model.Document;
import net.boklab.tools.client.mvp.AbstractPresenter;
import net.boklab.tools.client.place.Place;
import net.boklab.tools.client.router.Router;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.inject.Inject;
import com.google.inject.Provider;

public class DocumentItemPresenter extends AbstractPresenter<DocumentItemDisplay> {

    private String documentId;

    @Inject
    public DocumentItemPresenter(final Router router, Provider<DocumentItemDisplay> display) {
	super(display);
	getDisplay().getClickeable().addClickHandler(new ClickHandler() {
	    @Override
	    public void onClick(ClickEvent event) {
		router.fireRequest(new Place("documents", documentId));
	    }
	});
    }

    public void setDocument(Document document) {
	this.documentId = document.getId();
	getDisplay().getDocumentTitle().setText(document.getTitle());
	getDisplay().getDescription().setHTML(document.getDescription());
	getDisplay().getExtra().setHTML("Este es el id: " + documentId);
    }
}
