package net.boklab.document.client.ui;

import net.boklab.document.client.info.DocInfoPresenter;
import net.boklab.document.client.manager.DocumentOpenedEvent;
import net.boklab.document.client.manager.DocumentOpenedHandler;
import net.boklab.document.client.manager.Documents;
import net.boklab.document.client.model.Clip;
import net.boklab.document.client.model.Document;
import net.boklab.document.client.ui.clip.ClipPresenter;
import net.boklab.tools.client.mvp.AbstractPresenter;

import com.google.gwt.user.client.ui.HasWidgets;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;

@Singleton
public class DocumentPresenter extends AbstractPresenter<DocumentDisplay> {

    private final DocInfoPresenter docInfo;
    private final Provider<ClipPresenter> clipProvider;

    @Inject
    public DocumentPresenter(Documents documents, DocInfoPresenter docInfo,
	    final Provider<DocumentDisplay> displayProvider,
	    final Provider<ClipPresenter> clipProvider) {
	super(displayProvider);

	this.docInfo = docInfo;
	this.clipProvider = clipProvider;

	bind();

	documents.onDocumentOpened(new DocumentOpenedHandler() {
	    @Override
	    public void onDocumentOpened(DocumentOpenedEvent event) {
		setDocument(event.getDocument());
	    }
	});
    }

    private void bind() {
	DocumentDisplay display = getDisplay();
	display.setInfoDisplay(docInfo.getDisplay());
    }

    protected void setDocument(Document document) {
	docInfo.setDocument(document);
	DocumentDisplay display = getDisplay();
	HasWidgets contents = display.getContents();
	contents.clear();
	for (Clip clip : document) {
	    ClipPresenter presenter = clipProvider.get();
	    presenter.setClip(clip);
	    contents.add(presenter.getDisplay().asWidget());
	}

    }
}
