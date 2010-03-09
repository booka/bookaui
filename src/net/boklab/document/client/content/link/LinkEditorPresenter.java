package net.boklab.document.client.content.link;

import net.boklab.core.client.model.Bok;
import net.boklab.document.client.bok.editor.AbstractEditor;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.inject.Inject;
import com.google.inject.Provider;

/**
 * Editor de clips de enlaces
 * 
 * @author dani
 * 
 */
public class LinkEditorPresenter extends AbstractEditor<LinkEditorDisplay> {

    private Bok bok;
    private final LinkContentManager contentManager;

    @Inject
    public LinkEditorPresenter(final LinkContentManager manager,
	    final Provider<LinkEditorDisplay> provider) {
	super(provider);
	contentManager = manager;
    }

    @Override
    public Bok getBok() {
	return bok;
    }

    @Override
    public void setBok(final Bok bok) {
	this.bok = bok;
	final LinkEditorDisplay display = getDisplay();
	display.getBokTitle().setText(bok.getTitle());
	display.getBody().setText(bok.getBody());
    }

    @Override
    public void updateClip() {
	final LinkEditorDisplay display = getDisplay();
	bok.setTitle(display.getBokTitle().getText());
	bok.setBody(display.getBody().getText());
    }

    @Override
    protected void attach(final LinkEditorDisplay display) {
	display.getPreviewAction().addClickHandler(new ClickHandler() {
	    @Override
	    public void onClick(final ClickEvent event) {
		final String title = display.getBokTitle().getText();
		final String body = display.getBody().getText();
		final String rendered = contentManager.render(title, body);
		display.setPreviewHtml(rendered);
	    }
	});
    }
}
