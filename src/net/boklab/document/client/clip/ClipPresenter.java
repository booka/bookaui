package net.boklab.document.client.clip;

import net.boklab.document.client.clip.action.ClipActionsPresenter;
import net.boklab.document.client.clip.editor.ClipEditorDisplay;
import net.boklab.document.client.content.ContentHandler;
import net.boklab.document.client.manager.BokUpdatedEvent;
import net.boklab.document.client.manager.BokUpdatedHandler;
import net.boklab.document.client.model.Clip;
import net.boklab.tools.client.eventbus.EventBus;
import net.boklab.tools.client.mvp.Presenter;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.inject.Inject;

public class ClipPresenter implements Presenter<ClipDisplay> {

    private Clip clip;
    private ContentHandler contentType;
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
			setClip((Clip) event.getBok(), contentType);
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
	return contentType;
    }

    @Override
    public ClipDisplay getDisplay() {
	return display;
    }

    public boolean isClipType(final String type) {
	return clip.getContentType().equals(type);
    }

    public void setClip(final Clip clip, final ContentHandler contentType) {
	this.clip = clip;
	this.contentType = contentType;
	final String body = contentType.render(clip);
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
	display.setViewVisible(!waiting);
	display.setWaitingVisible(waiting);
    }
}
