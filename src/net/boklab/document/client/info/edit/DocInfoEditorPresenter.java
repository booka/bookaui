package net.boklab.document.client.info.edit;

import net.boklab.document.client.model.Document;
import net.boklab.tools.client.eventbus.EventBus;
import net.boklab.tools.client.mvp.AbstractPresenter;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.inject.Inject;
import com.google.inject.Provider;

public class DocInfoEditorPresenter extends AbstractPresenter<DocInfoEditorDisplay> {

    private Document document;
    private final EventBus eventBus;

    @Inject
    public DocInfoEditorPresenter(EventBus eventBus, Provider<DocInfoEditorDisplay> provider) {
	super(provider);
	this.eventBus = eventBus;
	bind();
    }

    public void setDocument(Document document) {
	this.document = document;
	DocInfoEditorDisplay display = getDisplay();
	display.getDocumentTitle().setText(document.getTitle());
	display.getDocumentDescription().setText(document.getDescription());
    }

    private void bind() {
	DocInfoEditorDisplay display = getDisplay();
	display.getSave().addClickHandler(new ClickHandler() {
	    @Override
	    public void onClick(ClickEvent event) {
		saveDocumentInfo();
	    }
	});

	display.getCancel().addClickHandler(new ClickHandler() {
	    @Override
	    public void onClick(ClickEvent event) {
		eventBus.fireEvent(new DocInfoEditCancelledEvent(document));
	    }
	});
    }

    protected void saveDocumentInfo() {
    }
}
