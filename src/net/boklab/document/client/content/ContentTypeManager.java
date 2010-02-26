package net.boklab.document.client.content;

import net.boklab.document.client.clip.ClipPresenter;
import net.boklab.document.client.clip.editor.ClipEditorDisplay;
import net.boklab.document.client.model.Clip;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;

@Singleton
public class ContentTypeManager {

    private final Provider<ClipPresenter> clipProvider;
    private final ContentTypeRegistry registry;
    private final Provider<ClipEditorDisplay> editorDisplayProvider;

    @Inject
    public ContentTypeManager(final ContentTypeRegistry registry, final Provider<ClipPresenter> clipProvider,
	    final Provider<ClipEditorDisplay> editorDisplayProvider) {
	this.registry = registry;
	this.clipProvider = clipProvider;
	this.editorDisplayProvider = editorDisplayProvider;
    }

    public ContentHandler getHandler(final Clip clip) {
	return registry.getHandler(clip.getContentType());
    }

    public ClipPresenter newClip(final Clip clip) {
	final ClipPresenter clipPresenter = clipProvider.get();
	final ContentHandler contentType = registry.getHandler(clip.getContentType());
	clipPresenter.setClip(clip, contentType);
	return clipPresenter;
    }

    public ClipEditorDisplay newEditor(final Clip clip) {
	return newEditor(clip, clip.getContentType());
    }

    public ClipEditorDisplay newEditor(final Clip clip, final String contentType) {
	final ContentHandler handler = registry.getHandler(contentType);
	final ContentEditor<?> editor = handler.newClipEditor(clip);
	final ClipEditorDisplay editorDisplay = editorDisplayProvider.get();
	editorDisplay.setEditor(editor);
	return editorDisplay;
    }

}
