package net.boklab.core.client.ui.browser;

import java.util.ArrayList;
import java.util.Iterator;

public class BrowserActions<B, I> implements Iterable<BrowserAction<B, I>> {

    private final ArrayList<BrowserAction<B, I>> actions;

    public BrowserActions() {
	this.actions = new ArrayList<BrowserAction<B, I>>();
    }

    public void addAction(final BrowserAction<B, I> action) {
	actions.add(action);
    }

    @Override
    public Iterator<BrowserAction<B, I>> iterator() {
	return actions.iterator();
    }

}
