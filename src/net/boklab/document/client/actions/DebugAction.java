package net.boklab.document.client.actions;

import net.boklab.core.client.I18nBok;
import net.boklab.document.client.clip.ClipPresenter;
import net.boklab.document.client.clip.action.ClipAction;
import net.boklab.document.client.clip.editor.ClipEditorDisplay;
import net.boklab.document.client.content.ContentTypeManager;
import net.boklab.document.client.content.debug.DebugContentHandler;
import net.boklab.document.client.model.Clip;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class DebugAction extends ClipAction {
    private static final String TYPE = "DebugAction";
    private final ContentTypeManager manager;

    @Inject
    public DebugAction(final ContentTypeManager manager) {
	super(TYPE, "Debug");
	this.manager = manager;
    }

    @Override
    public void execute(final ClipPresenter presenter) {
	final Clip clip = presenter.getClip();
	final ClipEditorDisplay editor = manager.newEditor(clip, DebugContentHandler.TYPE);
	editor.setSaveVisible(false);
	editor.getCancel().setText(I18nBok.t.closeAction());
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
	return true;
    }

}
