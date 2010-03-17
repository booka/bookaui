package net.boklab.document.client.bok;

import net.boklab.core.client.bok.events.BokUpdatedEvent;
import net.boklab.core.client.bok.events.BokUpdatedHandler;
import net.boklab.core.client.model.Bok;
import net.boklab.document.client.bok.action.BokActionsPresenter;
import net.boklab.document.client.bok.editor.BokEditorDisplay;
import net.boklab.document.client.content.ContentHandler;
import net.boklab.tools.client.eventbus.EventBus;
import net.boklab.tools.client.mvp.Presenter;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.MouseOutEvent;
import com.google.gwt.event.dom.client.MouseOutHandler;
import com.google.gwt.event.dom.client.MouseOverEvent;
import com.google.gwt.event.dom.client.MouseOverHandler;
import com.google.inject.Inject;

public class ClipPresenter implements Presenter<BokDisplay> {

    public static interface InsertHandler {

	void onInsert(ClipPresenter bokPresenter, boolean insertBefore);

	void remove(ClipPresenter bokPresenter);

    }
    private Bok bok;
    private ContentHandler contentHandler;

    private final BokDisplay display;
    private InsertHandler insertHandler;
    private final BokActionsPresenter actions;
    private boolean locked;
    private boolean active;

    @Inject
    public ClipPresenter(final EventBus eventBus, final BokDisplay display,
	    final BokActionsPresenter actions) {
	this.display = display;
	this.actions = actions;
	locked = true;

	eventBus.addHandler(BokUpdatedEvent.getType(), new BokUpdatedHandler() {
	    @Override
	    public void onBokUpdated(final BokUpdatedEvent event) {
		if (bok != null) {
		    final Bok newBok = event.getBok();
		    if (newBok.getId().equals(bok.getId())) {
			setBok(bok, contentHandler);
			setWaiting(false);
		    }
		}
	    }
	});
	display.setSlotsVisible(false);

	display.getMouseOver().addMouseOverHandler(new MouseOverHandler() {
	    @Override
	    public void onMouseOver(final MouseOverEvent event) {
		if (shouldActivate()) {
		    setActive(true);
		}
	    }
	});
	display.getMouseOut().addMouseOutHandler(new MouseOutHandler() {
	    @Override
	    public void onMouseOut(final MouseOutEvent event) {
		if (active) {
		    setActive(false);
		}
	    }
	});

	display.getActive().addClickHandler(new ClickHandler() {
	    @Override
	    public void onClick(final ClickEvent event) {
		if (!locked) {
		    setActionsVisible(!display.hasControls());
		}
	    }
	});

	display.getInsertBefore().addClickHandler(new ClickHandler() {
	    @Override
	    public void onClick(final ClickEvent event) {
		insertHandler.onInsert(ClipPresenter.this, true);
	    }
	});
	display.getInsertAfter().addClickHandler(new ClickHandler() {
	    @Override
	    public void onClick(final ClickEvent event) {
		insertHandler.onInsert(ClipPresenter.this, false);
	    }
	});
    }

    @Override
    public void bind() {
    }

    public void destroy() {
	insertHandler.remove(this);
    }

    public Bok getBok() {
	return bok;
    }

    public ContentHandler getContentHandler() {
	return contentHandler;
    }

    @Override
    public BokDisplay getDisplay() {
	return display;
    }

    public boolean isClipType(final String type) {
	return bok.getContentType().equals(type);
    }

    public void setActionsVisible(final boolean visible) {
	if (visible) {
	    actions.setPresenter(this);
	    display.setControls(actions.getDisplay());
	} else {
	    display.setControls(null);
	}
    }

    public void setBok(final Bok clip, final ContentHandler contentHandler) {
	bok = clip;
	this.contentHandler = contentHandler;
	final boolean hasContent = contentHandler != null;
	if (hasContent) {
	    final String body = contentHandler.render(clip);
	    display.getBody().setHTML(body);
	}
	getDisplay().setViewVisible(hasContent);
    }

    public void setEditor(final BokEditorDisplay editor) {
	display.setControls(editor);
	final boolean hasEditor = editor != null;
	display.setViewVisible(!hasEditor);
	if (hasEditor) {
	    display.addStyleName("editor");
	} else {
	    display.removeStyleName("editor");
	}
    }

    public void setInsertHandler(final InsertHandler handler) {
	insertHandler = handler;
    }

    public void setLocked(final boolean locked) {
	this.locked = locked;
    }

    public void setWaiting(final boolean waiting) {
	setEditor(null);
	display.setViewVisible(!waiting);
	display.setWaitingVisible(waiting);
    }

    private void setActive(final boolean active) {
	this.active = active;
	GWT.log("ClipPresenter ACTIVE: " + active);
	if (active) {
	    display.addStyleName("active");
	} else {
	    display.removeStyleName("active");
	}
	display.setSlotsVisible(active);
    }

    private boolean shouldActivate() {
	return !locked && !display.hasControls();
    }
}
