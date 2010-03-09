package net.boklab.indices.client.browser.actions;

import net.boklab.core.client.model.Bok;
import net.boklab.core.client.session.Sessions;
import net.boklab.core.client.ui.action.AbstractAction;
import net.boklab.core.client.ui.editor.EditorHandler;
import net.boklab.core.client.ui.icons.BokIcon;
import net.boklab.core.client.ui.icons.Icons;
import net.boklab.core.client.ui.overlay.OverlayPresenter;
import net.boklab.indices.client.I18nIndices;
import net.boklab.indices.client.IndiceManager;
import net.boklab.indices.client.browser.IndiceBrowserAction;
import net.boklab.indices.client.browser.IndiceBrowserPresenter;
import net.boklab.indices.client.editor.PointerEditorPresenter;
import net.boklab.indices.client.model.Indice;
import net.boklab.indices.client.model.Pointer;
import net.boklab.indices.client.pointer.PointerPresenter;

import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class AddPointerAction extends AbstractAction implements IndiceBrowserAction {

    private final OverlayPresenter overlay;
    private final PointerEditorPresenter editor;
    private final IndiceManager indices;
    private IndiceBrowserPresenter presenter;

    @Inject
    public AddPointerAction(final IndiceManager indices, final Sessions sessions,
	    final PointerEditorPresenter editor, final OverlayPresenter overlay) {
	super(I18nIndices.t.actionAdd(), Icons.get(BokIcon.add), sessions);
	this.indices = indices;
	this.editor = editor;
	this.overlay = overlay;
    }

    @Override
    public void execute() {
	editor.setPointer(new Pointer());
	editor.setCancelHandler(new EditorHandler() {
	    @Override
	    public void onCancel() {
		overlay.close();
	    }

	    @Override
	    public void onSave() {
		final Indice indice = presenter.getIndice();
		final Pointer pointer = editor.updatePointer();
		indice.add(pointer);
		final Bok indiceBok = indice.updateBok();
		indices.update(indiceBok, null);
		overlay.close();
	    }
	});
	overlay.show(editor.getDisplay());
    }

    @Override
    public void setPresenter(final IndiceBrowserPresenter presenter) {
	this.presenter = presenter;
    }

    @Override
    protected void onSessionChanged() {
	setVisible(sessions.isLoggedIn());
    }

    @Override
    public void setSelected(PointerPresenter selected) {
	// TODO Auto-generated method stub
	
    }

}
