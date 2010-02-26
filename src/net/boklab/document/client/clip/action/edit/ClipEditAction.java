package net.boklab.document.client.clip.action.edit;

import net.boklab.document.client.clip.ClipPresenter;
import net.boklab.document.client.clip.action.ClipAction;
import net.boklab.document.client.content.ContentTypeManager;
import net.boklab.document.client.content.editor.ClipEditorDisplay;
import net.boklab.document.client.content.slot.SlotContentType;
import net.boklab.document.client.manager.Documents;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class ClipEditAction extends ClipAction {

    private static final String TYPE = "ClipEditAction";
    private final ContentTypeManager manager;
    private final Documents documents;

    @Inject
    public ClipEditAction(final ContentTypeManager manager, final Documents documents) {
	super(TYPE, "Edit");
	this.manager = manager;
	this.documents = documents;
    }

    @Override
    public void execute(final ClipPresenter presenter) {
	final ClipEditorDisplay editor = manager.newEditor(presenter.getClip());
	editor.getSave().addClickHandler(new ClickHandler() {
	    @Override
	    public void onClick(final ClickEvent event) {
		presenter.setEditor(null);
	    }
	});
	editor.getCancel().addClickHandler(new ClickHandler() {
	    @Override
	    public void onClick(final ClickEvent event) {
		presenter.setEditor(null);
	    }
	});
	presenter.setEditor(editor);
    }

    @Override
    public boolean isApplicable(final ClipPresenter presenter) {
	return !presenter.isClipType(SlotContentType.TYPE);
    }

}
