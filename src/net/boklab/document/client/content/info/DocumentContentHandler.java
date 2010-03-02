package net.boklab.document.client.content.info;

import net.boklab.core.client.model.Bok;
import net.boklab.document.client.content.ContentEditor;
import net.boklab.document.client.content.ContentHandler;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;

@Singleton
public class DocumentContentHandler extends ContentHandler {
    private final Provider<InfoEditorPresenter> provider;

    @Inject
    public DocumentContentHandler(final Provider<InfoEditorPresenter> provider) {
	super("Document");
	this.provider = provider;
    }

    @Override
    public ContentEditor<?> newClipEditor() {
	return provider.get();
    }

    @Override
    public String render(final Bok bok) {
	String description = bok.getDescription();
	description = description != null ? description : "";
	return "<div class=\"Document\"><h1>" + bok.getTitle() + "</h1><p class=\"description\">" + description
		+ "</p></div>";
    }
}
