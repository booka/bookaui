package net.boklab.module.explore.client.browser;

import java.util.ArrayList;

import net.boklab.core.client.bok.events.BokOpenedEvent;
import net.boklab.core.client.bok.events.BokOpenedHandler;
import net.boklab.core.client.bok.events.BokUpdatedEvent;
import net.boklab.core.client.bok.events.BokUpdatedHandler;
import net.boklab.core.client.model.Bok;
import net.boklab.core.client.ui.browser.AbstractBrowserPresenter;
import net.boklab.core.client.ui.browser.BrowserDisplay;
import net.boklab.module.explore.client.manager.IndiceManager;
import net.boklab.module.explore.client.model.Indice;
import net.boklab.module.explore.client.model.Pointer;
import net.boklab.module.explore.client.pointer.PointerPresenter;
import net.boklab.module.explore.client.pointer.PointerWidget;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;

/**
 * Es el browser de indices
 */
@Singleton
public class IndiceBrowserPresenter extends AbstractBrowserPresenter {
    private final ArrayList<IndiceBrowserAction> actions;
    private Indice current;
    private PointerPresenter selected;

    @Inject
    public IndiceBrowserPresenter(final Provider<BrowserDisplay> provider,
	    final IndiceManager indices) {
	super(provider);
	actions = new ArrayList<IndiceBrowserAction>();
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

    public void addAction(final IndiceBrowserAction action) {
	actions.add(action);
	if (isBinded()) {
	    addAction(action, getDisplay());
	}
    }

    public Indice getIndice() {
	return current;
    }

    public boolean hasSelected() {
	return selected != null;
    }

    public void setSelected(final PointerPresenter pointerPresenter) {
	if (hasSelected()) {
	    selected.setSelected(false);
	}
	selected = pointerPresenter;
	selected.setSelected(true);
	for (final IndiceBrowserAction action : actions) {
	    action.setSelected(selected);
	}
    }

    private void addAction(final IndiceBrowserAction action, final BrowserDisplay display) {
	action.setPresenter(this);
	display.addAction(action).addClickHandler(new ClickHandler() {
	    @Override
	    public void onClick(final ClickEvent event) {
		action.execute();
	    }
	});
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

    @Override
    protected void attach(final BrowserDisplay display) {
	for (final IndiceBrowserAction action : actions) {
	    addAction(action, display);
	}
    }

}
