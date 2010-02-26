package net.boklab.document.client.content.html;

import net.boklab.document.client.content.ClipContentType;
import net.boklab.document.client.model.Clip;

import com.google.inject.Inject;
import com.google.inject.Provider;

public class HtmlContentType extends ClipContentType {

    public static final String TYPE = "text/html";
    private final Provider<HtmlEditorPresenter> provider;

    @Inject
    public HtmlContentType(final Provider<HtmlEditorPresenter> provider) {
	super("Html", TYPE);
	this.provider = provider;
    }

    @Override
    public HtmlEditorPresenter newClipEditor(final Clip clip) {
	final HtmlEditorPresenter presenter = provider.get();
	presenter.setClip(clip);
	return presenter;
    }

    @Override
    public String render(final Clip clip) {
	return clip.getBody();
    }
}
