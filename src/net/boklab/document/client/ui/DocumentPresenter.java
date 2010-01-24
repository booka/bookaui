package net.boklab.document.client.ui;

import net.boklab.document.client.manager.DocumentClipsHandler;
import net.boklab.document.client.manager.DocumentManager;
import net.boklab.document.client.model.Clip;
import net.boklab.document.client.model.Document;
import net.boklab.document.client.model.DocumentClips;
import net.boklab.document.client.ui.clip.ClipPresenter;
import net.boklab.tools.client.mvp.AbstractPresenter;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;

@Singleton
public class DocumentPresenter extends AbstractPresenter<DocumentDisplay> {

    @Inject
    public DocumentPresenter(DocumentManager documents,
	    final Provider<DocumentDisplay> displayProvider, final Provider<ClipPresenter> provider) {
	super(displayProvider);

	documents.onDocumentClips(new DocumentClipsHandler() {
	    @Override
	    public void onDocumentClips(DocumentClips documentClips) {
		Document document = documentClips.getDocument();
		getDisplay().getDocumentTitle().setText(document.getTitle());
		getDisplay().getDocumentDescription().setText(document.getDescription());
		for (Clip clip : documentClips) {
		    ClipPresenter presenter = provider.get();
		    presenter.setClip(clip);
		    getDisplay().getContent().add(presenter.getDisplay().asWidget());
		}
	    }
	});
    }

}
