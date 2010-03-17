package net.boklab.module.archives.client.archive.browser;

import net.boklab.core.client.model.Bok;
import net.boklab.core.client.ui.browser.BrowserItemPresenter;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.inject.Inject;

public class ArchiveItemPresenter extends BrowserItemPresenter<ArchiveItemDisplay> {
    private Bok document;

    @Inject
    public ArchiveItemPresenter(final ArchiveBrowserPresenter presenter,
	    final ArchiveItemDisplay display) {
	super(display);
	getDisplay().getSelectArea().addClickHandler(new ClickHandler() {
	    @Override
	    public void onClick(final ClickEvent event) {
		presenter.setSelected(ArchiveItemPresenter.this);
	    }
	});
    }

    @Override
    public void bind() {
    }

    public Bok getDocument() {
	return document;
    }

    public void setDocument(final Bok document) {
	this.document = document;
	getDisplay().getDocumentTitle().setText(document.getTitle());
	getDisplay().getDescription().setHTML(document.getDescription());
	getDisplay().getExtra().setHTML("Autor(a):" + document.getUserName());
    }

    @Override
    public void setSelected(final boolean selected) {

    }
}
