package net.boklab.document.client.content.debug;

import net.boklab.document.client.content.ContentEditor;
import net.boklab.document.client.model.Clip;

import com.google.inject.Inject;

public class DebugEditorPresenter implements ContentEditor<DebugEditorDisplay> {

    private final DebugEditorDisplay display;
    private Clip clip;

    @Inject
    public DebugEditorPresenter(final DebugEditorDisplay display) {
	this.display = display;
    }

    @Override
    public Clip getClip() {
	return clip;
    }

    @Override
    public DebugEditorDisplay getDisplay() {
	return display;
    }

    @Override
    public void setClip(final Clip clip) {
	this.clip = clip;
	display.getInfo().setText("Clip: " + clip.getId() + "(parent: " + clip.getParentId() + ")");
	display.getClipTitle().setText(clip.getTitle());
	display.getContentType().setText(clip.getContentType());
	display.getPosition().setText("" + clip.getPosition());
    }

    @Override
    public void updateClip() {

    }

}
