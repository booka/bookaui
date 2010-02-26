package net.boklab.document.client.doc;

import net.boklab.document.client.clip.ClipPresenter;
import net.boklab.document.client.content.ClipContentType;
import net.boklab.document.client.content.ContentTypeManager;
import net.boklab.document.client.content.CreateContentEvent;
import net.boklab.document.client.content.CreateContentHandler;
import net.boklab.document.client.content.editor.ClipEditorDisplay;
import net.boklab.document.client.content.slot.SlotContentType;
import net.boklab.document.client.info.DocInfoPresenter;
import net.boklab.document.client.manager.DocumentOpenedEvent;
import net.boklab.document.client.manager.DocumentOpenedHandler;
import net.boklab.document.client.manager.Documents;
import net.boklab.document.client.model.Clip;
import net.boklab.document.client.model.Document;
import net.boklab.tools.client.mvp.AbstractPresenter;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;

@Singleton
public class DocumentPresenter extends AbstractPresenter<DocumentDisplay> {

    private final DocInfoPresenter docInfo;
    private final ContentTypeManager typeManager;
    private final Documents documents;
    private Document currentDocument;

    @Inject
    public DocumentPresenter(final Documents documents, final ContentTypeManager typeManager,
	    final DocInfoPresenter docInfo, final Provider<DocumentDisplay> displayProvider) {
	super(displayProvider);
	this.documents = documents;
	this.typeManager = typeManager;

	this.docInfo = docInfo;

	bind();

	documents.onDocumentOpened(new DocumentOpenedHandler() {
	    @Override
	    public void onDocumentOpened(final DocumentOpenedEvent event) {
		setDocument(event.getDocument());
	    }
	});

	typeManager.onCreateContent(new CreateContentHandler() {
	    @Override
	    public void onCreateContent(final CreateContentEvent event) {
		createContentEditor(event.getContentType(), event.getClipPresenter());
	    }
	});
    }

    private void bind() {
	final DocumentDisplay display = getDisplay();
	display.setInfoDisplay(docInfo.getDisplay());
    }

    private void createContentEditor(final ClipContentType contentType, final ClipPresenter clipPresenter) {
	final Clip clip = new Clip();
	final ClipEditorDisplay editorDisplay = typeManager.newEditor(clip);

	editorDisplay.getCancel().addClickHandler(new ClickHandler() {
	    @Override
	    public void onClick(final ClickEvent event) {
		getDisplay().remove(editorDisplay);
	    }
	});

	editorDisplay.getSave().addClickHandler(new ClickHandler() {
	    @Override
	    public void onClick(final ClickEvent event) {
		documents.createClip(currentDocument, clip);
		getDisplay().remove(editorDisplay);
	    }
	});

	if (clipPresenter != null) {
	    getDisplay().insert(editorDisplay, clipPresenter.getDisplay());
	} else {
	    getDisplay().add(editorDisplay);
	}
    }

    private ClipPresenter createSlot(final int position) {
	final Clip slotClip = new Clip();
	slotClip.setPosition(position);
	slotClip.setContentType(SlotContentType.TYPE);
	final ClipPresenter slot = typeManager.newClip(slotClip);
	return slot;
    }

    protected void setDocument(final Document document) {
	currentDocument = document;
	docInfo.setDocument(document);
	final DocumentDisplay display = getDisplay();
	display.clear();
	Clip last = null;
	for (final Clip clip : document) {
	    final ClipPresenter slot = createSlot(clip.getPosition());
	    final ClipPresenter presenter = typeManager.newClip(clip);
	    display.add(slot.getDisplay());
	    display.add(presenter.getDisplay());
	    last = clip;
	}
	final int position = last != null ? last.getPosition() + 1 : 1;
	final ClipPresenter slot = createSlot(position);
	display.add(slot.getDisplay());
    }
}
