package net.boklab.tools.client.mvp;


public abstract class AbstractPresenter<D extends Display> implements Presenter<D> {
    protected final D display;

    public AbstractPresenter(D display) {
	this.display = display;
    }

    @Override
    public D getDisplay() {
	return display;
    }

}
