package net.boklab.tools.client.mvp;

import com.google.inject.Provider;

public abstract class AbstractPresenter<D extends Display> implements Presenter<D> {
    private D displayInstance;
    private final Provider<D> provider;
    private boolean binded;

    public AbstractPresenter(final Provider<D> provider) {
	this.provider = provider;
	this.displayInstance = null;
	binded = false;
    }

    @Override
    public final void bind() {
	if (!binded) {
	    attach(getDisplay());
	    binded = true;
	}
    }

    @Override
    public D getDisplay() {
	if (displayInstance == null) {
	    displayInstance = provider.get();
	    bind();
	}
	return displayInstance;
    }

    public boolean isBinded() {
	return binded;
    }

    protected void attach(final D display) {

    }

}
