package net.boklab.document.client.content.html;

import net.boklab.core.client.model.Bok;
import net.boklab.document.client.content.ContentEditor;

import com.google.inject.Inject;

public class HtmlEditorPresenter implements ContentEditor<HtmlEditorDisplay> {

    private final HtmlEditorDisplay display;
    private Bok bok;

    @Inject
    public HtmlEditorPresenter(final HtmlEditorDisplay display) {
	this.display = display;
    }

    @Override
    public Bok getBok() {
	return bok;
    }

    @Override
    public HtmlEditorDisplay getDisplay() {
	return display;
    }

    public void setBok(final Bok clip) {
	bok = clip;
	display.getBody().setText(clip.getBody());
    }

    @Override
    public void updateClip() {
	bok.setBody(display.getBody().getText());
    }

}
