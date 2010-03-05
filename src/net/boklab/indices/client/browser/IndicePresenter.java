package net.boklab.indices.client.browser;

import net.boklab.tools.client.mvp.Presenter;

import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class IndicePresenter implements Presenter<BrowserDisplay> {

    private final BrowserDisplay display;

    @Inject
    public IndicePresenter(final BrowserDisplay display) {
	this.display = display;
	display.getBrowserTitle().setText("Explorando");
    }

    public BrowserDisplay getDisplay() {
	return display;
    }
}
