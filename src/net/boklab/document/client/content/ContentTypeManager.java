package net.boklab.document.client.content;

import net.boklab.core.client.model.Bok;
import net.boklab.document.client.bok.BokPresenter;
import net.boklab.document.client.bok.BokPresenter.InsertHandler;
import net.boklab.document.client.bok.editor.BokEditorDisplay;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;

@Singleton
public class ContentTypeManager {

    private final Provider<BokPresenter> clipProvider;
    private final ContentHandlerRegistry registry;
    private final Provider<BokEditorDisplay> editorDisplayProvider;

    @Inject
    public ContentTypeManager(final ContentHandlerRegistry registry, final Provider<BokPresenter> clipProvider,
	    final Provider<BokEditorDisplay> editorDisplayProvider) {
	this.registry = registry;
	this.clipProvider = clipProvider;
	this.editorDisplayProvider = editorDisplayProvider;
    }

    public ContentHandler getHandler(final Bok bok) {
	return registry.getHandler(getContentType(bok));
    }

    public BokPresenter newBokPresenter(final Bok bok, final InsertHandler insertHandler) {
	final BokPresenter clipPresenter = clipProvider.get();
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

    public BokEditorDisplay newEditor(final Bok clip, final String contentType) {
	final ContentHandler handler = registry.getHandler(contentType);
	final ContentEditor<?> editor = handler.newClipEditor(clip);
	final BokEditorDisplay editorDisplay = editorDisplayProvider.get();
	editorDisplay.setEditor(editor);
	return editorDisplay;
    }

    private String getContentType(final Bok bok) {
	final String contentType = bok.getContentType();
	return !"".equals(contentType) ? contentType : bok.getBokType();
    }

}
