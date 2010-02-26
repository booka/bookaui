package net.boklab.document.client.clip;

import net.boklab.document.client.clip.action.ClipActionsPresenter;
import net.boklab.document.client.content.ClipContentType;
import net.boklab.document.client.content.editor.ClipEditorDisplay;
import net.boklab.document.client.model.Clip;
import net.boklab.tools.client.mvp.Presenter;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.inject.Inject;

public class ClipPresenter implements Presenter<ClipDisplay> {

    private Clip clip;
    private ClipContentType contentType;
    private final ClipDisplay display;

    @Inject
    public ClipPresenter(final ClipDisplay display, final ClipActionsPresenter actions) {
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
    }

    public Clip getClip() {
	return clip;
    }

    public ClipContentType getContentType() {
	return contentType;
    }

    @Override
    public ClipDisplay getDisplay() {
	return display;
    }

    public boolean isClipType(final String type) {
	return clip.getContentType().equals(type);
    }

    public void setClip(final Clip clip, final ClipContentType contentType) {
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
}
