package net.boklab.document.client.actions;

import net.boklab.core.client.I18nCore;
import net.boklab.core.client.model.Bok;
import net.boklab.document.client.bok.ClipPresenter;
import net.boklab.document.client.bok.action.BokAction;
import net.boklab.document.client.bok.editor.BokEditorDisplay;
import net.boklab.document.client.content.ContentManager;
import net.boklab.document.client.content.debug.DebugContentHandler;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class DebugAction extends BokAction {
    private static final String TYPE = "DebugAction";
    private final ContentManager manager;

    @Inject
    public DebugAction(final ContentManager manager) {
	super(TYPE, "Debug");
	this.manager = manager;
    }

    @Override
    public void execute(final ClipPresenter presenter) {
	final Bok bok = presenter.getBok();
	final BokEditorDisplay editor = manager.newEditor(bok, DebugContentHandler.TYPE);
	editor.setSaveVisible(false);
	editor.getCancel().setText(I18nCore.t.closeAction());
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
