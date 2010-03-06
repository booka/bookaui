package net.boklab.document.client.content.html;

import net.boklab.core.client.model.Bok;
import net.boklab.document.client.bok.editor.AbstractEditor;

import com.google.inject.Inject;
import com.google.inject.Provider;

public class HtmlEditorPresenter extends AbstractEditor<HtmlEditorDisplay> {

    private Bok bok;

    @Inject
    public HtmlEditorPresenter(final Provider<HtmlEditorDisplay> provider) {
	super(provider);
    }

    @Override
    public Bok getBok() {
	return bok;
    }

    public void setBok(final Bok clip) {
	bok = clip;
	getDisplay().getBody().setText(clip.getBody());
    }

    @Override
    public void updateClip() {
	bok.setBody(getDisplay().getBody().getText());
    }

}
