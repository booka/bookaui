package net.boklab.document.client.ui;

import net.boklab.document.client.manager.DocumentOpenedEvent;
import net.boklab.document.client.manager.DocumentOpenedHandler;
import net.boklab.document.client.manager.Documents;
import net.boklab.document.client.model.Clip;
import net.boklab.document.client.model.Document;
import net.boklab.document.client.ui.clip.ClipPresenter;
import net.boklab.tools.client.mvp.AbstractPresenter;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;

@Singleton
public class DocumentPresenter extends AbstractPresenter<DocumentDisplay> {

    @Inject
    public DocumentPresenter(Documents documents, final Provider<DocumentDisplay> displayProvider,
	    final Provider<ClipPresenter> provider) {
	super(displayProvider);

	documents.onDocumentOpened(new DocumentOpenedHandler() {
	    @Override
	    public void onDocumentOpened(DocumentOpenedEvent event) {
		Document document = event.getDocument();
		getDisplay().getDocumentTitle().setText(document.getTitle());
		getDisplay().getDocumentDescription().setText(document.getDescription());
		for (Clip clip : document) {
		    ClipPresenter presenter = provider.get();
		    presenter.setClip(clip);
		    getDisplay().getContent().add(presenter.getDisplay().asWidget());
		}
	    }
	});
    }

}
