package net.boklab.document.client.content.debug;

import net.boklab.core.client.model.Bok;
import net.boklab.document.client.bok.editor.AbstractEditor;
import net.boklab.document.client.content.ContentManager;

import com.google.inject.Inject;
import com.google.inject.Provider;

public class DebugEditorPresenter extends AbstractEditor<DebugEditorDisplay> {

    private Bok bok;
    private final ContentManager contentManager;

    @Inject
    public DebugEditorPresenter(final ContentManager contentManager,
	    final Provider<DebugEditorDisplay> provider) {
	super(provider);
	this.contentManager = contentManager;
    }

    @Override
    public Bok getBok() {
	return bok;
    }

    @Override
    public void setBok(final Bok bok) {
	this.bok = bok;
	String info = bok.getBokType() + ": " + bok.getId() + "(parent: " + bok.getParentId() + ")";
	info += " Handler: " + contentManager.getContentType(bok);
	final DebugEditorDisplay display = getDisplay();
	display.getInfo().setText(info);
	display.getClipTitle().setText(bok.getTitle());
	display.getContentType().setText(bok.getContentType());
	display.getPosition().setText("" + bok.getPosition());
    }

    @Override
    public void updateClip() {

    }

}
