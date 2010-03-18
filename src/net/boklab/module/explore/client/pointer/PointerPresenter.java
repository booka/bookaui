package net.boklab.module.explore.client.pointer;

import net.boklab.core.client.ui.browser.BrowserItemPresenter;
import net.boklab.module.explore.client.indice.browser.IndiceBrowserPresenter;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;

/**
 * Un elemento en la lista del browser del índice
 * 
 * @author dani
 * 
 */
public class PointerPresenter extends BrowserItemPresenter<PointerDisplay> {

    public PointerPresenter(final IndiceBrowserPresenter indice, final PointerDisplay display) {
	super(display);
	display.getSelectArea().addClickHandler(new ClickHandler() {
	    @Override
	    public void onClick(final ClickEvent event) {
		indice.setSelected(PointerPresenter.this);
	    }
	});
    }

    @Override
    public void bind() {
    }

    public void setPointer(final Pointer p) {
	display.getPointerTitle().setText(p.getTitle());
	display.getPosition().setText(p.getPosition());
    }

    @Override
    public void setSelected(final boolean active) {
	if (active) {
	    display.addStyleName("active");
	} else {
	    display.removeStyleName("active");
	}
    }

}
