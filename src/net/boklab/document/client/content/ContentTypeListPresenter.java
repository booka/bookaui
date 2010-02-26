package net.boklab.document.client.content;

import net.boklab.document.client.clip.ClipPresenter;
import net.boklab.tools.client.eventbus.EventBus;
import net.boklab.tools.client.mvp.Presenter;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.inject.Inject;

public class ContentTypeListPresenter implements Presenter<ContentTypeListDisplay> {

    private final ContentTypeListDisplay display;
    private ClipPresenter clipPresenter;

    @Inject
    public ContentTypeListPresenter(final EventBus eventBus, final ContentTypeRegistry registry,
	    final ContentTypeListDisplay display) {
	this.display = display;
	for (final ClipContentType contentType : registry) {
	    display.addContentType(contentType.getLabel()).addClickHandler(new ClickHandler() {
		@Override
		public void onClick(final ClickEvent event) {
		    eventBus.fireEvent(new CreateContentEvent(contentType, clipPresenter));
		}
	    });
	}
    }

    @Override
    public ContentTypeListDisplay getDisplay() {
	return display;
    }

    public void setCurrentClipPresenter(final ClipPresenter clipPresenter) {
	this.clipPresenter = clipPresenter;
    }

    public void setVisible(final boolean visible) {
	display.setVisible(visible);
    }

}
