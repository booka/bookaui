package net.boklab.document.client.actions;

import net.boklab.core.client.I18nCore;
import net.boklab.core.client.bok.events.UpdateBokEvent;
import net.boklab.core.client.model.Bok;
import net.boklab.core.client.session.Sessions;
import net.boklab.document.client.bok.BokPresenter;
import net.boklab.document.client.bok.action.BokAction;
import net.boklab.document.client.bok.editor.BokEditorDisplay;
import net.boklab.document.client.content.ContentEditor;
import net.boklab.document.client.content.ContentManager;
import net.boklab.tools.client.eventbus.EventBus;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class EditClipAction extends BokAction {

    private static final String TYPE = "EditClipAction";
    private final ContentManager manager;
    private final EventBus eventBus;
    private final Sessions sessions;

    @Inject
    public EditClipAction(final EventBus eventBus, final Sessions sessions, final ContentManager manager) {
	super(TYPE, I18nCore.t.editAction());
	this.eventBus = eventBus;
	this.sessions = sessions;
	this.manager = manager;
    }

    @Override
    public void execute(final BokPresenter presenter) {
	final Bok clip = presenter.getBok();
	final BokEditorDisplay editor = manager.newEditor(clip);
	editor.getCancel().setText(I18nCore.t.cancelAction());
	editor.getSave().setText(I18nCore.t.saveAction());
	editor.getSaveAction().addClickHandler(new ClickHandler() {
	    @Override
	    public void onClick(final ClickEvent event) {
		final ContentEditor<?> contentEditor = editor.getEditor();
		contentEditor.updateClip();
		eventBus.fireEvent(new UpdateBokEvent(clip, null));
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
    public boolean isApplicable(final BokPresenter presenter) {
	return sessions.isLoggedIn() && presenter.getContentHandler() != null;
    }

}
