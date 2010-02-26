package net.boklab.document.client.clip;

import net.boklab.core.client.persistence.BokUpdatedEvent;
import net.boklab.core.client.persistence.BokUpdatedHandler;
import net.boklab.document.client.clip.action.ClipActionsPresenter;
import net.boklab.document.client.clip.editor.ClipEditorDisplay;
import net.boklab.document.client.content.ContentHandler;
import net.boklab.document.client.model.Clip;
import net.boklab.tools.client.eventbus.EventBus;
import net.boklab.tools.client.mvp.Presenter;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.inject.Inject;

public class ClipPresenter implements Presenter<ClipDisplay> {

    private Clip clip;
    private ContentHandler contentHandler;
    private final ClipDisplay display;

    @Inject
    public ClipPresenter(final EventBus eventBus, final ClipDisplay display, final ClipActionsPresenter actions) {
	this.display = display;
	display.getActive().addClickHandler(new ClickHandler() {
	    @Override
	    public void onClick(final ClickEvent event) {
		if (display.areControls()) {
		    display.setControls(null);
		    actions.setPresenter(null);
		} else {
		    actions.setPresenter(ClipPresenter.this);
		    display.setControls(actions.getDisplay());
		}
	    }
	});

	eventBus.addHandler(BokUpdatedEvent.getType(), new BokUpdatedHandler() {
	    @Override
	    public void onBokUpdated(final BokUpdatedEvent event) {
		if (clip != null) {
		    if (event.getBok().getId().equals(clip.getId())) {
			setClip((Clip) event.getBok(), contentHandler);
			setWaiting(false);
		    }
		}
	    }
	});

    }

    public Clip getClip() {
	return clip;
    }

    public ContentHandler getContentType() {
	return contentHandler;
    }

    @Override
    public ClipDisplay getDisplay() {
	return display;
    }

    public boolean isClipType(final String type) {
	return clip.getContentType().equals(type);
    }

    public void setClip(final Clip clip, final ContentHandler contentHandler) {
	this.clip = clip;
	this.contentHandler = contentHandler;
	final String body = contentHandler.render(clip);
	getDisplay().getBody().setHTML(body);
    }

    public void setEditor(final ClipEditorDisplay editor) {
	display.setControls(editor);
	final boolean hasEditor = editor != null;
	display.setViewVisible(!hasEditor);
	if (hasEditor) {
	    display.addStyleName("editor");
	} else {
	    display.removeStyleName("editor");
	}
    }

    public void setWaiting(final boolean waiting) {
	setEditor(null);
	display.setViewVisible(!waiting);
	display.setWaitingVisible(waiting);
    }
}
