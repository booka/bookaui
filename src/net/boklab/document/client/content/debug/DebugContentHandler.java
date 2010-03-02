package net.boklab.document.client.content.debug;

import net.boklab.core.client.model.Bok;
import net.boklab.document.client.content.ContentEditor;
import net.boklab.document.client.content.ContentHandler;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;

@Singleton
public class DebugContentHandler extends ContentHandler {
    public static final String TYPE = "clip/debug";

    private final Provider<DebugEditorPresenter> provider;

    @Inject
    public DebugContentHandler(final Provider<DebugEditorPresenter> provider) {
	super(TYPE);
	this.provider = provider;
    }

    @Override
    public ContentEditor<?> newClipEditor() {
	final DebugEditorPresenter editor = provider.get();
	return editor;
    }

    @Override
    public String render(final Bok bok) {
	return "debug";
    }

}
