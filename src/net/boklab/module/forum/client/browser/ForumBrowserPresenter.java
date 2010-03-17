package net.boklab.module.forum.client.browser;

import net.boklab.core.client.ui.browser.AbstractBrowserPresenter;
import net.boklab.core.client.ui.browser.BrowserDisplay;
import net.boklab.module.forum.client.I18nForum;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;

@Singleton
public class ForumBrowserPresenter extends
	AbstractBrowserPresenter<ForumBrowserPresenter, TopicPresenter> {

    @Inject
    public ForumBrowserPresenter(final Provider<BrowserDisplay> provider,
	    final ForumBrowserActions actions) {
	super(provider, actions);
    }

    @Override
    protected void attach(final BrowserDisplay display) {
	display.getBrowserTitle().setText(I18nForum.t.browserTitle());
	super.attach(display);
    }
}
