package net.boklab.module.explore.client.indice.browser;

import net.boklab.core.client.bok.events.BokOpenedEvent;
import net.boklab.core.client.bok.events.BokOpenedHandler;
import net.boklab.core.client.bok.events.BokUpdatedEvent;
import net.boklab.core.client.bok.events.BokUpdatedHandler;
import net.boklab.core.client.model.Bok;
import net.boklab.core.client.ui.browser.AbstractBrowserPresenter;
import net.boklab.core.client.ui.browser.BrowserDisplay;
import net.boklab.module.explore.client.indice.Indice;
import net.boklab.module.explore.client.manager.IndiceManager;
import net.boklab.module.explore.client.pointer.Pointer;
import net.boklab.module.explore.client.pointer.PointerPresenter;
import net.boklab.module.explore.client.pointer.PointerWidget;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;

/**
 * Es el browser de indices
 */
@Singleton
public class IndiceBrowserPresenter extends
	AbstractBrowserPresenter<IndiceBrowserPresenter, PointerPresenter> {
    private Indice current;

    @Inject
    public IndiceBrowserPresenter(final Provider<BrowserDisplay> provider,
	    final IndiceBrowserActions actions, final IndiceManager indices) {
	super(provider, actions);
	current = null;

	indices.addOpenedHandler(new BokOpenedHandler() {
	    @Override
	    public void onBokOpened(final BokOpenedEvent event) {
		setIndice(event.getBok());
	    }
	});
	indices.addUpdatedHandler(new BokUpdatedHandler() {
	    @Override
	    public void onBokUpdated(final BokUpdatedEvent event) {
		setIndice(event.getBok());
	    }
	});
    }

    public Indice getIndice() {
	return current;
    }

    private void setIndice(final Bok indiceBok) {
	current = new Indice(indiceBok);
	final BrowserDisplay display = getDisplay();
	display.getBrowserTitle().setText(indiceBok.getTitle());
	display.clearList();
	for (final Pointer p : current.getPointers()) {
	    final PointerPresenter pointer = new PointerPresenter(this, new PointerWidget());
	    pointer.setPointer(p);
	    display.addItem(pointer.getDisplay());
	}
    }

}
