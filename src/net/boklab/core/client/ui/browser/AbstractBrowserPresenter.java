package net.boklab.core.client.ui.browser;

import net.boklab.module.explore.client.pointer.PointerPresenter;
import net.boklab.tools.client.mvp.AbstractPresenter;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.inject.Provider;

public class AbstractBrowserPresenter<B extends AbstractBrowserPresenter<?, I>, I> extends
	AbstractPresenter<BrowserDisplay> {
    protected PointerPresenter selected;
    private final BrowserActions<B, I> actions;

    public AbstractBrowserPresenter(final Provider<BrowserDisplay> provider,
	    final BrowserActions<B, I> actions) {
	super(provider);
	this.actions = actions;
    }

    public boolean hasSelected() {
	return selected != null;
    }

    @SuppressWarnings("unchecked")
    public void setSelected(final PointerPresenter pointerPresenter) {
	if (hasSelected()) {
	    selected.setSelected(false);
	}
	selected = pointerPresenter;
	selected.setSelected(true);
	for (final BrowserAction<B, I> action : actions) {
	    action.setSelected((I) selected);
	}
    }

    @SuppressWarnings("unchecked")
    private void addAction(final BrowserAction<B, I> action, final BrowserDisplay display) {
	action.setPresenter((B) this);
	display.addAction(action).addClickHandler(new ClickHandler() {
	    @Override
	    public void onClick(final ClickEvent event) {
		action.execute();
	    }
	});
    }

    @Override
    protected void attach(final BrowserDisplay display) {
	for (final BrowserAction<B, I> action : actions) {
	    addAction(action, display);
	}
    }
}
