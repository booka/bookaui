package net.boklab.core.client.ui.browser;

import net.boklab.tools.client.mvp.Presenter;

public abstract class BrowserItemPresenter<D extends BrowserItemDisplay> implements Presenter<D> {
    protected final D display;

    public BrowserItemPresenter(final D display) {
	this.display = display;
    }

    @Override
    public D getDisplay() {
	return display;
    }

    public abstract void setSelected(boolean selected);
}
