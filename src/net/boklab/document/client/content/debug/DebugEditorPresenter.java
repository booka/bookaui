package net.boklab.document.client.content.debug;

import net.boklab.core.client.model.Bok;
import net.boklab.document.client.content.ContentEditor;

import com.google.inject.Inject;

public class DebugEditorPresenter implements ContentEditor<DebugEditorDisplay> {

    private final DebugEditorDisplay display;
    private Bok bok;

    @Inject
    public DebugEditorPresenter(final DebugEditorDisplay display) {
	this.display = display;
    }

    @Override
    public Bok getBok() {
	return bok;
    }

    @Override
    public DebugEditorDisplay getDisplay() {
	return display;
    }

    @Override
    public void setBok(final Bok bok) {
	this.bok = bok;
	display.getInfo().setText(bok.getBokType() + ": " + bok.getId() + "(parent: " + bok.getParentId() + ")");
	display.getClipTitle().setText(bok.getTitle());
	display.getContentType().setText(bok.getContentType());
	display.getPosition().setText("" + bok.getPosition());
    }

    @Override
    public void updateClip() {

    }

}
