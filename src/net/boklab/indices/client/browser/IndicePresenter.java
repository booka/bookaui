package net.boklab.indices.client.browser;

import net.boklab.tools.client.mvp.AbstractPresenter;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;

/**
 * Es el browser de indices
 */
@Singleton
public class IndicePresenter extends AbstractPresenter<BrowserDisplay> {

    @Inject
    public IndicePresenter(final Provider<BrowserDisplay> provider) {
	super(provider);
    }

    @Override
    protected void attach() {
	getDisplay().getBrowserTitle().setText("Explorando");
    }

}
