package net.boklab.document.client.content.html;

import net.boklab.document.client.content.ContentHandler;
import net.boklab.document.client.model.Clip;

import com.google.inject.Inject;
import com.google.inject.Provider;

public class HtmlContentHandler extends ContentHandler {

    public static final String TYPE = "text/html";
    private final Provider<HtmlEditorPresenter> provider;

    @Inject
    public HtmlContentHandler(final Provider<HtmlEditorPresenter> provider) {
	super(TYPE);
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
	final String debug = "<div class='debug'>{Bok:" + clip.getId() + " pos:" + clip.getPosition() + " ctype:"
		+ clip.getContentType() + "}</div>";

	return debug + clip.getBody();
    }
}
