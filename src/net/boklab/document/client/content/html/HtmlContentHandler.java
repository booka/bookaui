package net.boklab.document.client.content.html;

import net.boklab.core.client.model.Bok;
import net.boklab.document.client.content.ContentHandler;

import com.google.inject.Inject;
import com.google.inject.Provider;

public class HtmlContentHandler extends ContentHandler {

    public static final String MIME = "text/html";
    private final Provider<HtmlEditorPresenter> provider;

    @Inject
    public HtmlContentHandler(final Provider<HtmlEditorPresenter> provider) {
	super(MIME);
	this.provider = provider;
    }

    @Override
    public HtmlEditorPresenter newClipEditor() {
	return provider.get();
    }

    @Override
    public String render(final Bok bok) {
	final String debug = getDebug(bok);

	return debug + bok.getBody();
    }

    private String getDebug(final Bok bok) {
	String debug = "<div class='debug'>{";
	debug += bok.getBokType() + ":" + bok.getId();
	debug += " par:" + bok.getParentId();
	debug += " pos:" + bok.getPosition();
	debug += " ctype:" + bok.getContentType();
	debug += " updated: " + bok.getUpdatedAt();
	debug += "}</div>";
	return debug;
    }
}
