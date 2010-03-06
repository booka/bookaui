package net.boklab.document.client.content.info;

import net.boklab.core.client.model.Bok;
import net.boklab.document.client.content.ContentEditor;
import net.boklab.document.client.content.ContentHandler;

import com.google.inject.Provider;

public class InfoContentHandler extends ContentHandler {

    protected final Provider<InfoEditorPresenter> provider;

    public InfoContentHandler(final String mimeType, final Provider<InfoEditorPresenter> provider) {
	super(mimeType);
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
	return "<div class=\"BokInfo " + type + "\"><h1>" + bok.getTitle()
		+ "</h1><p class=\"description\">" + description + "</p></div>";
    }

}
