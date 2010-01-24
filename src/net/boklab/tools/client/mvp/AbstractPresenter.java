package net.boklab.tools.client.mvp;

import com.google.inject.Provider;

public abstract class AbstractPresenter<D extends Display> implements Presenter<D> {
    private D display;
    private final Provider<D> provider;

    public AbstractPresenter(Provider<D> provider) {
	this.provider = provider;
	this.display = null;
    }

    @Override
    public D getDisplay() {
	if (display == null)
	    display = provider.get();
	return display;
    }

}
