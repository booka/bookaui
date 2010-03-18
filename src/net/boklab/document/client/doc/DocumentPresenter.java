package net.boklab.document.client.doc;

import java.util.ArrayList;

import net.boklab.core.client.model.Bok;
import net.boklab.core.client.user.SessionChangedEvent;
import net.boklab.core.client.user.SessionChangedHandler;
import net.boklab.core.client.user.UserSessionManager;
import net.boklab.document.client.DocumentManager;
import net.boklab.document.client.bok.ClipPresenter;
import net.boklab.document.client.bok.ClipPresenter.InsertHandler;
import net.boklab.document.client.content.ContentManager;
import net.boklab.tools.client.mvp.AbstractPresenter;

import com.google.gwt.core.client.GWT;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;

@Singleton
public class DocumentPresenter extends AbstractPresenter<DocumentDisplay> {

    private final ContentManager typeManager;
    private final ArrayList<ClipPresenter> boks;
    private ClipPresenter documentPresenter;
    private final UserSessionManager sessions;
    private final InsertHandler insertHandler;

    @Inject
    public DocumentPresenter(final DocumentManager documents, final UserSessionManager sessions,
	    final ContentManager typeManager, final Provider<DocumentDisplay> displayProvider) {
	super(displayProvider);
	this.sessions = sessions;

	boks = new ArrayList<ClipPresenter>();
	this.typeManager = typeManager;

	sessions.addSessionChangedHandler(new SessionChangedHandler() {
	    @Override
	    public void onSessionChanged(final SessionChangedEvent event) {
		final boolean locked = !sessions.isLoggedIn();
		for (final ClipPresenter presenter : boks) {
		    documentPresenter.setLocked(locked);
		    presenter.setLocked(locked);
		}
	    }
	}, true);

	insertHandler = new InsertHandler() {
	    @Override
	    public void onInsert(final ClipPresenter bokPresenter, final boolean insertBefore) {
		final Bok document = documentPresenter.getBok();
		final int newPosition = bokPresenter.getBok().getPosition();
		final Bok clip = document.newChild(null, null, sessions.getUserId(), newPosition);
		final ClipPresenter presenter = typeManager.newBokPresenter(clip, this);
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
	    public void remove(final ClipPresenter bokPresenter) {
		boks.remove(bokPresenter);
		getDisplay().remove(bokPresenter.getDisplay());
	    }

	};
    }

    public void setDocument(final Bok document) {
	final DocumentDisplay display = getDisplay();

	display.clear();
	boks.clear();

	documentPresenter = typeManager.newBokPresenter(document, new InsertHandler() {
	    @Override
	    public void onInsert(final ClipPresenter bokPresenter, final boolean insertBefore) {
		GWT.log("DOCPRES InsertBefore");
		final int position = bokPresenter.getBok().getPosition();
		final Bok bok = document.newChild(null, null, sessions.getUserId(), position);
		GWT.log("New bok");
		final ClipPresenter presenter = createBokPresenter(bok);
		presenter.setActionsVisible(true);
		if (boks.size() == 0) {
		    display.add(presenter.getDisplay());
		} else {
		    display.insert(presenter.getDisplay(), 1);
		}
	    }

	    @Override
	    public void remove(final ClipPresenter bokPresenter) {
	    }
	});
	display.add(documentPresenter.getDisplay());
	documentPresenter.setLocked(!sessions.isLoggedIn());

	ClipPresenter bokPresenter;
	for (final Bok clip : document.getChildren()) {
	    bokPresenter = createBokPresenter(clip);
	    display.add(bokPresenter.getDisplay());
	}
    }

    private ClipPresenter createBokPresenter(final Bok bok) {
	final ClipPresenter bokPresenter = typeManager.newBokPresenter(bok, insertHandler);
	boks.add(bokPresenter);
	bokPresenter.setLocked(!sessions.isLoggedIn());
	return bokPresenter;
    }
}
