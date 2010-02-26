package net.boklab.document.client.content;

import net.boklab.document.client.clip.ClipPresenter;
import net.boklab.document.client.content.editor.ClipEditorDisplay;
import net.boklab.document.client.model.Clip;
import net.boklab.document.client.slot.SlotPresenter;
import net.boklab.tools.client.eventbus.EventBus;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;

@Singleton
public class ContentTypeManager {

    private final Provider<ClipPresenter> clipProvider;
    private final Provider<SlotPresenter> slotProvider;
    private final ContentTypeRegistry registry;
    private final EventBus eventBus;
    private final Provider<ClipEditorDisplay> editorDisplayProvider;

    @Inject
    public ContentTypeManager(final EventBus eventBus, final ContentTypeRegistry registry,
	    final Provider<ClipPresenter> clipProvider, final Provider<SlotPresenter> slotProvider,
	    final Provider<ClipEditorDisplay> editorDisplayProvider) {
	this.eventBus = eventBus;
	this.registry = registry;
	this.clipProvider = clipProvider;
	this.slotProvider = slotProvider;
	this.editorDisplayProvider = editorDisplayProvider;
    }

    public ClipPresenter newClip(final Clip clip) {
	final ClipPresenter clipPresenter = clipProvider.get();
	final ClipContentType contentType = registry.getType(clip.getContentType());
	clipPresenter.setClip(clip, contentType);
	return clipPresenter;
    }

    public ClipEditorDisplay newEditor(final Clip clip) {
	final ClipContentType contentType = registry.getType(clip.getContentType());
	final ContentTypeEditorPresenter<?> editor = contentType.newClipEditor(clip);
	final ClipEditorDisplay presenter = editorDisplayProvider.get();
	presenter.setEditor(editor.getDisplay());
	return presenter;
    }

    public SlotPresenter newSlot() {
	return slotProvider.get();
    }

    public void onCreateContent(final CreateContentHandler handler) {
	eventBus.addHandler(CreateContentEvent.TYPE, handler);
    }

}
