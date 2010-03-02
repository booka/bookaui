package net.boklab.document.client.actions;

import net.boklab.core.client.model.Bok;
import net.boklab.core.client.session.Sessions;
import net.boklab.document.client.bok.BokPresenter;
import net.boklab.document.client.bok.action.BokAction;
import net.boklab.document.client.bok.editor.BokEditorDisplay;
import net.boklab.document.client.content.ContentEditor;
import net.boklab.document.client.content.ContentHandler;
import net.boklab.document.client.content.ContentManager;
import net.boklab.document.client.model.Clip;
import net.boklab.document.client.persistence.ClipCreatedEvent;
import net.boklab.document.client.persistence.ClipCreatedHandler;
import net.boklab.document.client.persistence.Documents;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.inject.Inject;

public class CreateContentAction extends BokAction {

    public static final String TYPE = "CreateHtml";
    private final ContentManager manager;
    private final Sessions sessions;
    private final Documents documents;
    private String contentType;

    @Inject
    public CreateContentAction(final ContentManager manager, final Sessions sessions, final Documents documents) {
	super(TYPE, null);
	this.manager = manager;
	this.sessions = sessions;
	this.documents = documents;
    }

    public CreateContentAction config(final String contentType, final String actionLabel) {
	this.contentType = contentType;
	this.actionLabel = actionLabel;
	return this;
    }

    @Override
    public void execute(final BokPresenter presenter) {
	final Bok clip = presenter.getBok();
	clip.setContentType(contentType);
	final BokEditorDisplay editorDisplay = manager.newEditor(clip);
	editorDisplay.getCancelAction().addClickHandler(new ClickHandler() {
	    @Override
	    public void onClick(final ClickEvent event) {
		presenter.destroy();
	    }
	});
	editorDisplay.getSaveAction().addClickHandler(new ClickHandler() {
	    @Override
	    public void onClick(final ClickEvent event) {
		clip.setBokType(Clip.TYPE);
		save(presenter, new Clip(clip), editorDisplay);
	    }
	});
	presenter.setEditor(editorDisplay);
    }

    @Override
    public boolean isApplicable(final BokPresenter presenter) {
	return sessions.isLoggedIn() && presenter.getContentHandler() == null;
    }

    private void save(final BokPresenter presenter, final Clip clip, final BokEditorDisplay editorDisplay) {
	final ContentEditor<?> editor = editorDisplay.getEditor();
	editor.updateClip();
	documents.createClip(clip, new ClipCreatedHandler() {
	    @Override
	    public void onClipCreated(final ClipCreatedEvent event) {
		final Clip newClip = event.getClip();
		final ContentHandler contentHandler = manager.getHandler(newClip);
		presenter.setBok(newClip, contentHandler);
		presenter.setWaiting(false);
	    }
	});
	presenter.setWaiting(true);
    }

}
