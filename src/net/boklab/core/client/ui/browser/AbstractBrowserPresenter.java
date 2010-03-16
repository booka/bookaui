package net.boklab.core.client.ui.browser;

import net.boklab.tools.client.mvp.AbstractPresenter;

import com.google.inject.Provider;

public class AbstractBrowserPresenter extends AbstractPresenter<BrowserDisplay> {

    public AbstractBrowserPresenter(final Provider<BrowserDisplay> provider) {
	super(provider);
    }

}
