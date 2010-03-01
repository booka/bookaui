package net.boklab.document.client.content.info;

import net.boklab.core.client.model.Bok;
import net.boklab.document.client.content.ContentEditor;

import com.google.inject.Inject;

public class InfoEditorPresenter implements ContentEditor<InfoEditorDisplay> {

    private final InfoEditorDisplay display;
    private Bok bok;

    @Inject
    public InfoEditorPresenter(final InfoEditorDisplay display) {
	this.display = display;
    }

    @Override
    public Bok getBok() {
	return bok;
    }

    @Override
    public InfoEditorDisplay getDisplay() {
	return display;
    }

    @Override
    public void setBok(final Bok bok) {
	this.bok = bok;
	display.getBokTitle().setText(bok.getTitle());
	display.getDescription().setText(bok.getDescription());
    }

    @Override
    public void updateClip() {
	bok.setTitle(display.getBokTitle().getText());
	bok.setDescription(display.getDescription().getText());
    }

}
