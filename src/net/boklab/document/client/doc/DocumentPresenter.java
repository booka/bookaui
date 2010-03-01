package net.boklab.document.client.doc;

import java.util.ArrayList;

import net.boklab.core.client.model.Bok;
import net.boklab.document.client.bok.BokPresenter;
import net.boklab.document.client.bok.BokPresenter.InsertHandler;
import net.boklab.document.client.content.ContentTypeManager;
import net.boklab.document.client.model.Clip;
import net.boklab.document.client.model.Document;
import net.boklab.document.client.persistence.DocumentRetrievedEvent;
import net.boklab.document.client.persistence.DocumentRetrievedHandler;
import net.boklab.document.client.persistence.Documents;
import net.boklab.tools.client.mvp.AbstractPresenter;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;

@Singleton
public class DocumentPresenter extends AbstractPresenter<DocumentDisplay> implements InsertHandler {

    private final ContentTypeManager typeManager;
    private final ArrayList<BokPresenter> boks;

    @Inject
    public DocumentPresenter(final Documents documents, final ContentTypeManager typeManager,
	    final Provider<DocumentDisplay> displayProvider) {
	super(displayProvider);

	boks = new ArrayList<BokPresenter>();
	this.typeManager = typeManager;

	documents.onDocumentOpened(new DocumentRetrievedHandler() {
	    @Override
	    public void onDocumentRetrieved(final DocumentRetrievedEvent event) {
		setDocument(event.getDocument());
	    }
	});

    }

    @Override
    public void onInsert(final BokPresenter bokPresenter, final boolean insertBefore) {
	final BokPresenter presenter = typeManager.newBokPresenter(null, this);
	presenter.setActionsVisible(true);
	final DocumentDisplay display = getDisplay();
	final int currentIndex = display.getDisplayIndex(bokPresenter.getDisplay());
	if (insertBefore) {
	    display.insert(presenter.getDisplay(), currentIndex);
	} else {
	    final int last = display.getBokCount() - 1;
	    if (currentIndex == last) {
		display.add(presenter.getDisplay());
	    } else {
		display.insert(presenter.getDisplay(), currentIndex + 1);
	    }
	}
    }

    @Override
    public void remove(final BokPresenter bokPresenter) {
	boks.remove(bokPresenter);
	getDisplay().remove(bokPresenter.getDisplay());
    }

    private BokPresenter createBokPresenter(final Bok bok) {
	final BokPresenter bokPresenter = typeManager.newBokPresenter(bok, this);
	boks.add(bokPresenter);
	return bokPresenter;
    }

    protected void setDocument(final Document document) {
	final DocumentDisplay display = getDisplay();

	display.clear();
	boks.clear();

	display.add(createBokPresenter(document).getDisplay());

	for (final Clip clip : document.getClips()) {
	    display.add(createBokPresenter(clip).getDisplay());
	}
    }
}
