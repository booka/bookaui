package net.boklab.document.client.actions;

import net.boklab.core.client.I18nBok;
import net.boklab.core.client.model.Bok;
import net.boklab.document.client.bok.BokPresenter;
import net.boklab.document.client.bok.action.BokAction;
import net.boklab.document.client.bok.editor.BokEditorDisplay;
import net.boklab.document.client.content.ContentTypeManager;
import net.boklab.document.client.content.debug.DebugContentHandler;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class DebugAction extends BokAction {
    private static final String TYPE = "DebugAction";
    private final ContentTypeManager manager;

    @Inject
    public DebugAction(final ContentTypeManager manager) {
	super(TYPE, "Debug");
	this.manager = manager;
    }

    @Override
    public void execute(final BokPresenter presenter) {
	final Bok bok = presenter.getBok();
	final BokEditorDisplay editor = manager.newEditor(bok, DebugContentHandler.TYPE);
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
    public boolean isApplicable(final BokPresenter presenter) {
	return true;
    }

}
