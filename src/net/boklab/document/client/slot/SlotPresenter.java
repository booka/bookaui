package net.boklab.document.client.slot;

import net.boklab.document.client.clip.ClipPresenter;
import net.boklab.document.client.content.ContentTypeListPresenter;
import net.boklab.tools.client.mvp.Presenter;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.inject.Inject;

public class SlotPresenter implements Presenter<SlotDisplay> {

    private final SlotDisplay display;
    private ClipPresenter presenter;

    @Inject
    public SlotPresenter(final SlotDisplay display, final ContentTypeListPresenter contentTypeList) {
	this.display = display;

	display.getOpen().addClickHandler(new ClickHandler() {
	    @Override
	    public void onClick(final ClickEvent event) {
		if (display.hasContentTypeList()) {
		    contentTypeList.setVisible(false);
		    display.setList(null);
		} else {
		    contentTypeList.setVisible(false);
		    contentTypeList.setCurrentClipPresenter(presenter);
		    display.setList(contentTypeList.getDisplay());
		    contentTypeList.setVisible(true);
		}
	    }
	});

    }

    @Override
    public SlotDisplay getDisplay() {
	return display;
    }

    public void setClipPresenter(final ClipPresenter presenter) {
	this.presenter = presenter;
    }

}
