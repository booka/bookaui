package net.boklab.document.client.actions;

import net.boklab.core.client.bok.events.BokCreatedEvent;
import net.boklab.core.client.bok.events.BokCreatedHandler;
import net.boklab.core.client.model.Bok;
import net.boklab.core.client.user.UserSessionManager;
import net.boklab.document.client.ClipManager;
import net.boklab.document.client.bok.ClipPresenter;
import net.boklab.document.client.bok.action.BokAction;
import net.boklab.document.client.bok.editor.BokEditorDisplay;
import net.boklab.document.client.content.ContentEditor;
import net.boklab.document.client.content.ContentHandler;
import net.boklab.document.client.content.ContentManager;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.inject.Inject;

public class CreateContentAction extends BokAction {

    public static final String TYPE = "CreateHtml";
    private final ContentManager manager;
    private final UserSessionManager sessions;
    private final ClipManager clips;
    private String contentType;

    @Inject
    public CreateContentAction(final ContentManager manager, final UserSessionManager sessions, final ClipManager documents) {
	super(TYPE, null);
	this.manager = manager;
	this.sessions = sessions;
	clips = documents;
    }

    public CreateContentAction config(final String contentType, final String actionLabel) {
	this.contentType = contentType;
	this.actionLabel = actionLabel;
	return this;
    }

    @Override
    public void execute(final ClipPresenter presenter) {
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
		clip.setBokType(ClipManager.getModelType());
		save(presenter, clip, editorDisplay);
	    }
	});
	presenter.setEditor(editorDisplay);
    }

    @Override
    public boolean isApplicable(final ClipPresenter presenter) {
	return sessions.isLoggedIn() && presenter.getContentHandler() == null;
    }

    private void save(final ClipPresenter presenter, final Bok clip, final BokEditorDisplay editorDisplay) {
	final ContentEditor<?> editor = editorDisplay.getEditor();
	editor.updateClip();
	clips.create(clip, new BokCreatedHandler() {
	    @Override
	    public void onBokCreated(final BokCreatedEvent event) {
		final Bok newClip = event.getBok();
		final ContentHandler contentHandler = manager.getHandler(newClip);
		presenter.setBok(newClip, contentHandler);
		presenter.setWaiting(false);
	    }
	});
	presenter.setWaiting(true);
    }

}
