package net.boklab.document.client.content.debug;

import net.boklab.core.client.model.Bok;
import net.boklab.document.client.content.ContentEditor;
import net.boklab.document.client.content.ContentManager;

import com.google.inject.Inject;

public class DebugEditorPresenter implements ContentEditor<DebugEditorDisplay> {

    private final DebugEditorDisplay display;
    private Bok bok;
    private final ContentManager contentManager;

    @Inject
    public DebugEditorPresenter(final DebugEditorDisplay display, final ContentManager contentManager) {
	this.display = display;
	this.contentManager = contentManager;
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
	String info = bok.getBokType() + ": " + bok.getId() + "(parent: " + bok.getParentId() + ")";
	info += " Handler: " + contentManager.getContentType(bok);
	display.getInfo().setText(info);
	display.getClipTitle().setText(bok.getTitle());
	display.getContentType().setText(bok.getContentType());
	display.getPosition().setText("" + bok.getPosition());
    }

    @Override
    public void updateClip() {

    }

}
