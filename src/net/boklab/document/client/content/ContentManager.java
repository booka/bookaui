package net.boklab.document.client.content;

import net.boklab.core.client.model.Bok;
import net.boklab.document.client.bok.ClipPresenter;
import net.boklab.document.client.bok.ClipPresenter.InsertHandler;
import net.boklab.document.client.bok.editor.BokEditorDisplay;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;

@Singleton
public class ContentManager {

    private final Provider<ClipPresenter> clipProvider;
    private final ContentHandlerRegistry registry;
    private final Provider<BokEditorDisplay> editorDisplayProvider;

    @Inject
    public ContentManager(final ContentHandlerRegistry registry, final Provider<ClipPresenter> clipProvider,
	    final Provider<BokEditorDisplay> editorDisplayProvider) {
	this.registry = registry;
	this.clipProvider = clipProvider;
	this.editorDisplayProvider = editorDisplayProvider;
    }

    public String getContentType(final Bok bok) {
	final String contentType = bok.getContentType();
	if (contentType == null || "".equals(contentType)) {
	    return bok.getBokType();
	} else {
	    return contentType;
	}
    }

    public ContentHandler getHandler(final Bok bok) {
	return registry.getHandler(getContentType(bok));
    }

    public ClipPresenter newBokPresenter(final Bok bok, final InsertHandler insertHandler) {
	final ClipPresenter clipPresenter = clipProvider.get();
	clipPresenter.setInsertHandler(insertHandler);
	if (bok != null) {
	    final String bokContentType = getContentType(bok);
	    final ContentHandler contentHandler = registry.getHandler(bokContentType);
	    clipPresenter.setBok(bok, contentHandler);
	}
	return clipPresenter;
    }

    public BokEditorDisplay newEditor(final Bok bok) {
	return newEditor(bok, getContentType(bok));
    }

    public BokEditorDisplay newEditor(final Bok bok, final String contentType) {
	final ContentHandler handler = registry.getHandler(contentType);
	final ContentEditor<?> editor = handler.newClipEditor();
	editor.setBok(bok);
	final BokEditorDisplay editorDisplay = editorDisplayProvider.get();
	editorDisplay.setEditor(editor);
	return editorDisplay;
    }

}
