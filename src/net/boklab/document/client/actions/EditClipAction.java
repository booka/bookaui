package net.boklab.document.client.actions;

import net.boklab.core.client.I18nBok;
import net.boklab.document.client.clip.ClipPresenter;
import net.boklab.document.client.clip.action.ClipAction;
import net.boklab.document.client.clip.editor.ClipEditorDisplay;
import net.boklab.document.client.content.ContentEditor;
import net.boklab.document.client.content.ContentTypeManager;
import net.boklab.document.client.content.slot.SlotContentHandler;
import net.boklab.document.client.manager.Documents;
import net.boklab.document.client.model.Clip;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class EditClipAction extends ClipAction {

    private static final String TYPE = "EditClipAction";
    private final ContentTypeManager manager;
    private final Documents documents;

    @Inject
    public EditClipAction(final ContentTypeManager manager, final Documents documents) {
	super(TYPE, I18nBok.t.saveAction());
	this.manager = manager;
	this.documents = documents;
    }

    @Override
    public void execute(final ClipPresenter presenter) {
	final Clip clip = presenter.getClip();
	final ClipEditorDisplay editor = manager.newEditor(clip);
	editor.getCancel().setText(I18nBok.t.cancelAction());
	editor.getSave().setText(I18nBok.t.saveAction());
	editor.getSaveAction().addClickHandler(new ClickHandler() {
	    @Override
	    public void onClick(final ClickEvent event) {
		final ContentEditor<?> contentEditor = editor.getEditor();
		contentEditor.updateClip();
		documents.updateClip(clip);
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
	return documents.isUserLoggedIn() && !presenter.isClipType(SlotContentHandler.TYPE);
    }

}
