package net.boklab.document.client.content.info;

import net.boklab.core.client.model.Bok;
import net.boklab.document.client.bok.editor.AbstractEditor;

import com.google.inject.Inject;
import com.google.inject.Provider;

public class InfoEditorPresenter extends AbstractEditor<InfoEditorDisplay> {

    private Bok bok;

    @Inject
    public InfoEditorPresenter(final Provider<InfoEditorDisplay> provider) {
	super(provider);
    }

    @Override
    public Bok getBok() {
	return bok;
    }

    @Override
    public void setBok(final Bok bok) {
	this.bok = bok;
	final InfoEditorDisplay display = getDisplay();
	display.getBokTitle().setText(bok.getTitle());
	display.getDescription().setText(bok.getDescription());
    }

    @Override
    public void updateClip() {
	final InfoEditorDisplay display = getDisplay();
	bok.setTitle(display.getBokTitle().getText());
	bok.setDescription(display.getDescription().getText());
    }

}
