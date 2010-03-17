package net.boklab.module.explore.client.pointer;

import net.boklab.core.client.ui.browser.BrowserItemPresenter;
import net.boklab.module.explore.client.indice.browser.IndiceBrowserPresenter;

/**
 * Un elemento en la lista del browser del índice
 * 
 * @author dani
 * 
 */
public class PointerPresenter extends BrowserItemPresenter<PointerDisplay> {

    public PointerPresenter(final IndiceBrowserPresenter indice, final PointerDisplay display) {
	super(display);

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
