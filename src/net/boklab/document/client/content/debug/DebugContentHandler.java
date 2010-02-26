package net.boklab.document.client.content.debug;

import net.boklab.document.client.content.ContentEditor;
import net.boklab.document.client.content.ContentHandler;
import net.boklab.document.client.model.Clip;

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
    public ContentEditor<?> newClipEditor(final Clip clip) {
	final DebugEditorPresenter editor = provider.get();
	editor.setClip(clip);
	return editor;
    }

    @Override
    public String render(final Clip clip) {
	return "debug";
    }

}
