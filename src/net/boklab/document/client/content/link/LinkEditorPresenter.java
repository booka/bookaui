package net.boklab.document.client.content.link;

import net.boklab.core.client.model.Bok;
import net.boklab.document.client.content.ContentEditor;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.inject.Inject;

public class LinkEditorPresenter implements ContentEditor<LinkEditorDisplay> {

    private final LinkEditorDisplay display;
    private Bok bok;

    @Inject
    public LinkEditorPresenter(final LinkContentManager manager, final LinkEditorDisplay display) {
	this.display = display;

	display.getPreviewAction().addClickHandler(new ClickHandler() {
	    @Override
	    public void onClick(final ClickEvent event) {
		final String title = display.getBokTitle().getText();
		final String body = display.getBody().getText();
		final String rendered = manager.render(title, body);
		display.setPreviewHtml(rendered);
	    }
	});
    }

    @Override
    public Bok getBok() {
	return bok;
    }

    @Override
    public LinkEditorDisplay getDisplay() {
	return display;
    }

    @Override
    public void setBok(final Bok bok) {
	this.bok = bok;
	display.getBokTitle().setText(bok.getTitle());
	display.getBody().setText(bok.getBody());
    }

    @Override
    public void updateClip() {
	bok.setTitle(display.getBokTitle().getText());
	bok.setBody(display.getBody().getText());
    }
}
