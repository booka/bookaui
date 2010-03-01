package net.boklab.document.client.actions;

import net.boklab.core.client.session.Sessions;
import net.boklab.document.client.I18nDocs;
import net.boklab.document.client.bok.BokPresenter;
import net.boklab.document.client.bok.action.BokAction;
import net.boklab.document.client.bok.editor.BokEditorDisplay;
import net.boklab.document.client.content.ContentEditor;
import net.boklab.document.client.content.ContentHandler;
import net.boklab.document.client.content.ContentTypeManager;
import net.boklab.document.client.content.html.HtmlContentHandler;
import net.boklab.document.client.model.Clip;
import net.boklab.document.client.persistence.ClipCreatedEvent;
import net.boklab.document.client.persistence.ClipCreatedHandler;
import net.boklab.document.client.persistence.Documents;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class CreateHtmlAction extends BokAction {

    public static final String TYPE = "CreateHtml";
    private final ContentTypeManager manager;
    private final Sessions sessions;
    private final Documents documents;

    @Inject
    public CreateHtmlAction(final ContentTypeManager manager, final Sessions sessions, final Documents documents) {
	super(TYPE, I18nDocs.t.createHtmlAction());
	this.manager = manager;
	this.sessions = sessions;
	this.documents = documents;
    }

    @Override
    public void execute(final BokPresenter presenter) {
	final Clip clip = new Clip();
	clip.setContentType(HtmlContentHandler.TYPE);
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
		save(presenter, clip, editorDisplay);
	    }
	});
	presenter.setEditor(editorDisplay);
    }

    @Override
    public boolean isApplicable(final BokPresenter presenter) {
	return sessions.isLoggedIn();
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
