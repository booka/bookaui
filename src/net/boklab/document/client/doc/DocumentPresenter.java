package net.boklab.document.client.doc;

import java.util.ArrayList;

import net.boklab.core.client.model.Bok;
import net.boklab.core.client.session.SessionChangedEvent;
import net.boklab.core.client.session.SessionChangedHandler;
import net.boklab.core.client.session.Sessions;
import net.boklab.document.client.bok.BokPresenter;
import net.boklab.document.client.bok.BokPresenter.InsertHandler;
import net.boklab.document.client.content.ContentManager;
import net.boklab.document.client.model.Clip;
import net.boklab.document.client.model.Document;
import net.boklab.document.client.persistence.DocumentRetrievedEvent;
import net.boklab.document.client.persistence.DocumentRetrievedHandler;
import net.boklab.tools.client.eventbus.EventBus;
import net.boklab.tools.client.mvp.AbstractPresenter;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;

@Singleton
public class DocumentPresenter extends AbstractPresenter<DocumentDisplay> implements InsertHandler {

    private final ContentManager typeManager;
    private final ArrayList<BokPresenter> boks;
    private BokPresenter documentPresenter;

    @Inject
    public DocumentPresenter(final EventBus eventBus, final Sessions sessions, final ContentManager typeManager,
	    final Provider<DocumentDisplay> displayProvider) {
	super(displayProvider);

	boks = new ArrayList<BokPresenter>();
	this.typeManager = typeManager;

	eventBus.addHandler(DocumentRetrievedEvent.getType(), new DocumentRetrievedHandler() {
	    @Override
	    public void onDocumentRetrieved(final DocumentRetrievedEvent event) {
		setDocument(event.getDocument());
	    }
	});

	eventBus.addHandler(SessionChangedEvent.TYPE, new SessionChangedHandler() {
	    @Override
	    public void onSessionChanged(final SessionChangedEvent event) {
	    }
	});

	// setLoggedIn(sessions.isLoggedIn());
    }

    @Override
    public void onInsert(final BokPresenter bokPresenter, final boolean insertBefore) {
	final Clip bok = new Clip();
	bok.setParentId(documentPresenter.getBok().getId());
	bok.setBokType(null);
	int newPosition = bokPresenter.getBok().getPosition();
	newPosition = insertBefore ? newPosition : newPosition + 1;
	bok.setPosition(newPosition);
	final BokPresenter presenter = typeManager.newBokPresenter(bok, this);
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

	documentPresenter = typeManager.newBokPresenter(document, new InsertHandler() {
	    @Override
	    public void onInsert(final BokPresenter bokPresenter, final boolean insertBefore) {
		final Clip bok = new Clip();
		bok.setParentId(documentPresenter.getBok().getId());
		bok.setBokType(null);
		bok.setPosition(1);
		final BokPresenter presenter = createBokPresenter(bok);
		presenter.setActionsVisible(true);
		if (boks.size() == 0) {
		    display.add(presenter.getDisplay());
		} else {
		    display.insert(presenter.getDisplay(), 1);
		}
	    }

	    @Override
	    public void remove(final BokPresenter bokPresenter) {
	    }
	});
	display.add(documentPresenter.getDisplay());

	BokPresenter bokPresenter;
	for (final Clip clip : document.getClips()) {
	    bokPresenter = createBokPresenter(clip);
	    display.add(bokPresenter.getDisplay());
	}
    }

}
