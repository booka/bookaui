package net.boklab.document.client.browser;

import net.boklab.core.client.model.Bok;
import net.boklab.document.client.DocumentManager;
import net.boklab.tools.client.mvp.AbstractPresenter;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.inject.Inject;
import com.google.inject.Provider;

public class DocumentItemPresenter extends AbstractPresenter<DocumentItemDisplay> {
    private Bok document;

    @Inject
    public DocumentItemPresenter(final DocumentManager documents, final Provider<DocumentItemDisplay> display) {
	super(display);
	getDisplay().getClickeable().addClickHandler(new ClickHandler() {
	    @Override
	    public void onClick(final ClickEvent event) {
		documents.open(document.getId(), document.getTitle(), null);
	    }
	});
    }

    public void setDocument(final Bok document) {
	this.document = document;
	getDisplay().getDocumentTitle().setText(document.getTitle());
	getDisplay().getDescription().setHTML(document.getDescription());
	getDisplay().getExtra().setHTML("Autor(a):" + document.getUserName());
    }
}
