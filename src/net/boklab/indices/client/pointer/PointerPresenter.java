package net.boklab.indices.client.pointer;

import net.boklab.indices.client.browser.IndiceBrowserPresenter;
import net.boklab.indices.client.model.Pointer;
import net.boklab.tools.client.mvp.Presenter;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;

/**
 * Un elemento en la lista del browser del índice
 * 
 * @author dani
 * 
 */
public class PointerPresenter implements Presenter<PointerDisplay> {

    private final PointerDisplay display;

    public PointerPresenter(final IndiceBrowserPresenter indice, final PointerDisplay display) {
	this.display = display;
	display.getSelf().addClickHandler(new ClickHandler() {
	    @Override
	    public void onClick(final ClickEvent event) {
		indice.setSelected(PointerPresenter.this);
	    }
	});
    }

    @Override
    public void bind() {
    }

    @Override
    public PointerDisplay getDisplay() {
	return display;
    }

    public void setSelected(final boolean active) {
	if (active) {
	    display.addStyleName("active");
	} else {
	    display.removeStyleName("active");
	}
    }

    public void setPointer(final Pointer p) {
	display.getPointerTitle().setText(p.getTitle());
	display.getPosition().setText(p.getPosition());
    }

}
