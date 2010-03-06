package net.boklab.tools.client.mvp;

public interface Presenter<D extends Display> {

    Presenter<Display> NONE = new Presenter<Display>() {
	@Override
	public void bind() {
	}

	@Override
	public Display getDisplay() {
	    return Display.NONE;
	}
    };

    public void bind();

    D getDisplay();

}
