package net.boklab.module.explore.client.editor;

import net.boklab.core.client.ui.editor.EditorHandler;
import net.boklab.module.explore.client.model.Pointer;
import net.boklab.tools.client.mvp.AbstractPresenter;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;

@Singleton
public class PointerEditorPresenter extends AbstractPresenter<PointerEditorDisplay> {

    private EditorHandler editorHandler;
    private Pointer pointer;

    @Inject
    public PointerEditorPresenter(final Provider<PointerEditorDisplay> provider) {
	super(provider);
    }

    public void setCancelHandler(final EditorHandler editorHandler) {
	this.editorHandler = editorHandler;
    }

    public void setPointer(final Pointer pointer) {
	this.pointer = pointer;
	final PointerEditorDisplay display = getDisplay();
	display.getPosition().setText(pointer.getPosition());
	display.getPointerTitle().setText(pointer.getTitle());
    }

    public Pointer updatePointer() {
	final PointerEditorDisplay display = getDisplay();
	pointer.setPosition(display.getPosition().getText());
	pointer.setTitle(display.getPointerTitle().getText());
	return pointer;
    }

    @Override
    protected void attach(final PointerEditorDisplay display) {
	display.getCancel().addClickHandler(new ClickHandler() {
	    @Override
	    public void onClick(final ClickEvent event) {
		if (editorHandler != null) {
		    editorHandler.onCancel();
		}
	    }
	});

	display.getSave().addClickHandler(new ClickHandler() {
	    @Override
	    public void onClick(final ClickEvent event) {
		if (editorHandler != null) {
		    editorHandler.onSave();
		}
	    }
	});
    }
}
