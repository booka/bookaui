package net.boklab.document.client.actions;

import net.boklab.core.client.I18nCore;
import net.boklab.core.client.model.Bok;
import net.boklab.core.client.session.Sessions;
import net.boklab.document.client.ClipManager;
import net.boklab.document.client.bok.ClipPresenter;
import net.boklab.document.client.bok.action.BokAction;
import net.boklab.document.client.bok.editor.BokEditorDisplay;
import net.boklab.document.client.content.ContentEditor;
import net.boklab.document.client.content.ContentManager;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class EditClipAction extends BokAction {

    private static final String TYPE = "EditClipAction";
    private final ContentManager manager;
    private final Sessions sessions;
    private final ClipManager clips;

    @Inject
    public EditClipAction(final ClipManager clips, final Sessions sessions,
	    final ContentManager manager) {
	super(TYPE, I18nCore.t.editAction());
	this.clips = clips;
	this.sessions = sessions;
	this.manager = manager;
    }

    @Override
    public void execute(final ClipPresenter presenter) {
	final Bok clip = presenter.getBok();
	final BokEditorDisplay editor = manager.newEditor(clip);
	editor.getCancel().setText(I18nCore.t.cancelAction());
	editor.getSave().setText(I18nCore.t.saveAction());
	editor.getSaveAction().addClickHandler(new ClickHandler() {
	    @Override
	    public void onClick(final ClickEvent event) {
		final ContentEditor<?> contentEditor = editor.getEditor();
		contentEditor.updateClip();
		clips.update(clip, null);
		presenter.setEditor(null);
		presenter.setWaiting(true);
	    }
	});
	editor.getCancelAction().addClickHandler(new ClickHandler() {
	    @Override
	    public void onClick(final ClickEvent event) {
		presenter.setEditor(null);
	    }
	});
	presenter.setEditor(editor);
    }

    @Override
    public boolean isApplicable(final ClipPresenter presenter) {
	return sessions.isLoggedIn() && presenter.getContentHandler() != null;
    }

}
