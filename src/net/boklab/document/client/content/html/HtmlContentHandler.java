package net.boklab.document.client.content.html;

import net.boklab.core.client.model.Bok;
import net.boklab.document.client.content.ContentHandler;

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
    public HtmlEditorPresenter newClipEditor(final Bok bok) {
	final HtmlEditorPresenter presenter = provider.get();
	presenter.setBok(bok);
	return presenter;
    }

    @Override
    public String render(final Bok bok) {
	final String debug = "<div class='debug'>{" + bok.getBokType() + ":" + bok.getId() + " pos:"
		+ bok.getPosition() + " ctype:" + bok.getContentType() + " updated: " + bok.getUpdatedAt() + "}</div>";

	return debug + bok.getBody();
    }
}
