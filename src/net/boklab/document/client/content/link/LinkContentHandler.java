package net.boklab.document.client.content.link;

import net.boklab.core.client.model.Bok;
import net.boklab.document.client.content.ContentEditor;
import net.boklab.document.client.content.ContentHandler;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;

@Singleton
public class LinkContentHandler extends ContentHandler {

    public static final String MIME = "text/link";
    private final Provider<LinkEditorPresenter> provider;
    private final LinkContentManager manager;

    @Inject
    public LinkContentHandler(final LinkContentManager manager, final Provider<LinkEditorPresenter> provider) {
	super(MIME);
	this.manager = manager;
	this.provider = provider;
    }

    @Override
    public ContentEditor<?> newClipEditor() {
	return provider.get();
    }

    @Override
    public String render(final Bok bok) {
	return manager.render(bok.getTitle(), bok.getBody());
    }

}
