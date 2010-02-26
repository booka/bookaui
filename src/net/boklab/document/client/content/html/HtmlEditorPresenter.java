package net.boklab.document.client.content.html;

import net.boklab.document.client.content.ContentTypeEditorPresenter;
import net.boklab.document.client.model.Clip;

import com.google.inject.Inject;

public class HtmlEditorPresenter implements ContentTypeEditorPresenter<HtmlEditorDisplay> {

    private final HtmlEditorDisplay display;
    private Clip clip;

    @Inject
    public HtmlEditorPresenter(final HtmlEditorDisplay display) {
	this.display = display;
    }

    @Override
    public Clip getClip() {
	return clip;
    }

    @Override
    public HtmlEditorDisplay getDisplay() {
	return display;
    }

    public void setClip(final Clip clip) {
	this.clip = clip;
	display.getBody().setText(clip.getBody());
    }

}
