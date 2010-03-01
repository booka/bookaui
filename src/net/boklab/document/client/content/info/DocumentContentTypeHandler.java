package net.boklab.document.client.content.info;

import net.boklab.core.client.model.Bok;
import net.boklab.document.client.content.ContentEditor;
import net.boklab.document.client.content.ContentHandler;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;

@Singleton
public class DocumentContentTypeHandler extends ContentHandler {
    private final Provider<InfoEditorPresenter> provider;

    @Inject
    public DocumentContentTypeHandler(final Provider<InfoEditorPresenter> provider) {
	super("Document");
	this.provider = provider;
    }

    @Override
    public ContentEditor<?> newClipEditor(final Bok bok) {
	final InfoEditorPresenter editor = provider.get();
	editor.setBok(bok);
	return editor;
    }

    @Override
    public String render(final Bok bok) {
	return "<div class=\"Document\"><h1>" + bok.getTitle() + "</h1><p class=\"description\">"
		+ bok.getDescription() + "</p></div>";
    }
}
